/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calificaciones;

import Solicitudes.SolicitudCalificacion;
import Solicitudes.SolicitudesDeCalificacion;
import Deshacer.PilaAcciones;
import Deshacer.Accion;
import Excepciones.ColaException;
import Excepciones.DinamicListException;

/**
 * Procesa solicitudes de calificación utilizando una cola FIFO.
 * Modifica el arreglo dinámico de calificaciones y registra
 * todas las operaciones en la pila de acciones para permitir deshacer.
 * @author axelm
 */
public class ProcesadorCalificaciones {
    private SolicitudesDeCalificacion<SolicitudCalificacion> cola;
    private PilaAcciones pila;

    /**
     * Inicializa el procesador con una cola de solicitudes y una pila de acciones.
     *
     * @param cola estructura FIFO que contiene las solicitudes
     * @param pila pila donde se registran las acciones realizadas
     */
    public ProcesadorCalificaciones(SolicitudesDeCalificacion<SolicitudCalificacion> cola, PilaAcciones pila) {
        this.cola = cola;
        this.pila = pila;
    }

    /**
     * Procesa la siguiente solicitud de la cola.
     * - Si no tiene índice → inserta al final.
     * - Si tiene índice → modifica el valor existente.
     *
     * Cada operación se registra en la pila de acciones.
     *
     * @param califs arreglo dinámico del estudiante
     * @return información sobre la operación realizada
     * @throws ColaException si la cola está vacía
     * @throws DinamicListException si ocurre error en el vector de calificaciones
     */
    public ResultadoCalificacion procesar(Calificaciones califs) throws ColaException, DinamicListException {
        SolicitudCalificacion solicitud = cola.desencolar();

        if (solicitud.getIndice() == null) {
            int indice = califs.getSize();
            califs.append(solicitud.getCalificacion());
            pila.push(new Accion(Accion.Tipo.CALIFICACION, null, solicitud));
            return new ResultadoCalificacion(true, -1, indice);
        }

        float previo = califs.get(solicitud.getIndice());
        califs.replace(solicitud.getIndice(), solicitud.getCalificacion());
        pila.push(new Accion(Accion.Tipo.CALIFICACION, previo, solicitud));
        return new ResultadoCalificacion(false, previo, solicitud.getIndice());
    }
}