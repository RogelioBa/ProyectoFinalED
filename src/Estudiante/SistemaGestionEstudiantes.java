/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estudiante;

import static Estudiante.Accion.TipoAccion.CALIFICACION_AGREGADA;
import static Estudiante.Accion.TipoAccion.INSCRIPCION_CURSO;
import static Estudiante.Accion.TipoAccion.REGISTRO_ESTUDIANTE;
import java.util.EmptyStackException;
import java.util.List;

/**
 *
 * @author Roger Jr
 */
// --- 6. SISTEMA DE GESTIÓN DE ESTUDIANTES (MAIN) ---
public class SistemaGestionEstudiantes {

    // Estructuras de datos principales
    private estudiantesABB bstEstudiantes; // BST ordenado por matrícula
    private Pila<Accion> pilaAcciones; // Pila para el historial de acciones

    public SistemaGestionEstudiantes() {
        this.bstEstudiantes = new estudiantesABB();
        this.pilaAcciones = new Pila<>();
        // Inicializar con algunos datos de ejemplo
        inicializarDatos();
    }

    // --- LÓGICA DE GESTIÓN (Funcionalidades solicitadas) ---
    /**
     * 1.1. Registro de estudiantes Realiza la validación e inserción en el BST.
     *
     * @param matricula Matrícula del estudiante.
     * @param nombre Nombre completo.
     * @param tel Teléfono.
     * @param correo Correo electrónico.
     * @param dir Dirección postal.
     */
    public void registrarEstudiante(String matricula, String nombre, String tel, String correo, String dir) {
        if (matricula == null || matricula.trim().isEmpty()) {
            System.out.println("Error: La matrícula no puede estar vacía.");
            return;
        }

        // Validación de datos simple (ej. el teléfono es numérico)
        if (!tel.matches("\\d+")) {
            System.out.println("Error: El teléfono debe contener solo dígitos.");
            return;
        }

        Estudiante nuevo = new Estudiante(matricula, nombre, tel, correo, dir);

        if (bstEstudiantes.insertar(nuevo)) {
            // Registrar la acción en la Pila
            Accion accion = new Accion(Accion.TipoAccion.REGISTRO_ESTUDIANTE,
                    "Registro de " + nombre,
                    matricula);
            pilaAcciones.agregarAccion(accion);
            System.out.println("¡Estudiante registrado con éxito y acción registrada para Deshacer!");
        } else {
            System.out.println("Error: Ya existe un estudiante con la matrícula " + matricula + ".");
        }
    }

    /**
     * 1.2. Búsqueda de estudiante por matrícula.
     *
     * @param matricula La matrícula a buscar.
     */
    public void buscarEstudiante(String matricula) {
        Estudiante encontrado = bstEstudiantes.buscar(matricula);

        if (encontrado != null) {
            System.out.println("\n--- Estudiante Encontrado ---");
            System.out.println(encontrado.toString());
            System.out.println("-----------------------------");
        } else {
            System.out.println("Estudiante con matrícula '" + matricula + "' no encontrado.");
        }
    }

    /**
     * 6.1. Listado de estudiantes ordenados por promedio (Usando AVL). *
     * Proceso: 1. Obtener todos los estudiantes del BST. 2. Recalcular
     * recursivamente el promedio de cada uno. 3. Insertar (Promedio,
     * Estudiante) en un nuevo AVL. 4. Recorrer el AVL In-Orden.
     */
    public void listarEstudiantesOrdenadosPorPromedio() {
        List<Estudiante> todos = bstEstudiantes.obtenerTodosLosEstudiantes();

        if (todos.isEmpty()) {
            System.out.println("No hay estudiantes registrados para generar el reporte.");
            return;
        }

        EstudianteAVL avlPromedios = new EstudianteAVL();

        for (Estudiante e : todos) {
            // 2. Cálculo del promedio recursivo
            double promedio = e.calcularPromedioRecursivo();

            // 3. Inserción en el AVL
            avlPromedios.insertar(promedio, e);
        }

        // 4. Recorrido In-Orden del AVL (Imprime el resultado)
        avlPromedios.inOrdenRecorrido();
    }

