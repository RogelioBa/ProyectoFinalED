/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calificaciones;

/**
 * Representa el resultado de procesar una solicitud de calificación.
 * Indica si fue inserción o reemplazo, el valor previo y el índice afectado.
 * @author axelm
 */
public class ResultadoCalificacion {
    private boolean insertado;
    private float valorAnterior;
    private int indiceAfectado;

    public ResultadoCalificacion(boolean insertado, float valorAnterior, int indiceAfectado) {
        this.insertado = insertado;
        this.valorAnterior = valorAnterior;
        this.indiceAfectado = indiceAfectado;
    }

    public boolean fueInsertado() {
        return insertado;
    }

    public float getValorAnterior() {
        return valorAnterior;
    }

    public int getIndiceAfectado() {
        return indiceAfectado;
    }
}