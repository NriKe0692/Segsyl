package com.enrique.segsyl.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by USUARIO on 11/11/2016.
 */

public class Alumno implements Parcelable{
    private String codigo;
    private String password;
    private String nombre;
    private String apellidos;
    private ArrayList<Curso> cursos;

    public Alumno(String codigo, String password, String nombre, String apellidos, ArrayList<Curso> cursos) {
        this.codigo = codigo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cursos = cursos;
    }

    protected Alumno(Parcel in) {
        codigo = in.readString();
        password = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        cursos = in.createTypedArrayList(Curso.CREATOR);
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(codigo);
        parcel.writeString(password);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeTypedList(cursos);
    }
}
