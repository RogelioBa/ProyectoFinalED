/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

/**
 *
 * @author Roger Jr
 */
public class ListaDobleCircular<T> 
{

    private class Nodo 
    {
        T dato;
        Nodo siguiente;
        Nodo anterior; 

        public Nodo(T dato) 
        {
            this.dato = dato;
            this.siguiente = this; 
            this.anterior = this;
        }
    }

    private Nodo inicio;
    private int tamanio;

    public ListaDobleCircular() 
    {
        this.inicio = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un estudiante al final de la cola de espera.
     */
    public void agregar(T dato) 
    {
        Nodo nuevo = new Nodo(dato);

        if (estaVacia()) 
        {
            inicio = nuevo;
        } else {
            Nodo ultimo = inicio.anterior; 

            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;

            nuevo.siguiente = inicio;
            inicio.anterior = nuevo;
        }
        tamanio++;
    }

    /**
     * Elimina un dato específico
     */
    public boolean eliminar(T dato) 
    {
        if (estaVacia()) return false;
        Nodo actual = inicio;

        do 
        {
            if (actual.dato.equals(dato)) 
            {
                if (tamanio == 1) {
                    inicio = null;
                } 
                else 
                {
                    Nodo elAnterior = actual.anterior;
                    Nodo elSiguiente = actual.siguiente;

                    elAnterior.siguiente = elSiguiente;
                    elSiguiente.anterior = elAnterior;

                    if (actual == inicio) 
                    {
                        inicio = elSiguiente;
                    }
                }
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        } while (actual != inicio);

        return false;
    }

    /**
     * Mostrar los N primeros de la espera.
     */
    public void mostrarPrimeros(int n) 
    {
        if (estaVacia()) 
        {
            System.out.println("Lista de espera vacia.");
            return;
        }

        Nodo actual = inicio;
        int contador = 0;
        
        System.out.print("Lista de Espera: ");
        do 
        {
            System.out.print(actual.dato + " <-> ");
            actual = actual.siguiente;
            contador++;
        } 
        while (actual != inicio && contador < n);
        
    }
    
    public T eliminarPrimero() 
    {
        if (estaVacia()) return null;
        T dato = inicio.dato;
        eliminar(dato);
        return dato;
    }

    public boolean estaVacia() 
    {
        return inicio == null;
    }

    public int getTamanio() 
    {
        return tamanio;
    }
}
