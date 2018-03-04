/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import ccss.AnalizadorLexico_ccss;
import ccss.AnalizadorSintactico_ccss;
import cjs.AnalizadorLexico_cjs;
import cjs.AnalizadorSintactico_cjs;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import proye1_compi2018.Nodo;

/**
 *
 * @author James_PC
 */
public class Analizadores {

    AnalizadorLexico_cjs lexico_cjs;
    AnalizadorSintactico_cjs sintactico_cjs;
    AnalizadorLexico_ccss lexico_ccss;
    AnalizadorSintactico_ccss sintactico_ccss;
    public String error_ccss = "";
    public String error_cjs = "";
    public Analizadores(){
        
    }
    
    public Nodo Analizar_ccss(String entrada){
        lexico_ccss = new AnalizadorLexico_ccss(new BufferedReader( new StringReader(entrada)));
        sintactico_ccss = new AnalizadorSintactico_ccss(lexico_ccss);    
        try{ 
           sintactico_ccss.parse();
            error_ccss = lexico_ccss.Cerror;
            error_ccss = error_ccss + sintactico_ccss.verror;
            return sintactico_ccss.padre;
        }catch(Exception ex){
            System.out.println("Error "+ex);  
            return null;
        }
    }
    
    public void graficar_ccss(){
        if(sintactico_ccss != null){
            if(sintactico_ccss.padre != null){
                graficar(sintactico_ccss.padre);
                System.out.println("Se ha graficado con exito");
            }
        }
        else
            System.out.println("No se puede graficar!!!");
    }
    
    public Nodo Analizar_cjs(String entrada){
        lexico_cjs = new AnalizadorLexico_cjs(new BufferedReader( new StringReader(entrada)));
        sintactico_cjs = new AnalizadorSintactico_cjs(lexico_cjs);
        
        try{ sintactico_cjs.parse();
            error_cjs = lexico_cjs.Cerror;
            error_cjs = error_cjs + sintactico_cjs.verror;
            return sintactico_cjs.padre;
        }catch(Exception ex){
            System.out.println("Error "+ex);  }
        return null;
    }
     
    public void graficar_cjs(){
        if(sintactico_cjs != null){
            if(sintactico_cjs.padre != null){
                graficar(sintactico_cjs.padre);
                System.out.println("Se ha graficado con exito");
            }
        }
        else
            System.out.println("No se puede graficar!!!");
    }
    
    public void graficar(Nodo raiz){
        FileWriter archivo = null;
        PrintWriter pw = null;
        String cadena = graficarNodo(raiz);
        
        try{
            archivo = new FileWriter("arbol.dot");
            pw = new PrintWriter(archivo);
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            archivo.close();
        }catch (Exception e) {
            System.out.println(e +" 1");
        }
        
        try {
            String cmd = "dot -Tpng arbol.dot -o arbol.png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe +" 2");
        }
        
    }
    
    public String graficarNodo(Nodo nodo){
        String cadena = "";
        for(Nodo hijos : nodo.getHijos())
        {
            cadena += "\"" + nodo.getNumNodo() + "_" + nodo.getNombre() + "=" + nodo.getValor() + "\"->\"" + hijos.getNumNodo() + "_" + hijos.getNombre() + "=" + hijos.getValor() + "\"";
            cadena += graficarNodo(hijos);
        }
        return cadena;
    }
    
    public String muestratexto(String archivo) {
      String cadena;
      String texto=""; 
      try {
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
              texto = texto + "\n" +cadena; 
        }
      }catch(Exception ex){
          
      }
            return texto;
    }

    public void grafica_arbol(String nombre, Nodo raiz){
        FileWriter archivo = null;
        PrintWriter pw = null;
        String cadena = graficarNodo(raiz);
        
        try{
            archivo = new FileWriter(nombre+".dot");
            pw = new PrintWriter(archivo);
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            archivo.close();
        }catch (Exception e) {
            System.out.println(e +" 1");
        }
        
        try {
            String cmd = "dot -Tpng "+ nombre +".dot -o " + nombre + ".png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe +" 2");
        }     
    }
}
