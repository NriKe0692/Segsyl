package com.enrique.segsyl.Models;

import java.io.Serializable;

/**
 * Created by USUARIO on 26/11/2016.
 */

public class Tema implements Serializable{
    String nombreTema;
    boolean verificado;

    public Tema(String nombreTema, boolean verificado) {
        this.nombreTema = nombreTema;
        this.verificado = verificado;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}
