/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Deshacer;

import Excepciones.PilaException;

/**
 * Implementación de una pila para almacenar acciones realizadas
 * en el sistema con el fin de permitir operaciones de deshacer.
 * @author axelm
 */
public class PilaAcciones {
    private Nodo cima;
    private int size;

    /**
     * Crea una pila vacía.
     */
    public PilaAcciones(){
        cima = null;
        size = 0;
    }

    /**
     * Nodo interno de la pila.
     */
    class Nodo{
        private Nodo siguiente;
        private Accion accion;

        public Nodo(Accion dato){
            this.accion = dato;
        }

        public void setAccion(Accion accion){
            this.accion = accion;
        }

        public Accion getAccion(){
            return this.accion;
        }

        public void setSiguiente(Nodo siguiente){
            this.siguiente = siguiente;
        }

        public Nodo getSiguiente(){
            return this.siguiente;
        }
    }

    /**
     * Inserta una acción en la cima de la pila.
     *
     * @param accion acción a almacenar
     */
    public void push(Accion accion){
        Nodo nuevo = new Nodo(accion);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        size++;
    }

    /**
     * Remueve y retorna la acción más reciente.
     *
     * @return la acción en la cima
     * @throws PilaException si la pila está vacía
     */
    public Accion pop() throws PilaException{
        if(empty()){
            throw new PilaException("La pila esta vacia");
        }

        Accion accion = cima.getAccion();
        cima = cima.getSiguiente();
        size--;
        return accion;
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return true si está vacía, false en caso contrario
     */
    public boolean empty(){
        return size == 0;
    }

    /**
     * Retorna el número de elementos almacenados.
     *
     * @return tamaño de la pila
     */
    public int getSize(){
        return size;
    }
}