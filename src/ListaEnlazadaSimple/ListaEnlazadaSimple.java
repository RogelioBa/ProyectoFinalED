/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaEnlazadaSimple;

/**
 *
 * @author Roger Jr
 */
public class ListaEnlazadaSimple<T> 
{
    private class Nodo<T> 
    {
        T dato;
        Nodo siguiente;

        public Nodo(T dato) 
        {
            this.dato = dato;
            this.siguiente = null;
        }

        public T getDato() 
        {
            return dato;
        }

        public void setDato(T dato) 
        {
            this.dato = dato;
        }

        public Nodo getSiguiente() 
        {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) 
        {
            this.siguiente = siguiente;
        }
        
        
    }
    
    private Nodo<T> inicio; 
    private int tamanio;    

    public ListaEnlazadaSimple() 
    {
        this.inicio = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * @param dato El dato a guardar.
     */
    public void agregar(T dato) 
    {
        Nodo<T> nuevo = new Nodo<>(dato);
        
        if (estaVacia()) 
        {
            inicio = nuevo;
        } 
        else 
        {
            Nodo<T> aux = inicio;
            while (aux.getSiguiente() != null) 
            {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        tamanio++;
    }

    /**
     * Elimina un elemento específico de la lista (usando equals).
     * @param dato El dato a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(T dato) 
    {
        if (estaVacia()) 
        {
            return false;
        }

        if (inicio.getDato().equals(dato)) 
        {
            inicio = inicio.getSiguiente();
            tamanio--;
            return true;
        }

        Nodo<T> aux = inicio;
        while (aux.getSiguiente() != null) 
        {
            if (aux.getSiguiente().getDato().equals(dato)) 
            {
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                tamanio--;
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    /**
     * Busca si un elemento existe en la lista.
     * @param dato Dato a buscar.
     * @return El dato encontrado o null si no existe.
     */
    public T buscar(T dato) 
    {
        Nodo<T> actual = inicio;
        while (actual != null) 
        {
            if (actual.getDato().equals(dato)) 
            {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Obtiene el dato en una posición específica (como un arreglo).
     * Útil para recorrer la lista con un for normal.
     */
    public T get(int indice) 
    {
        if (indice < 0 || indice >= tamanio) return null;
        
        Nodo<T> actual = inicio;
        for (int i = 0; i < indice; i++) 
        {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public boolean estaVacia() 
    {
        return inicio == null;
    }

    public int getTamanio() 
    {
        return tamanio;
    }
    
    public void imprimir() 
    {
        Nodo<T> actual = inicio;
        System.out.print("[ ");
        while (actual != null) 
        {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println("]");
    }
}
