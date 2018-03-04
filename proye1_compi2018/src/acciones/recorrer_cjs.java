
package acciones;
import cjs.Simbolo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proye1_compi2018.Tsimbolo;
import proye1_compi2018.Nodo;

public class recorrer_cjs {
    
    public Tsimbolo global;
    javax.swing.JTextArea txt_consola;
    public Stack<Integer> pila_desplay = new Stack<Integer>();
    public Stack<Tsimbolo> pila_tb = new Stack<Tsimbolo>();
    public String error_cjs = "";

    public recorrer_cjs (Tsimbolo global, javax.swing.JTextArea txt_consola, Nodo cjs){
        this.global = global;
        this.txt_consola = txt_consola;
        Tsimbolo tb = new Tsimbolo("local","local");
        pila_tb.push(tb);
        primera_pasada(cjs, true, 0);
    } 

    
    public Tsimbolo primera_pasada(Nodo cjs, boolean estado, int ambito){
        Tsimbolo recuperar ;
        for(Nodo hijo : cjs.getHijos()){ 
            switch(hijo.getNombre()){
                case "FUNCION":
                    recuperar = metodo_crear_funcion(estado, hijo, ambito);
                    break;
              }
        }
        inicio_cjs(cjs, true, 0); 
        return null;
    }
    //bool global
    public Tsimbolo inicio_cjs(Nodo cjs, boolean estado, int ambito){
        Tsimbolo recuperar ;
        for(Nodo hijo : cjs.getHijos()){ 
            switch(hijo.getNombre()){
                case "DIMV_VARIABLE":
                    recuperar = metodo_Dimv_variable(ambito, estado, hijo.getValor());
                    break;
                case "DIMV_ASIGNACION":
                    recuperar = metodo_DIMV_Asig(hijo,ambito,estado,hijo.getValor());
                    break;
                case "DIMV_VECTOR":
                    recuperar = metodo_dimv_vector(ambito, estado, hijo.getValor(), hijo);
                    break;
                case "VARIABLE":
                    recuperar = metodo_variable(ambito, estado, hijo);
                    imprimir("correcto");
                    break;
                case "VECTOR":
                    recuperar = metodo_vector(ambito, estado, hijo);
                    break;
                case "IMPRIMIR":
                    recuperar = metodo_imprimir(hijo, estado);
                    break;
                case "VECTOR_VACIO":
                    recuperar = metodo_vector_vacio(ambito, estado, hijo.getValor(), hijo);
                    break;
                case "VARIABLE_VECTOR":
                    recuperar = metodo_variable_vector(ambito, estado, hijo);
                    break;
                case "SI":
                    recuperar = metodo_si(estado, hijo, ambito);
                    if(recuperar.getRb().equalsIgnoreCase("RETURN")){
                        return recuperar;
                    }
                    break;
                case "SINO":
                    break;
                case "MIENTRAS":
                    recuperar = metodo_mientras(estado, hijo, ambito);
                    break;
                case "FUNCION":
                    break;
                case "LLAMADA":
                    recuperar = llamada_funcion(hijo, estado);
                    break;
                case "PARAMETROS":
                    break;
                case "RETORNAR":
                    return metodo_retonar(hijo, estado);
                case "MENSAJE":
                    mesaje(hijo, estado);
                    break;
            }
        }
        return global;
    }
    
    public Tsimbolo metodo_Dimv_variable(int ambito, boolean estado, String nombre){
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo temp1 = buscar_variable(estado, nombre, "VARIABLE");
        Tsimbolo temp2 = buscar_variable(estado, nombre, "VECTOR");
        Tsimbolo temp3 = buscar_Solo_local_variable(nombre, "VARIABLE");
        Tsimbolo temp4 = buscar_Solo_local_variable(nombre, "VECTOR");
        if(temp1==null && temp2==null && estado){ //global
            Tsimbolo nuevo = new Tsimbolo(nombre,"VARIABLE",ambito);
            global.addVector(nuevo);
            temp.setTipo_valor("CORRECTO");
            return temp;
        }else if(temp3==null && temp4==null && !estado){ //local
                Tsimbolo nuevo = new Tsimbolo(nombre,"VARIABLE",ambito);
                actual.addVector(nuevo);
                temp.setTipo_valor("CORRECTO");
                return temp;
        }else{
            Hay_error("Esta variable ya exite: "+nombre);
            temp.setTipo_valor("ERROR");
            return temp;
        }
    } 
    
    public Tsimbolo buscar_variable(boolean estado, String nombre, String tipo){
        Tsimbolo actual = pila_tb.peek();
        if(estado==true){
            for(Tsimbolo hijo : global.getVector()){
                if(hijo.getNombre().equalsIgnoreCase(nombre) && hijo.getTipo().equalsIgnoreCase(tipo)){ 
                        return hijo; }
                }
        }else{
            for(Tsimbolo hijo : actual.getVector()){
                if(hijo.getNombre().equalsIgnoreCase(nombre) && hijo.getTipo().equalsIgnoreCase(tipo)){ 
                        return hijo; }
            }for(Tsimbolo hijo : global.getVector()){
                if(hijo.getNombre().equalsIgnoreCase(nombre) && hijo.getTipo().equalsIgnoreCase(tipo)){ 
                        return hijo; }
                }
            }
        return null;
    }
    
