package com.enrique.segsyl.Models;

import java.util.ArrayList;

/**
 * Created by USUARIO on 26/11/2016.
 */

public class CursosResponse {

    ArrayList<Semanas> semanas;

    public class Semanas{
        int numero;
        String fechaInicio;
        String fechaFin;
        ArrayList<Asignaturas> asignaturas;

        public class Asignaturas{
            int grupoId;
            String asignaturaNombre;
            boolean validado;
            String fechaInicio;
            String fechaFin;
            String tipoClase;
            int sesionId;
            int numeroGrupo;

            public int getNumeroGrupo() {
                return numeroGrupo;
            }

            public void setNumeroGrupo(int numeroGrupo) {
                this.numeroGrupo = numeroGrupo;
            }

            public int getSessionId() {
                return sesionId;
            }

            public void setSessionId(int sssionId) {
                this.sesionId = sesionId;
            }

            public String getTipoClase() {
                return tipoClase;
            }

            public void setTipoClase(String tipoClase) {
                this.tipoClase = tipoClase;
            }

            public String getFechaInicio() {
                return fechaInicio;
            }

            public void setFechaInicio(String fechaInicio) {
                this.fechaInicio = fechaInicio;
            }

            public String getFechaFin() {
                return fechaFin;
            }

            public void setFechaFin(String fechaFin) {
                this.fechaFin = fechaFin;
            }

            public int getGrupoId() {
                return grupoId;
            }

            public void setGrupoId(int grupoId) {
                this.grupoId = grupoId;
            }

            public String getAsignaturaNombre() {
                return asignaturaNombre;
            }

            public void setAsignaturaNombre(String asignaturaNombre) {
                this.asignaturaNombre = asignaturaNombre;
            }

            public boolean isValidado() {
                return validado;
            }

            public void setValidado(boolean validado) {
                this.validado = validado;
            }
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public String getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(String fechaFin) {
            this.fechaFin = fechaFin;
        }

        public ArrayList<Asignaturas> getAsignaturas() {
            return asignaturas;
        }

        public void setAsignaturas(ArrayList<Asignaturas> asignaturas) {
            this.asignaturas = asignaturas;
        }
    }

    public ArrayList<Semanas> getSemanas() {
        return semanas;
    }

    public void setSemanas(ArrayList<Semanas> semanas) {
        this.semanas = semanas;
    }
}
