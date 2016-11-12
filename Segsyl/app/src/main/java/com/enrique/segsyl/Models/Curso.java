package com.enrique.segsyl.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by USUARIO on 11/11/2016.
 */

public class Curso implements Parcelable{
    private String nombre;
    private int numSemana;
    private int ciclo;
    private ArrayList<Tema> temas;

    public Curso(String nombre, int numSemana, int ciclo, ArrayList<Tema> temas) {
        this.nombre = nombre;
        this.numSemana = numSemana;
        this.ciclo = ciclo;
        this.temas = temas;
    }

    protected Curso(Parcel in) {
        nombre = in.readString();
        numSemana = in.readInt();
        ciclo = in.readInt();
        temas = in.createTypedArrayList(Tema.CREATOR);
    }

    public static final Creator<Curso> CREATOR = new Creator<Curso>() {
        @Override
        public Curso createFromParcel(Parcel in) {
            return new Curso(in);
        }

        @Override
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumSemana() {
        return numSemana;
    }

    public void setNumSemana(int numSemana) {
        this.numSemana = numSemana;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeInt(numSemana);
        parcel.writeInt(ciclo);
        parcel.writeTypedList(temas);
    }
}
