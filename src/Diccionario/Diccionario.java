/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Diccionario;

import Listas.ListaEnlazadaSimple;

/**
 *
 * @author Roger Jr
 */
public class Diccionario<K, V> 
{
    private class Entrada 
    {
        K clave;
        V valor;

        public Entrada(K clave, V valor) 
        {
            this.clave = clave;
            this.valor = valor;
        }

        @Override
        public boolean equals(Object obj) 
        {
            if (obj instanceof Diccionario.Entrada) 
            {
                Entrada otra = (Entrada) obj;
                return this.clave.equals(otra.clave);
            }
            return false;
        }
        
        @Override
        public String toString() 
        {
            return "{" + clave + ": " + valor + "}";
        }
    }

    private ListaEnlazadaSimple<Entrada>[] tabla; 
    private int capacidad; 
    private int cantidadElementos;

    //es para que no moleste mucho ya que java tiene una regla
    //que no se pueden crear arreglos de tipo generico
    @SuppressWarnings("unchecked")
    public Diccionario(int capacidad) 
    {
        this.capacidad = capacidad;
        this.cantidadElementos = 0;
        // En Java no se pueden crear arreglos genéricos directamente, hay que hacer cast
        this.tabla = (ListaEnlazadaSimple<Entrada>[]) new ListaEnlazadaSimple[capacidad];
        
        for (int i = 0; i < capacidad; i++) 
        {
            tabla[i] = new ListaEnlazadaSimple<>();
        }
    }

    /**
     * Función Hash: Convierte la clave en un índice válido del arreglo.
     * Es lo que decías de "Hashcode entre número de elementos".
     */
    private int hash(K clave) 
    {
        // Math.abs es para evitar números negativos
        return Math.abs(clave.hashCode()) % capacidad;
    }

    /**
     * Agrega o actualiza un valor en el diccionario.
     */
    public void agregar(K clave, V valor) 
    {
        int indice = hash(clave);
        ListaEnlazadaSimple<Entrada> lista = tabla[indice];
        
        Entrada nuevaEntrada = new Entrada(clave, valor);
        
        Entrada existente = lista.buscar(nuevaEntrada); 
        
        if (existente != null) 
        {
            existente.valor = valor; 
        } 
        else 
        {
            lista.agregar(nuevaEntrada); 
            cantidadElementos++;
        }
    }

    /**
     * Recupera el valor asociado a una clave.
     * @return El valor o null si no existe.
     */
    public V recuperar(K clave) 
    {
        int indice = hash(clave);
        ListaEnlazadaSimple<Entrada> lista = tabla[indice];
        
        Entrada busqueda = new Entrada(clave, null);
        Entrada encontrado = lista.buscar(busqueda);
        
        return (encontrado != null) ? encontrado.valor : null;
    }

    /**
     * Elimina un elemento por su clave.
     */
    public boolean eliminar(K clave) 
    {
        int indice = hash(clave);
        ListaEnlazadaSimple<Entrada> lista = tabla[indice];
        
        Entrada busqueda = new Entrada(clave, null);
        
        if (lista.eliminar(busqueda)) 
        {
            cantidadElementos--;
            return true;
        }
        return false;
    }
    
    /**
     * Método auxiliar para ver todo el contenido (útil para "Listar Cursos")
     */
    public void imprimirTabla() 
    {
        for (int i = 0; i < capacidad; i++) 
        {
            if (!tabla[i].estaVacia()) 
            {
                tabla[i].imprimir(); 
            }
        }
    }
}
