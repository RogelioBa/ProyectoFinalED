package MenuPrincipal;

import Estudiante.SistemaGestionEstudiantes;
import GestionCursos.GestionCursos;
import Listas.ListaCircularSimple;
import Solicitudes.SolicitudesDeCalificacion;
import Deshacer.PilaAcciones;
import Excepciones.MatriculaInvalidaException;
import Excepciones.NombreInvalidoException;
import Excepciones.correoInvalidoException;
import Excepciones.direccionVaciaException;
import Excepciones.telefonoInvalidoException;

/**
 * SistemaAcademicoApp
 * -------------------
 * Punto de entrada del sistema académico.
 * Inicializa las dependencias y lanza el menú principal.
 *
 * @author Roberto
 * @version 1.0
 */
public class SistemaAcademicoApp {
    public static void main(String[] args) throws MatriculaInvalidaException, NombreInvalidoException, telefonoInvalidoException, correoInvalidoException, direccionVaciaException {
        // Inicializar dependencias principales
        SistemaGestionEstudiantes sistema = new SistemaGestionEstudiantes();
        GestionCursos gestionCursos = new GestionCursos();
        ListaCircularSimple listaCircular = new ListaCircularSimple();
        SolicitudesDeCalificacion solicitudesCalificacion = new SolicitudesDeCalificacion();
        PilaAcciones pilaAcciones = new PilaAcciones();

        // Lanzar menú principal con todas las dependencias
        new MenuPrincipal(sistema, gestionCursos, listaCircular, solicitudesCalificacion, pilaAcciones);
    }
}