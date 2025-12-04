/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Roger Jr
 */
public class CursoNoEncontradoException extends Exception 
{
    public CursoNoEncontradoException(String clave) 
    {
        super("Error: No se encontro ningún curso con la clave '" + clave + "'.");
    }
}