    /**
     * Simulación: 4.2. Procesar solicitud de calificación (agregando una
     * calificación). * Nota: En un sistema real, esta lógica vendría después
     * del Dequeue de una Cola de Solicitudes. Aquí simulamos la actualización
     * directa y registramos la acción.
     *
     * * @param matricula Matrícula del estudiante.
     * @param nuevaCalificacion Calificación a añadir.
     */
    public void agregarCalificacion(String matricula, double nuevaCalificacion) {
        Estudiante e = bstEstudiantes.buscar(matricula);

        if (e == null) {
            System.out.println("Error: Estudiante no encontrado para aplicar la calificación.");
            return;
        }

        // 2. Actualiza el arreglo de calificaciones
        e.agregarCalificacion(nuevaCalificacion);
        System.out.printf("Calificación (%.2f) agregada al estudiante %s.\n", nuevaCalificacion, e.getNombreCompleto());

        // 3. Registra la operación en la Pila de acciones
        // Los 'datosPrevios' son el tamaño actual del arreglo, que es el índice a eliminar para deshacer.
        int indiceEliminar = e.getCalificaciones().size() - 1;
        Accion accion = new Accion(Accion.TipoAccion.CALIFICACION_AGREGADA,
                String.format("Calificación %.2f para %s", nuevaCalificacion, matricula),
                new Object[]{matricula, indiceEliminar});
        pilaAcciones.agregarAccion(accion);
    }

    /**
     * 5.1. Deshacer última acción. Hace POP de la pila y revierte la operación.
     */
    public void deshacerUltimaAccion() {
        try {
            Accion ultimaAccion = pilaAcciones.DesaserAccion();
            System.out.println("\n--- DESHACIENDO ACCIÓN: " + ultimaAccion.tipo + " ---");

            switch (ultimaAccion.tipo) {
                case REGISTRO_ESTUDIANTE:
                    String matriculaARevertir = (String) ultimaAccion.datosPrevios;
                    if (bstEstudiantes.eliminar(matriculaARevertir)) {
                        System.out.println("✔ Deshecho: Se eliminó el registro del estudiante con matrícula " + matriculaARevertir + ".");
                    } else {
                        System.out.println("⚠ Advertencia: El estudiante ya había sido eliminado manualmente.");
                    }
                    break;
                case CALIFICACION_AGREGADA:
                    Object[] datos = (Object[]) ultimaAccion.datosPrevios;
                    String mat = (String) datos[0];
                    int indice = (int) datos[1];

                    Estudiante e = bstEstudiantes.buscar(mat);
                    if (e != null && indice < e.getCalificaciones().size()) {
                        // Al deshacer, simplemente eliminamos la última calificación agregada (por índice)
                        e.getCalificaciones().remove(indice);
                        System.out.printf("✔ Deshecho: Se eliminó la última calificación del estudiante %s.\n", mat);
                    } else {
                        System.out.println("⚠ Advertencia: No se pudo deshacer la calificación (estudiante no encontrado o índice inválido).");
                    }
                    break;
                case INSCRIPCION_CURSO:
                    // Lógica para deshacer inscripción (requiere la implementación del Catálogo/Listas Enlazadas)
                    System.out.println("Deshacer inscripción no implementado en este módulo (requiere módulo de Cursos).");
                    break;
            }
        } catch (EmptyStackException e) {
            System.out.println("La Pila de acciones está vacía. No hay nada que deshacer.");
        } catch (Exception e) {
            System.err.println("Error al intentar deshacer la acción: " + e.getMessage());
        }
    }

    /**
     * Inicializa algunos datos para la demostración.
     */
    private void inicializarDatos() {
        registrarEstudiante("A001", "Ana Pérez", "1234567890", "ana@tec.mx", "Calle A #10");
        // No registrar la acción para la precarga, simular una acción ya ejecutada
        pilaAcciones.DesaserAccion();
        registrarEstudiante("C003", "Carlos Gómez", "9876543210", "carlos@tec.mx", "Calle C #30");
        pilaAcciones.DesaserAccion();
        registrarEstudiante("B002", "Beto Ramírez", "5551234567", "beto@tec.mx", "Calle B #20");
        pilaAcciones.DesaserAccion();

        // Agregar calificaciones (con registro de acción)
        agregarCalificacion("A001", 9.5);
        agregarCalificacion("A001", 8.0);
        agregarCalificacion("B002", 7.0);
        agregarCalificacion("C003", 10.0);
        agregarCalificacion("C003", 8.5);
    }
}

  
