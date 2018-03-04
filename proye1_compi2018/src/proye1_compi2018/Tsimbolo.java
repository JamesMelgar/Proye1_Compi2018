
package proye1_compi2018;

import java.util.ArrayList;

public class Tsimbolo {
    private String tipo;
    private String tipo_valor;
    private String nombre;
    private int parametros;
    private String valor;
    private Nodo raiz;
    private int ambito;
    private ArrayList<Tsimbolo> vector;
    private String rb;
    
    public Tsimbolo(String nombre, String tipo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipo_valor = "";
        this.parametros = 0;
        this.valor = "";
        this.raiz = null;
        this.ambito = 0;
        vector = new ArrayList<>();
        this.rb ="";
    }
    
    public Tsimbolo(String nombre, String tipo, int ambito){
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipo_valor = "";
        this.parametros = 0;
        this.valor = "";
        this.raiz = null;
        this.ambito = ambito;
        vector = new ArrayList<>();
        this.rb ="";
    }

    public void setRb(String rb) {
        this.rb = rb;
    }

    public String getRb() {
        return rb;
    }
      
    public void addVector(Tsimbolo hijo)
    {
        vector.add(hijo);
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTipo_valor(String tipo_valor) {
        this.tipo_valor = tipo_valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setParametros(int parametros) {
        this.parametros = parametros;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }

    public void setVector(ArrayList<Tsimbolo> vector) {
        this.vector = vector;
    }
    
  

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public int getAmbito() {
        return ambito;
    }

    public ArrayList<Tsimbolo> getVector() {
        return vector;
    }
  
    public String getTipo_valor() {
        return tipo_valor;
    }

    public int getParametros() {
        return parametros;
    }
}
