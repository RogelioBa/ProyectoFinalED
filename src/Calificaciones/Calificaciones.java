/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calificaciones;

import Excepciones.DinamicListException;

/**
 * Maneja un arreglo dinámico de calificaciones.
 * Permite insertar, eliminar, reemplazar y obtener valores, simulando el comportamiento de un vector dinámico.
 * @author axelm
 */
public class Calificaciones {
    private float[] calificaciones;
    private int numCalificaciones;   

    /**
     * Inicializa la estructura con un arreglo vacío.
     */
    public Calificaciones(){
        calificaciones = new float[0];
        numCalificaciones = 0;
    }

    /**
     * Agrega una nueva calificación al final del arreglo.
     *
     * @param calif valor de la calificación
     */
    public void append(float calif){
        float [] nuevasCalifs = new float[numCalificaciones + 1];
        System.arraycopy(calificaciones, 0, nuevasCalifs, 0, numCalificaciones);
        nuevasCalifs[numCalificaciones] = calif;
        calificaciones = nuevasCalifs;
        numCalificaciones++;
    }

    /**
     * Inserta una calificación en una posición específica,
     * recorriendo los elementos restantes.
     *
     * @param i índice donde insertar
     * @param calif valor a insertar
     * @throws DinamicListException si el índice está fuera de rango
     */
    public void insert(int i, float calif) throws DinamicListException{
        if(i > numCalificaciones || i < 0){
            throw new DinamicListException("El indice esta fuera de rango");
        }
        numCalificaciones++;
        float[] nuevasCalifs = new float[numCalificaciones];
        for(int k = 0; k < numCalificaciones; k++){
            if(k < i){
                nuevasCalifs[k] = calificaciones[k];
            }
            if(k == i){
                nuevasCalifs[k] = calif;
            }
            if(k > i){
                nuevasCalifs[k] = calificaciones[k - 1];
            }
        }
        calificaciones = nuevasCalifs;
    }

    /**
     * Reemplaza el valor de un índice existente.
     *
     * @param i índice a modificar
     * @param calif nuevo valor
     * @throws DinamicListException si el índice es inválido o lista vacía
     */
    public void replace(int i, float calif) throws DinamicListException{
        if(empty()){
            throw new DinamicListException("La lista de calificaciones esta vacia");
        }
        if(i >= numCalificaciones || i < 0){
            throw new DinamicListException("El indice esta fuera de rango");
        }
        calificaciones[i] = calif;
    }

    /**
     * Elimina la calificación en un índice y recorre el arreglo.
     *
     * @param i índice a eliminar
     * @throws DinamicListException si está vacío o fuera de rango
     */
    public void delete(int i) throws DinamicListException{
        if(empty()){
            throw new DinamicListException("La lista de calificaciones esta vacia");
        }
        if(i >= numCalificaciones || i < 0){
            throw new DinamicListException("El indice esta fuera de rango");
        }
        float[] nuevasCalifs = new float[numCalificaciones - 1];
        int index = 0;
        for(int k = 0; k < numCalificaciones; k++){
            if(k != i){
                nuevasCalifs[index] = calificaciones[k];
                index++;
            }
        }
        numCalificaciones--;
        calificaciones = nuevasCalifs;
    }

    /**
     * Obtiene la calificación en un índice específico.
     *
     * @param i índice solicitado
     * @return valor de la calificación
     * @throws DinamicListException si está vacío o fuera de rango
     */
    public float get(int i) throws DinamicListException{
        if(empty()){
            throw new DinamicListException("La lista esta vacia");
        }
        if(i >= numCalificaciones || i < 0){
            throw new DinamicListException("El indice esta fuera de rango");
        }
        return calificaciones[i];
    }

    /**
     * Verifica si el arreglo está vacío.
     * @return true si está vacio, false si no
     */
    public boolean empty(){
        return numCalificaciones == 0;
    }

    /**
     * Retorna el número de elementos almacenados.
     * @return la cantidad de calificaciones guardadas
     */
    public int getSize(){
        return this.numCalificaciones;
    }
}