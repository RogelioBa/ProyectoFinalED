/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Roger Jr
 */
public class EstudianteYaInscritoException extends Exception 
{
    public EstudianteYaInscritoException(String nombre, String curso) {
        super("Error: El estudiante " + nombre + " ya está inscrito (o en espera) en el curso " + curso + ".");
    }
}