    public Tsimbolo buscar_Solo_local_variable(String nombre, String tipo){
        Tsimbolo actual = pila_tb.peek();
        for(Tsimbolo hijo : actual.getVector()){
            if(hijo.getNombre().equalsIgnoreCase(nombre) && hijo.getTipo().equalsIgnoreCase(tipo)){ 
                return hijo; }
            }
        return null;
    }
    
    public Tsimbolo metodo_DIMV_Asig(Nodo raiz,int ambito, boolean estado, String nombre){
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo temp1 = buscar_variable(estado, nombre, "VARIABLE");
        Tsimbolo temp2 = buscar_variable(estado, nombre, "VECTOR");
        Tsimbolo temp3 = buscar_Solo_local_variable(nombre, "VARIABLE");
        Tsimbolo temp4 = buscar_Solo_local_variable(nombre, "VECTOR");
        if(temp1==null && temp2==null && estado){
            Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
                Tsimbolo nuevo = new Tsimbolo("","");
                if(!exp.getValor().equalsIgnoreCase("ERROR")){
                    if(exp.getTipo().equalsIgnoreCase("VECTOR")){
                       temp.setTipo_valor("ERROR");
                       return temp;
                    }else{
                        nuevo.setNombre(nombre);
                        nuevo.setAmbito(ambito);
                        nuevo.setValor(exp.getValor());
                        nuevo.setTipo_valor(exp.getTipo_valor());
                        nuevo.setTipo("VARIABLE");
                        global.addVector(nuevo);
                        temp.setTipo_valor("CORRECTO");
                        return temp;
                    }
                }else{
                    Hay_error("Se encontro error al asignar: "+nombre);
                    temp.setTipo_valor("ERROR");
                    return temp;
                }
        }else if(temp3==null && temp4==null && !estado){ //no global
             Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
             Tsimbolo nuevo = new Tsimbolo("","");
             if(!exp.getValor().equalsIgnoreCase("ERROR")){
                    if(exp.getTipo().equalsIgnoreCase("VECTOR")){
                        temp.setTipo_valor("ERROR");
                        return temp;
                    }else{
                        nuevo.setNombre(nombre);
                        nuevo.setAmbito(ambito);
                        nuevo.setValor(exp.getValor());
                        nuevo.setTipo_valor(exp.getTipo_valor());
                        nuevo.setTipo("VARIABLE");
                        actual.addVector(nuevo);
                        temp.setTipo_valor("CORRECTO");
                        return temp;
                    }
                }else{
                    Hay_error("Se encontro error al asignar: "+nombre);
                    temp.setTipo_valor("ERROR");
                    return temp;
                }
        }else{
            Hay_error("Esta variable ya exite: "+nombre);
            temp.setTipo_valor("ERROR");
            return temp;
        }
    }
    
    public Tsimbolo metodo_dimv_vector(int ambito, boolean estado, String nombre, Nodo raiz){
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo temp1 = buscar_variable(estado, nombre, "VARIABLE");
        Tsimbolo temp2 = buscar_variable(estado, nombre, "VECTOR");
        if(temp1==null && temp2==null){
            if(estado){//global
                Tsimbolo nuevo = new Tsimbolo(nombre,"VECTOR",ambito);
                nuevo.setParametros(raiz.getHijos().size());
                nuevo = llenar_vector(nuevo, estado, raiz);
                if(nuevo.getTipo_valor().equalsIgnoreCase("ERROR")){
                    Hay_error("En declarar vector");
                    temp.setTipo_valor("ERROR");
                    return temp;
                }else{
                    global.addVector(nuevo);
                    temp.setTipo_valor("CORRECTO");
                    return temp;
                }
            }else{ //no global
                Tsimbolo nuevo = new Tsimbolo(nombre,"VECTOR",ambito);
                nuevo.setParametros(raiz.getHijos().size());
                nuevo = llenar_vector(nuevo, estado, raiz);
                if(nuevo.getTipo_valor().equalsIgnoreCase("ERROR")){
                    Hay_error("En declarar vector");
                    temp.setTipo_valor("ERROR");
                    return temp;
                }else{
                    actual.addVector(nuevo);
                    temp.setTipo_valor("CORRECTO");
                    return temp;
                }  
            }
        }else{
            Hay_error("Esta variable ya exite: "+nombre);
            temp.setTipo_valor("ERROR");
            return temp;
        }
    } 
    
