/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proye1_compi2018;

import java.util.ArrayList;

public class listahtml {
    
    private String nombre;
    private String id;
    private String tipo;
    private ArrayList<listahtml> siguiente;
    private Object instancia;
    private String grupo;
    private String funcion;

    public listahtml(String nombre, String tipo, Object instancia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.instancia = instancia;
        this.siguiente = new ArrayList<>();
        this.id = "";
        this.grupo = "";
        this.funcion = "";
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public listahtml(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.instancia = null;
        this.siguiente = new ArrayList<>();
        this.nombre = "";
        this.grupo = "";
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    public void addsiguiente(listahtml hijo)
    {
        siguiente.add(hijo);
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSiguiente(ArrayList<listahtml> siguiente) {
        this.siguiente = siguiente;
    }

    public void setInstancia(Object instancia) {
        this.instancia = instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<listahtml> getSiguiente() {
        return siguiente;
    }

    public Object getInstancia() {
        return instancia;
    }
    
    
    
}
