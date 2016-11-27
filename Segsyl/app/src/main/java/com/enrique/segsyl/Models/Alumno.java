package com.enrique.segsyl.Models;

/**
 * Created by USUARIO on 26/11/2016.
 */

public class Alumno {
    String username;
    String pass;

    public Alumno(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