    public Tsimbolo metodo_variable(int ambito, boolean estado, Nodo raiz){
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo temp1 = buscar_variable(estado, raiz.getValor(), "VARIABLE");
        Tsimbolo temp2 = buscar_variable( estado, raiz.getValor(), "VECTOR");
        if(temp1!=null){ 
            Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
            if(exp.getTipo_valor().equalsIgnoreCase("ERROR")){
                Hay_error("Hubo errror en asignacion.");
                temp.setTipo_valor("ERROR");
                return temp;
            }else{
                temp1.setValor(exp.getValor());
                temp1.setTipo_valor(exp.getTipo_valor());
                if(exp.getTipo().equalsIgnoreCase("VECTOR")){
                    Hay_error("No se puede asignar un vector a una variable");
                    temp.setTipo_valor("ERROR");
                    return temp;
                }else{
                    temp1.setTipo("VARIABLE");
                    temp.setTipo_valor("CORECCTO");
                    return temp;
                }
            }
        }else if(temp2!=null){
            Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
            if(exp.getTipo_valor().equalsIgnoreCase("ERROR")){
                Hay_error("Hubo errror en asignacion.");
                temp.setTipo_valor("ERROR");
                return temp;
            }else{
                if(exp.getTipo().equalsIgnoreCase("VECTOR") &&
                        exp.getParametros()==temp2.getParametros()){ // aqui voy verificar los indices
                    temp2 = pasar_vec1_a_vec2(exp, temp2);
                    temp.setTipo_valor("CORRECTO");
                    return temp;
                }else{
                    Hay_error("No se puede asignar un valor a una variable o indice distintos");
                    temp.setTipo_valor("ERROR");
                    return temp;
                }
            }
        }else{
            Hay_error("La variable que desea asignar no existe");
            temp.setTipo_valor("ERROR");
            return temp;
        }
    }
    
    public Tsimbolo pasar_vec1_a_vec2(Tsimbolo v1, Tsimbolo v2){
        int con = 0;
        for(Tsimbolo hijo : v1.getVector()){
            v2.getVector().get(con).setValor(hijo.getValor());
            v2.getVector().get(con).setTipo_valor(hijo.getTipo_valor());
            con = con + 1;
        }
        return v2;
    }
    
    public Tsimbolo metodo_imprimir(Nodo raiz, boolean estado){
        Tsimbolo tb = expresiones(raiz.getHijos().get(0), estado);
        Tsimbolo temp = new Tsimbolo("", "");
        if(tb.getTipo_valor().equalsIgnoreCase("ERROR")){
            Hay_error("Funcion imprimir");
            temp.setTipo_valor("ERROR");
            return temp;
        }else{
            String consola = txt_consola.getText() +tb.getValor()+"\n";
            txt_consola.setText(consola);  
            temp.setTipo_valor("CORRECTO");
            return temp;
        }  
    }
    
    public Tsimbolo metodo_vector_vacio(int ambito, boolean estado, String nombre, Nodo raiz){
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo temp1 = buscar_variable(estado, nombre, "VARIABLE");
        Tsimbolo temp2 = buscar_variable(estado, nombre, "VECTOR");
        Tsimbolo temp = new Tsimbolo("","");
        if(temp1==null && temp2==null){
            Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
                if(!exp.getValor().equalsIgnoreCase("ERROR")){
                    if(exp.getTipo_valor().equalsIgnoreCase("NUMERO")){
                        Double d1 = Double.parseDouble(exp.getValor());
                        int n = (int) d1.doubleValue();
                        if(n>0){
                            Tsimbolo nuevo = new Tsimbolo(nombre,"VECTOR");
                            nuevo.setAmbito(ambito);
                            nuevo.setParametros(n);
                            nuevo = llenar_vector_vacio(nuevo, n);
                            if(estado){
                                global.addVector(nuevo);
                                temp.setTipo_valor("CORRECTO");
                                return temp;
                            }else{
                                actual.addVector(nuevo);
                                temp.setTipo_valor("CORRECTO");
                                return temp;
                            }
                        }else{
                            Hay_error("Indice menor o igual cero"+nombre);
                            temp.setTipo_valor("ERROR");
                            return temp;
                        }
                    }else{
                        Hay_error("Indice no es de tipo numero "+nombre);
                        temp.setTipo_valor("ERROR");
                        return temp;
                    }
                }else{
                    Hay_error("Se encontro error en crear vector"+nombre);
                    temp.setTipo_valor("ERROR");
                    return temp;
                }
        }else{
            Hay_error("Esta variable ya exite: "+nombre);
            temp.setTipo_valor("ERROR");
            return temp;
        }
    } 
      
    public Tsimbolo llenar_vector_vacio(Tsimbolo vector, int numero){
        int valor = 0;
        while(valor!=numero){
            String nombre = vector.getNombre()+"_"+valor;
            Tsimbolo nuevo = new Tsimbolo(nombre, "VARIABLE");
            nuevo.setAmbito(vector.getAmbito());
            vector.addVector(nuevo);
            valor = valor + 1;
        }
        return vector;
    }
    
