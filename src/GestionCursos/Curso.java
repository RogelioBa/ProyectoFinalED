/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionCursos;

import Estudiante.Estudiante;
import Listas.ListaDobleCircular;
import Listas.ListaEnlazadaSimple;

/**
 *
 * @author Roger Jr
 */

public class Curso 
{
    private String clave;           // Identificador único
    private String nombre;          // Nombre
    private int capacidadMaxima;    // Cupo límite
    private int cantidadInscritos;  // Contador 

    private ListaEnlazadaSimple<Estudiante> inscritos;
    private ListaDobleCircular<Estudiante> listaEspera;

    public Curso(String clave, String nombre, int capacidadMaxima) 
    {
        this.clave = clave;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadInscritos = 0;
        
        this.inscritos = new ListaEnlazadaSimple<>();
        this.listaEspera = new ListaDobleCircular<>();
    }


    /**
     * Verifica si aún caben estudiantes en el curso.
     * @return true si hay espacio, false si está lleno.
     */
    public boolean hayCupo() 
    {
        return cantidadInscritos < capacidadMaxima;
    }

    /**
     * Registra un nuevo inscrito y aumenta el contador.
     */
    public void registrarInscrito(Estudiante estudiante) 
    {
        if (hayCupo()) 
        {
            inscritos.agregar(estudiante);
            cantidadInscritos++;
        }
    }

    /**
     * Agrega un estudiante a la lista de espera.
     */
    public void agregarAEspera(Estudiante estudiante) 
    {
        listaEspera.agregar(estudiante);
    }
    
    public boolean estaInscrito(Estudiante estudiante) 
    {
        if (inscritos.buscar(estudiante) != null) 
        {
            return true;
        }
        
        if (listaEspera.buscar(estudiante) != null) 
        {
            return true; 
        }
        
        return false; 
    }


    public String getClave() 
    {
        return clave;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public int getCapacidadMaxima() 
    {
        return capacidadMaxima;
    }

    public int getCantidadInscritos() 
    {
        return cantidadInscritos;
    }

    public ListaEnlazadaSimple<Estudiante> getInscritos() 
    {
        return inscritos;
    }

    public ListaDobleCircular<Estudiante> getListaEspera() 
    {
        return listaEspera;
    }

    @Override
    public String toString() 
    {
        return "Clave del curso: " + clave + "\n" + " Curso: " + nombre + "\n" + " Inscritos: " + cantidadInscritos + "/" + capacidadMaxima;
    }
}
