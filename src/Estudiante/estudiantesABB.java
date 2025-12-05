/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estudiante;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
 public class estudiantesABB {

    NodoABB raiz;

    public estudiantesABB() {
        raiz = null;
    }

    public boolean insertar(Estudiante nuevoEstudiante) {
        if (buscar(nuevoEstudiante.getMatricula()) != null) {
            return false; 
        }
        raiz = insertarRecursivo(raiz, nuevoEstudiante);
        return true;
    }

    private NodoABB insertarRecursivo(NodoABB nodo, Estudiante estudiante) {
        if (nodo == null) {
            return new NodoABB(estudiante);
        }

        int comparacion = estudiante.getMatricula().compareTo(nodo.estudiante.getMatricula());

        if (comparacion < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, estudiante);
        } else if (comparacion > 0) {
            nodo.derecha = insertarRecursivo(nodo.derecha, estudiante);
        }
        

        return nodo;
    }


    public Estudiante buscar(String matricula) {
        return buscarRecursivo(raiz, matricula);
    }

    private Estudiante buscarRecursivo(NodoABB nodo, String matricula) {
        if (nodo == null) {
            return null; 
        }

        int comparacion = matricula.compareTo(nodo.estudiante.getMatricula());

        if (comparacion == 0) {
            return nodo.estudiante; 
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierda, matricula); 
        } else {
            return buscarRecursivo(nodo.derecha, matricula); 
        }
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        inOrden(raiz, estudiantes);
        return estudiantes;
    }

    private void inOrden(NodoABB nodo, List<Estudiante> lista) {
        if (nodo != null) {
            inOrden(nodo.izquierda, lista);
            lista.add(nodo.estudiante);
            inOrden(nodo.derecha, lista);
        }
    }

    public boolean eliminar(String matricula) {
        Estudiante encontrado = buscar(matricula);
        if (encontrado == null) {
            return false;
        }
        raiz = eliminarRecursivo(raiz, matricula);
        return true;
    }

    private NodoABB eliminarRecursivo(NodoABB nodo, String matricula) {
        if (nodo == null) {
            return nodo;
        }

        int comparacion = matricula.compareTo(nodo.estudiante.getMatricula());

        if (comparacion < 0) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, matricula);
        } else if (comparacion > 0) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, matricula);
        } else {
            if (nodo.izquierda == null) {
                return nodo.derecha;
            }
            if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            nodo.estudiante = getValorMinimo(nodo.derecha);

            nodo.derecha = eliminarRecursivo(nodo.derecha, nodo.estudiante.getMatricula());
        }
        return nodo;
    }

    private Estudiante getValorMinimo(NodoABB nodo) {
        Estudiante valorMinimo = nodo.estudiante;
        while (nodo.izquierda != null) {
            valorMinimo = nodo.izquierda.estudiante;
            nodo = nodo.izquierda;
        }
        return valorMinimo;
    }
}