    public Tsimbolo metodo_vector(int ambito, boolean estado, Nodo raiz){
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo temp2 = buscar_variable(estado, raiz.getValor(), "VECTOR");
        if(temp2 != null){
             Tsimbolo indice = expresiones(raiz.getHijos().get(0), estado);
             Tsimbolo igual = expresiones(raiz.getHijos().get(1), estado);
             if(!indice.getTipo_valor().equalsIgnoreCase("ERROR") && 
                     !igual.getTipo_valor().equalsIgnoreCase("ERROR")){
                if(indice.getTipo_valor().equalsIgnoreCase("NUMERO")){
                    //double b
                    Double d1 = Double.parseDouble(indice.getValor());
                    int n = (int) d1.doubleValue();
                    String nombre = raiz.getValor()+"_"+n;
                    Tsimbolo hijo = buscar_vector(temp2, nombre);
                    if(hijo!=null){
                        hijo.setValor(igual.getValor());
                        hijo.setTipo_valor(igual.getTipo_valor());
                        temp.setTipo_valor("correcto");
                        return temp;
                    }else{
                        Hay_error("El limete del vector es incorrecto");
                        temp.setTipo_valor("ERROR");
                        return temp;
                    }
                 }else{
                     Hay_error("El tipo de indice del vector no es un numero");
                     temp.setTipo_valor("ERROR");
                     return temp;
                 }
             }else{
                 Hay_error("Error en indice o en asignacion revisar vector: "+raiz.getNombre());
                 temp.setTipo_valor("ERROR");
                 return temp;
             }
        }else{
            Hay_error("No exite el vector");
            temp.setTipo_valor("ERROR");
            return temp;
        } 
    }
    
    public Tsimbolo buscar_vector(Tsimbolo vector, String nombre){
        for(Tsimbolo hijo : vector.getVector()){
            if(hijo.getNombre().equalsIgnoreCase(nombre)){
                return hijo;
            }
        }
        return null;
    }
    
    public Tsimbolo llenar_vector(Tsimbolo vector, boolean estado, Nodo raiz){
        Tsimbolo temp = new Tsimbolo("", "");
        String nombre = vector.getNombre();
        int ambito = vector.getAmbito();
        int contador = 0;
        for(Nodo hijo : raiz.getHijos()){ 
           Tsimbolo tb = expresiones(hijo, estado);
           if(tb.getTipo_valor().equalsIgnoreCase("ERROR")){
               temp.setTipo_valor("ERROR");
               return temp;
           }else{
               tb.setNombre(nombre+"_"+contador);
               tb.setAmbito(ambito);
               tb.setTipo_valor(tb.getTipo_valor());
               vector.addVector(tb);
           }
           contador = contador + 1;
        }
        return vector;
    }
    
    public void Hay_error(String error){
        error_cjs = error_cjs + " Tipo de errror semantico: "+error+"\n"; 
    }
    
