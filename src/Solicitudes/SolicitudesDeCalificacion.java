/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Solicitudes;

import Excepciones.ColaException;

/**
 * Cola genérica para manejar solicitudes de calificación.
 * Sigue el comportamiento FIFO usando nodos enlazados.
 * @author axelm
 * @param <T> tipo de dato almacenado
 */
public class SolicitudesDeCalificacion<T> {
    private Nodo<T> finCola;
    private Nodo<T> inicioCola;
    private int solicitudes;

    /**
     * Crea una cola vacía.
     */
    public SolicitudesDeCalificacion(){
        this.finCola = null;
        this.inicioCola = null;
        this.solicitudes = 0;
    }

    /**
     * Crea una cola con un elemento inicial.
     *
     * @param dato elemento inicial de la cola
     */
    public SolicitudesDeCalificacion(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        this.finCola = nuevo;
        this.inicioCola = nuevo;
        this.solicitudes = 1;
    }

    /**
     * Crea una cola con dos elementos: inicio y fin.
     *
     * @param inicio primer elemento
     * @param fin segundo elemento
     */
    public SolicitudesDeCalificacion(T inicio, T fin){
        this.finCola = new Nodo<>(fin);
        this.inicioCola = new Nodo<>(inicio);
        inicioCola.setSiguiente(finCola);
        this.solicitudes = 2;
    }

    /**
     * Nodo interno para la cola.
     */
    class Nodo<T>{
        private Nodo<T> siguiente;
        private T dato;

        public Nodo(T dato){
            this.dato = dato;
        }

        public void setDato(T dato){
            this.dato = dato;
        }

        public T getDato(){
            return this.dato;
        }

        public void setSiguiente(Nodo<T> siguiente){
            this.siguiente = siguiente;
        }

        public Nodo<T> getSiguiente(){
            return this.siguiente;
        }
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param dato elemento a encolar
     */
    public void encolar(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if(inicioCola == null){
            inicioCola = nuevo;
            finCola = nuevo;
            solicitudes++;
            return;
        }
        if(inicioCola == finCola && inicioCola != null){
            inicioCola.setSiguiente(nuevo);
            finCola = nuevo;
            solicitudes++;
            return;
        }
        finCola.setSiguiente(nuevo);
        finCola = nuevo;
        solicitudes++;
    }

    /**
     * Remueve y retorna el elemento al frente de la cola.
     *
     * @return el primer elemento de la cola
     * @throws ColaException si la cola está vacía
     */
    public T desencolar() throws ColaException{
        if(empty()){
            throw new ColaException("La cola esta vacia");
        }
        T dato = inicioCola.getDato();
        inicioCola = inicioCola.getSiguiente();
        solicitudes--;
        if(inicioCola == null){
            finCola = null;
        }
        return dato;
    }

    /**
     * Obtiene el elemento en un índice sin eliminarlo.
     *
     * @param i índice solicitado
     * @return el dato en la posición o null si está fuera de rango
     */
    public T get(int i){
        if(i < 0 || i >= solicitudes){
            return null;
        }
        Nodo<T> aux = inicioCola;
        for(int k = 0; k < i; k++){
            aux = aux.getSiguiente();
        }
        return aux.getDato();
    }

    /**
     * Busca un elemento en la cola.
     *
     * @param dato elemento a buscar
     * @return posición donde se encuentra
     * @throws ColaException si no se encuentra
     */
    public int indexOf(T dato) throws ColaException{
        Nodo<T> aux = inicioCola;
        int pos = 0;
        while(aux != null){
            if(aux.getDato().equals(dato)){
                return pos;
            }
            pos++;
            aux = aux.getSiguiente();
        }
        throw new ColaException("No se encontro el dato en la fila");
    }

    /**
     * Retorna el número de elementos en la cola.
     *
     * @return tamaño de la cola
     */
    public int getSize(){
        return this.solicitudes;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si está vacía, false si tiene elementos
     */
    public boolean empty(){
        return solicitudes == 0;
    }
}