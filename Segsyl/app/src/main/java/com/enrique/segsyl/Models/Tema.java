package com.enrique.segsyl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USUARIO on 12/11/2016.
 */

public class Tema implements Parcelable{
    String nombreTema;
    boolean realizado = false;

    public Tema(String nombreTema, boolean realizado) {
        this.nombreTema = nombreTema;
        this.realizado = realizado;
    }

    protected Tema(Parcel in) {
        nombreTema = in.readString();
        realizado = in.readByte() != 0;
    }

    public static final Creator<Tema> CREATOR = new Creator<Tema>() {
        @Override
        public Tema createFromParcel(Parcel in) {
            return new Tema(in);
        }

        @Override
        public Tema[] newArray(int size) {
            return new Tema[size];
        }
    };

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombreTema);
        parcel.writeByte((byte) (realizado ? 1 : 0));
    }
}