    public Tsimbolo expresiones(Nodo raiz, boolean estado){
        Tsimbolo temp = new Tsimbolo("", "");
        switch(raiz.getHijos().size()){
            case 2:
                Tsimbolo izq = expresiones(raiz.getHijos().get(0), estado);
                Tsimbolo der = expresiones(raiz.getHijos().get(1), estado);
                switch(raiz.getNombre()){
                    case "SUMA":  
                        return casos_sumas(izq, der);
                    case "RESTA":
                        return casos_RESTA(izq, der);
                    case "POTENCIA":
                        return casos_potencia(izq, der);
                    case "MULTIPLICACION":
                        return casos_multiplicacion(izq, der);
                    case "DIVISION":
                        return casos_division(izq, der);
                    case "IGUAL":
                        return caso_igual(izq, der);
                    case "DIFERENTE":
                        return caso_diferente(izq, der);
                    case "MENORQ":
                        return caso_menorq(izq, der);
                    case "MAYORQ":
                        return caso_mayorq(izq, der);
                    case "MAYORY":
                        return caso_mayory(izq, der);
                    case "MENORY":
                        return caso_menory(izq, der);
                    case "OR":
                        return caso_or(izq, der);
                    case "AND":
                        return caso_and(izq, der);
                    case "MODULO":
                        return caso_modulo(izq, der);
                }
            case 1:
            Tsimbolo cen = expresiones(raiz.getHijos().get(0),estado);
                switch(raiz.getNombre()){
                    case "NOT":
                        return caso_not(cen);
                    case "ADICION":
                        return caso_adicion(cen);
                    case "SUSTRACCION":
                        return caso_sustraccion(cen);
                }
            case 0:
                switch(raiz.getNombre()){
                    case "NUMERO":
                        temp.setTipo_valor("NUMERO");
                        temp.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp;
                    case "CADENA":
                        Tsimbolo temp1 = new Tsimbolo("","");
                        temp1.setTipo_valor("CADENA");
                        temp1.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp1;
                    case "DATE":
                        Tsimbolo temp2 = new Tsimbolo("","");
                        temp2.setTipo_valor("DATE");
                        temp2.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp2;
                    case "DATATIME":
                        Tsimbolo temp3 = new Tsimbolo("","");
                        temp3.setTipo_valor("DATETIME");
                        temp3.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp3;
                    case "BOOL":
                        Tsimbolo temp4 = new Tsimbolo("","");
                        temp4.setTipo_valor("BOOL");
                        temp4.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp4;
                    case "ID":
                        temp = buscar_id(estado, raiz.getValor());
                        if("".equals(temp.getTipo_valor()) && "VARIABLE".equalsIgnoreCase(temp.getTipo())){
                            temp.setTipo_valor("ERROR");
                            return temp; 
                        }
                        return temp;
                    case "VECTOR":
                        temp = devolver_valor_vector(estado, raiz);
                        return temp;
                    case "LLAMADA":
                        temp = llamada_funcion(raiz, estado);
                        if(temp.getRb().equalsIgnoreCase("RETURN")){
                            temp.setRb("");
                        }else{
                            Hay_error("La funcion no contiene return");
                            temp.setTipo_valor("ERROR");
                        }
                        return temp;
                }
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo devolver_valor_vector( boolean estado, Nodo raiz){
        Tsimbolo temp;
        temp = buscar_variable(estado,raiz.getValor(), "VECTOR");
        if(temp != null){
            Tsimbolo indice = expresiones(raiz.getHijos().get(0), estado);
            if(indice.getTipo_valor().equalsIgnoreCase("NUMERO")){
                Double d1 = Double.parseDouble(indice.getValor());
                int n = (int) d1.doubleValue();
                String nombre = raiz.getValor()+"_"+n;
                Tsimbolo hijo = buscar_vector(temp, nombre);
                if(hijo!=null){
                    return hijo;
                }
            }
        }
        Tsimbolo temp1 = new Tsimbolo("","");
        temp1.setTipo_valor("ERROR");
        return temp1; 
    }
    
    public Tsimbolo buscar_id(boolean estado, String nombre){
        Tsimbolo temp;
        temp = buscar_variable(estado,nombre, "VARIABLE");
        if(temp != null){
            return temp; }
        temp = buscar_variable(estado,nombre, "VECTOR");
        if(temp != null){
            return temp;
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo casos_sumas(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 = Double.parseDouble(izq.getValor());
                   n2 = Double.parseDouble(der.getValor());
                   n3 = n1 + n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 =  Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 + n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    b3 = b1 || b2;
                    temp.setValor(bol_str(b3));
                    temp.setTipo_valor("BOOL");
                    return temp;
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 =  Double.parseDouble(der.getValor());
                    n3 = n1 + n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                 case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                 case "NUMERO":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                 case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                 case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                 case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp; 
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp; 
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp; 
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Double bol_int(boolean valor){
        if(valor){
            return 1.0;
        }else{
            return 0.0;
        }
        
    }
    
    public String  bol_str(boolean valor){
         if(valor){
            return "true";
        }else{
            return "false";
        }
    }
    
    int bol_int_str(String valor){
        if(valor.equalsIgnoreCase("TRUE")){
            return 1;
        } return 0;
    }
    
    public Tsimbolo Agregar_vectores_hijos(Tsimbolo vector,Tsimbolo nuevo,int ambito){
        int numero = 0;
        for(Tsimbolo hijo : vector.getVector()){
            String nombre = nuevo.getNombre() +"_"+numero;
            Tsimbolo temp = new Tsimbolo(nombre,"VARIABLE");
            temp.setAmbito(ambito);
            temp.setValor(hijo.getValor());
            temp.setTipo_valor(hijo.getTipo_valor());
            nuevo.addVector(temp);
            numero = numero + 1;
        }
        return nuevo;
    }
     
    public void imprimir(String i){
        System.out.println(i);
    }
    
    public Tsimbolo casos_RESTA(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   n3 = n1 - n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 =  Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 - n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 - n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo casos_multiplicacion(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   n3 = n1 * n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 * n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    b3 = b1 && b2;                 
                    temp.setValor(bol_str(b3));
                    temp.setTipo_valor("BOOL");
                    return temp;
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 * n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo casos_division(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 = Double.parseDouble(izq.getValor());
                   n2 = Double.parseDouble(der.getValor());
                   n3 = n1 / n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 / n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 / n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo casos_potencia(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   n3 = (double)Math.pow(n1, n2);
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = (double)Math.pow(n1, n2);
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = (double)Math.pow(n1, n2);
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo caso_igual(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   if(n1==n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "CADENA":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = der.getValor().length();
                    if(n1==n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    if(n1==n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n1 = bol_int(b1);
                    if(n1==n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n1 = Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                     if(n1==n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = der.getValor();
                    v2 = izq.getValor();
                     if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n2 = Double.parseDouble(izq.getValor());
                    n1 = der.getValor().length();
                    if(n1==n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }
                case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   } 
                case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo caso_diferente(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   if(n1==n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "CADENA":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = der.getValor().length();
                    if(n1==n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    if(n1==n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n1 = bol_int(b1);
                    if(n1==n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n1 = Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                     if(n1==n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = der.getValor();
                    v2 = izq.getValor();
                     if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n2 = Double.parseDouble(izq.getValor());
                    n1 = der.getValor().length();
                    if(n1==n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }
                case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   } 
                case "DATETIME":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    if(v1.equalsIgnoreCase(v2)){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo caso_mayorq(Tsimbolo izq, Tsimbolo der){
        double n1 , n2;
        String v1, v2;
        boolean b1, b2, b3;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    if(n1>n2){
                        temp.setValor("TRUE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }else{
                        temp.setValor("FALSE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }
                case "CADENA":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = der.getValor().length();
                    if(n1>n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    if(n1>n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n1 = bol_int(b1);
                    if(n1>n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n1 = Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                     if(n1>n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = der.getValor();
                    v2 = izq.getValor();
                    n1 = v1.length();
                    n2 = v2.length();
                    if(n1>n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n2 = Double.parseDouble(izq.getValor());
                    n1 = der.getValor().length();
                    if(n1>n2){
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    } 
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }          
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v1 = v1 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v2 = v2 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }
        temp.setTipo_valor("ERROR");
        return temp;
    }
    
    public Tsimbolo caso_menorq(Tsimbolo izq, Tsimbolo der){
        double n1 , n2;
        String v1, v2;
        boolean b1, b2, b3;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    if(n1>n2){
                        temp.setValor("FALSE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }else{
                        temp.setValor("TRUE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }
                case "CADENA":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = der.getValor().length();
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n1 = bol_int(b1);
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n1 = Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                     if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = der.getValor();
                    v2 = izq.getValor();
                    n1 = v1.length();
                    n2 = v2.length();
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n2 = Double.parseDouble(izq.getValor());
                    n1 = der.getValor().length();
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    } 
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }          
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v1 = v1 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v2 = v2 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }
        temp.setTipo_valor("ERROR");
        return temp;
    }
    
    public Tsimbolo caso_mayory(Tsimbolo izq, Tsimbolo der){
        double n1 , n2;
        String v1, v2;
        boolean b1, b2, b3;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    if(n1<n2){
                        temp.setValor("FALSE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }else{
                        temp.setValor("TRUE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }
                case "CADENA":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = der.getValor().length();
                    if(n1<n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    if(n1<n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n1 = bol_int(b1);
                    if(n1<n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n1 = Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                     if(n1<n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = der.getValor();
                    v2 = izq.getValor();
                    n1 = v1.length();
                    n2 = v2.length();
                    if(n1<n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n2 = Double.parseDouble(izq.getValor());
                    n1 = der.getValor().length();
                    if(n1<n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    } 
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }          
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v1 = v1 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v2 = v2 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.before(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1<n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }
        temp.setTipo_valor("ERROR");
        return temp;
    }
    
    public Tsimbolo caso_menory(Tsimbolo izq, Tsimbolo der){
        double n1 , n2;
        String v1, v2;
        boolean b1, b2, b3;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    if(n1>n2){
                        temp.setValor("FALSE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }else{
                        temp.setValor("TRUE");
                        temp.setTipo_valor("BOOL");
                        return temp;
                    }
                case "CADENA":
                    n1 = Double.parseDouble(izq.getValor());
                    n2 = der.getValor().length();
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n1 = bol_int(b1);
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n1 = Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                     if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1 = der.getValor();
                    v2 = izq.getValor();
                    n1 = v1.length();
                    n2 = v2.length();
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                    }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "NUMERO":
                    n2 = Double.parseDouble(izq.getValor());
                    n1 = der.getValor().length();
                    if(n1>n2){
                       temp.setValor("FALSE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }else{
                       temp.setValor("TRUE");
                       temp.setTipo_valor("BOOL");
                       return temp; 
                   }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    } 
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }          
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATE")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf1.parse(v1);
                        Date date2 = sdf1.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v1 = v1 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("DATETIME")){
            switch(der.getTipo_valor()){
                case "CADENA":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATETIME":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{                  
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
                case "DATE":
                    v1=izq.getValor();
                    v2=der.getValor();
                    try{
                        v2 = v2 + " 00:00:00";
                        Date date1 = sdf2.parse(v1);
                        Date date2 = sdf2.parse(v2);
                        if(date1.after(date2)==true){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }catch (ParseException ex) {
                         n1 = v1.length();
                         n2 = v2.length();
                         if(n1>n2){
                            temp.setValor("FALSE");
                            temp.setTipo_valor("BOOL");
                            return temp;
                        }else{
                            temp.setValor("TRUE");
                            temp.setTipo_valor("BOOL");
                            return temp; 
                        }
                    }
            }
        }
        temp.setTipo_valor("ERROR");
        return temp;
    }
    
    public Tsimbolo caso_and(Tsimbolo izq, Tsimbolo der){
        Tsimbolo temp = new Tsimbolo("", "");
        boolean b1, b2, b3;
        if(izq.getTipo_valor().equalsIgnoreCase(der.getTipo_valor())){
            if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
                b1 = Boolean.parseBoolean(izq.getValor());
                b2 = Boolean.parseBoolean(der.getValor());
                b3 = b1 && b2;                    
                temp.setValor(bol_str(b3));
                temp.setTipo_valor("BOOL");
                return temp;
            }
        }
        temp.setTipo_valor("ERROR");
        return temp;
    }
    
    public Tsimbolo caso_or(Tsimbolo izq, Tsimbolo der){
        Tsimbolo temp = new Tsimbolo("", "");
        boolean b1, b2, b3;
        if(izq.getTipo_valor().equalsIgnoreCase(der.getTipo_valor())){
            if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
                b1 = Boolean.parseBoolean(izq.getValor());
                b2 = Boolean.parseBoolean(der.getValor());
                b3 = b1 || b2;                    
                temp.setValor(bol_str(b3));
                temp.setTipo_valor("BOOL");
                return temp;
            }
        }
        temp.setTipo_valor("ERROR");
        return temp;
    }
    
    public Tsimbolo caso_modulo(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   n3 = n1 % n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 % n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 % n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo caso_not(Tsimbolo uno){
        Tsimbolo temp = new Tsimbolo("", "");
        if(uno.getTipo_valor().equalsIgnoreCase("BOOL")){
            if(uno.getValor().equalsIgnoreCase("TRUE")){
                temp.setValor("FALSE");
                temp.setTipo_valor("NUMERO");
                return temp;
            }else{
                temp.setValor("TRUE");
                temp.setTipo_valor("NUMERO");
                return temp;
            }
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo caso_adicion(Tsimbolo uno){
        Tsimbolo temp = new Tsimbolo("", "");
        if(uno.getTipo_valor().equalsIgnoreCase("NUMERO")){
            double n1 = Double.parseDouble(uno.getValor());
            n1 = n1 + 1;
            temp.setValor(String.valueOf(n1));
            temp.setTipo_valor("NUMERO");
            return temp;
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo caso_sustraccion(Tsimbolo uno){
        Tsimbolo temp = new Tsimbolo("", "");
        if(uno.getTipo_valor().equalsIgnoreCase("NUMERO")){
            double n1 = Double.parseDouble(uno.getValor());
            n1 = n1 - 1;
            temp.setValor(String.valueOf(n1));
            temp.setTipo_valor("NUMERO");
            return temp;
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public void consola_tsimbolo(Tsimbolo raiz){
        System.out.println("----------------------------");
        System.out.println("nombre: "+raiz.getNombre());
        System.out.println("valor: "+raiz.getValor());
        System.out.println("Tipo: "+raiz.getTipo());
        System.out.println("tipo_valor: "+raiz.getTipo_valor());
    }
    
    public Tsimbolo metodo_variable_vector(int ambito, boolean estado, Nodo raiz){
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo temp = new Tsimbolo("","");
        String nombre = raiz.getValor();
        Tsimbolo temp2 = buscar_variable(estado, nombre, "VECTOR");
        if(temp2!=null){
            int n = temp2.getParametros();
            if(n==raiz.getHijos().size()){
                Tsimbolo nuevo = pasar_vector(temp2, raiz,estado);
                if(!temp.getTipo_valor().equalsIgnoreCase("ERROR")){
                    temp.setTipo_valor("CORRECTO");
                    return temp;
                }else{
                    temp.setTipo_valor("ERROR");
                    return temp;
                }     
            }else{
                Hay_error("Cantidad parametros incorrectos");
                temp.setTipo_valor("ERROR");
                return temp;
            }
        }else{
            Hay_error("No se encontro el vecto");
            temp.setTipo_valor("ERROR");
            return temp;
        }
    }
     
    public Tsimbolo pasar_vector(Tsimbolo simbolo, Nodo raiz,boolean estado){
        int numero = 0;
        Tsimbolo temp = new Tsimbolo("","");
        for(Nodo hijo : raiz.getHijos()){
            Tsimbolo temp1 = expresiones(hijo, estado);
            if(temp1.getValor().equalsIgnoreCase("ERROR") && temp1.getTipo().equalsIgnoreCase("VECTOR")){
                Hay_error("Error exp vector raiz");
                temp.setTipo_valor("ERROR");
                return temp;
            }else{
               Tsimbolo nuevo = simbolo.getVector().get(numero);
               nuevo.setValor(temp1.getValor());
               nuevo.setTipo_valor(temp1.getTipo_valor());
            }
            numero = numero + 1;
        }
        return simbolo;
    }
      
    public Tsimbolo metodo_si(boolean estado, Nodo raiz, int ambito){
        ambito = ambito + 1;
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
        if(exp.getValor().equalsIgnoreCase("TRUE")){
            Tsimbolo nuevo = inicio_cjs(raiz, estado, ambito);
            Sacar_ambito(ambito, estado);
            return nuevo;
        }else if(exp.getValor().equalsIgnoreCase("FALSE")){
            int n = raiz.getHijos().size()-1;
            Nodo sino = raiz.getHijos().get(n);
            if(sino.getNombre().equalsIgnoreCase("SINO")){
                Tsimbolo nuevo = inicio_cjs(sino, estado, ambito);
                Sacar_ambito(ambito, estado);
                return nuevo;
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp;
        }
        return temp;
    }
    
    public void Sacar_ambito(int ambito, boolean estado ){
        Tsimbolo actual = pila_tb.peek();
        if(estado){
            int numero=global.getVector().size()-1;
            for (int i = numero; i >= 0; i--) {
                 if(global.getVector().get(i).getAmbito()==ambito){
                      global.getVector().remove(i);
                 }
            }
        }else{
            int numero=actual.getVector().size()-1;   
            for (int i = numero; i >= 0; i--) {
                 if(actual.getVector().get(i).getAmbito()==ambito){
                      actual.getVector().remove(i);
                 }
            }
        }
    }
    
    public Tsimbolo metodo_mientras(boolean estado, Nodo raiz, int ambito){
        ambito = ambito + 1;
        Tsimbolo temp = new Tsimbolo("","");
        Tsimbolo actual = pila_tb.peek();
        Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
        while(exp.getValor().equalsIgnoreCase("TRUE")){
            Tsimbolo nuevo = inicio_cjs(raiz, estado, ambito);
            exp = expresiones(raiz.getHijos().get(0), estado);
            Sacar_ambito(ambito, estado);
        }
        return null;
    }
    
    public Tsimbolo metodo_crear_funcion(boolean estado, Nodo raiz, int ambito){
        Tsimbolo temp = new Tsimbolo("","");
        int parametros = raiz.getHijos().get(0).getHijos().size();
        String nombre = raiz.getValor();
        Tsimbolo funcion = buscar_funcion(nombre, parametros);
        if(funcion==null){
            Tsimbolo nueva = new Tsimbolo(nombre, "FUNCION");
            nueva.setParametros(parametros);
            nueva.setRaiz(raiz);
            global.addVector(nueva);
            temp.setTipo_valor("CORRECTO");
            return temp;
        }else{
            Hay_error("La funcion ya existe "+nombre);
            temp.setTipo_valor("ERROR");
            return temp;
        }
    }
    
    public Tsimbolo buscar_funcion(String nombre, int parametros){
        for(Tsimbolo hijo : global.getVector()){
             if(hijo.getNombre().equalsIgnoreCase(nombre) && hijo.getTipo().equalsIgnoreCase("FUNCION")
                     && hijo.getParametros() == parametros){ 
                        return hijo; 
             }
       }
        return null;
    }
    
    public Tsimbolo llamada_funcion( Nodo raiz, boolean estado){
        int ambito = 0; //iniciamos ambito
        int parametros = raiz.getHijos().get(0).getHijos().size(); //calculamos el numero de parametros
        Tsimbolo buscar = buscar_funcion(raiz.getValor(), parametros);//buscamos funcion con parametros
        Tsimbolo temp = new Tsimbolo("","");//creamos una tb simbolos temporal
        if(buscar!=null){
            Tsimbolo nueva = new Tsimbolo(raiz.getValor(),"FUNCION"); //creamos funcion
            nueva = crear_parametros(nueva, raiz.getHijos().get(0), buscar.getRaiz(), estado);
            if(nueva!= null){
                pila_tb.add(nueva);
                Tsimbolo result = inicio_cjs(buscar.getRaiz(), false, 0);
                pila_tb.pop();
                return result;
            }else{
                Hay_error("Error en parametros "+raiz.getNombre());
                temp.setTipo_valor("ERROR");
                return temp;
            }
        }else{
            Hay_error("La funcion no existe "+raiz.getNombre());
            temp.setTipo_valor("ERROR");
            return temp; 
        }
        
    }
   
    public Tsimbolo crear_parametros(Tsimbolo nueva, Nodo parametros, Nodo funcion,  boolean estado){
        int n = 0;
        for(Nodo hijos : parametros.getHijos()){
            Tsimbolo exp = expresiones(hijos, estado);
            String nombre = funcion.getHijos().get(0).getHijos().get(n).getNombre();
            if(exp.getTipo_valor().equalsIgnoreCase("ERROR")){
                return null;
            }else if(exp.getTipo().equalsIgnoreCase("VECTOR")) {  
                Tsimbolo hoja = new Tsimbolo(nombre, "VECTOR");
                hoja = Vector_tipo_parametro(exp, hoja);
                nueva.addVector(hoja);
            }else{
                Tsimbolo hoja = new Tsimbolo(nombre, "VARIABLE");
                hoja.setTipo_valor(exp.getTipo_valor());
                hoja.setValor(exp.getValor());
                nueva.addVector(hoja);
            }
            n = n +1 ;
        }
        return nueva;
    }
    
    public Tsimbolo Vector_tipo_parametro(Tsimbolo vector, Tsimbolo agregar){
        int n = 0;
        for(Tsimbolo hijo : vector.getVector()){
            String nombre = agregar.getNombre()+"_"+n;
            Tsimbolo nueva = new Tsimbolo(nombre,"VARIABLE");
            nueva.setValor(hijo.getValor());
            nueva.setTipo_valor(hijo.getTipo_valor());
            agregar.addVector(nueva);
            n = n + 1;
        }
        return agregar;
    }
    
    public Tsimbolo metodo_retonar(Nodo raiz, boolean estado){
        Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
        Tsimbolo temp = new Tsimbolo("","");//creamos una tb simbolos temporal
        if(!exp.getTipo_valor().equalsIgnoreCase("ERROR")){
                exp.setRb("RETURN");
                return exp;
        }else{
            Hay_error("Hay error en variable retornar");
            temp.setRb("RETURN");
            temp.setTipo_valor("ERROR");
            return temp;
        }
    }
    
    public void mesaje(Nodo raiz, boolean estado){
        Tsimbolo exp = expresiones(raiz.getHijos().get(0), estado);
        JOptionPane.showMessageDialog(null, exp.getValor(), "Soy un Mensaje", JOptionPane.WARNING_MESSAGE);
    }
    
}

