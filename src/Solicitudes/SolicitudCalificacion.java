/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Solicitudes;

/**
 *
 * @author axelm
 */
public class SolicitudCalificacion {
    private String matricula;
    private float calificacion;
    private Integer indice;

    public SolicitudCalificacion(String matricula, float calificacion) {
        this.matricula = matricula;
        this.calificacion = calificacion;
        this.indice = null;
    }

    public SolicitudCalificacion(String matricula, float calificacion, int indice) {
        this.matricula = matricula;
        this.calificacion = calificacion;
        this.indice = indice;
    }

    public String getMatricula() {
        return matricula;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public Integer getIndice() {
        return indice;
    }
}