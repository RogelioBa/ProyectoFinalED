/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

import Estudiante.Estudiante;

/**
 * ListaCircularSimple
 * -------------------
 * Estructura circular para rotar el rol de tutor/líder entre estudiantes.
 * Cada nodo apunta al siguiente, formando un ciclo.
 *
 * @author Roberto
 * @version 1.0
 */
public class ListaCircularSimple {

    private Nodo actual;

    /**
     * Nodo interno que contiene un estudiante y referencia al siguiente.
     */
    private static class Nodo {
        Estudiante estudiante;
        Nodo siguiente;

        Nodo(Estudiante estudiante) {
            this.estudiante = estudiante;
        }
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si no hay nodos, false si hay al menos uno.
     */
    public boolean estaVacia() {
        return actual == null;
    }

    /**
     * Agrega un estudiante a la lista circular.
     * @param estudiante el estudiante a agregar.
     */
    public void agregar(Estudiante estudiante) {
        Nodo nuevo = new Nodo(estudiante);
        if (actual == null) {
            nuevo.siguiente = nuevo;
            actual = nuevo;
        } else {
            Nodo temp = actual;
            while (temp.siguiente != actual) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
            nuevo.siguiente = actual;
        }
    }

    /**
     * Avanza al siguiente estudiante en la lista.
     */
    public void rotar() {
        if (!estaVacia()) {
            actual = actual.siguiente;
        }
    }

    /**
     * Obtiene el estudiante actual con rol asignado.
     * @return el estudiante actual.
     */
    public Estudiante obtenerTutorActual() {
        return estaVacia() ? null : actual.estudiante;
    }
}