/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proye1_compi2018;


public class Complemento {
    private String grupo;
    private boolean b_grupo;
    private String id;
    private boolean b_id;
    private String alineado;
    private boolean b_ali;
    private int alto;
    private boolean b_alto;
    private int ancho;
    private boolean  b_ancho;
    private String fondo;
    private boolean b_fondo;
    private String click;
    
    public Complemento(){
        grupo = "";
        b_grupo = false;
        id = "";
        b_id = false;
        alineado = "";
        b_ali = false;
        alto = 0;
        b_alto= false;
        ancho = 0;
        b_ancho = false;
        this.fondo = "";
        b_fondo = false;
        this.click = "";
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean isB_grupo() {
        return b_grupo;
    }

    public void setB_grupo(boolean b_grupo) {
        this.b_grupo = b_grupo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isB_id() {
        return b_id;
    }

    public void setB_id(boolean b_id) {
        this.b_id = b_id;
    }

    public String getAlineado() {
        return alineado;
    }

    public void setAlineado(String alineado) {
        this.alineado = alineado;
    }

    public boolean isB_ali() {
        return b_ali;
    }

    public void setB_ali(boolean b_ali) {
        this.b_ali = b_ali;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public boolean isB_alto() {
        return b_alto;
    }

    public void setB_alto(boolean b_alto) {
        this.b_alto = b_alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public boolean isB_ancho() {
        return b_ancho;
    }

    public void setB_ancho(boolean b_ancho) {
        this.b_ancho = b_ancho;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public boolean isB_fondo() {
        return b_fondo;
    }

    public void setB_fondo(boolean b_fondo) {
        this.b_fondo = b_fondo;
    }
    
}
