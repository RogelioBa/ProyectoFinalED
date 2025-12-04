/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Solicitudes;

/**
 * Representa una solicitud para agregar o modificar una calificación.
 * Puede incluir un índice (modificación) o no (inserción).
 * @author axelm
 */
public class SolicitudCalificacion {
    private String matricula;
    private float calificacion;
    private Integer indice;

    /**
     * Crea una solicitud de inserción de calificación.
     *
     * @param matricula matrícula del estudiante
     * @param calificacion valor a insertar
     */
    public SolicitudCalificacion(String matricula, float calificacion) {
        this.matricula = matricula;
        this.calificacion = calificacion;
        this.indice = null;
    }

    /**
     * Crea una solicitud de modificación de calificación.
     *
     * @param matricula matrícula del estudiante
     * @param calificacion nuevo valor
     * @param indice posición a modificar
     */
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