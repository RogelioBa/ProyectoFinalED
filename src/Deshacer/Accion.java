/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Deshacer;

/**
 * Representa una acción realizada dentro del sistema,
 * necesaria para permitir la operación de deshacer.
 * Puede guardar el valor previo y el valor nuevo.
 * @author axelm
 */
public class Accion {
    /**
     * Tipos de acciones que pueden registrarse.
     */
    public enum Tipo {
        REGISTRO, INSCRIPCION, BAJA, CALIFICACION
    }

    private Tipo tipo;
    private Object datoPrevio;
    private Object datoNuevo;

    /**
     * Crea una acción registrada.
     *
     * @param tipo tipo de acción realizada
     * @param datoPrevio valor previo antes de la acción
     * @param datoNuevo valor aplicado después de la acción
     */
    public Accion(Tipo tipo, Object datoPrevio, Object datoNuevo) {
        this.tipo = tipo;
        this.datoPrevio = datoPrevio;
        this.datoNuevo = datoNuevo;
    }

    /**
     * Retorna el tipo de acción.
     *
     * @return tipo de la acción
     */
    public Tipo getTipo() { 
        return tipo; 
    }

    /**
     * Retorna el dato previo antes de la acción.
     *
     * @return valor previo
     */
    public Object getDatoPrevio() { 
        return datoPrevio; 
    }

    /**
     * Retorna el dato nuevo después de la acción.
     *
     * @return valor nuevo
     */
    public Object getDatoNuevo() { 
        return datoNuevo; 
    }
}