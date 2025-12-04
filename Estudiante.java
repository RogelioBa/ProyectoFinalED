package org.dallanapinya.eddproyecto;

import Excepciones.MatriculaInvalidaException;
import Excepciones.NombreInvalidoException;
import Excepciones.correoInvalidoException;
import Excepciones.direccionVaciaException;
import Excepciones.telefonoInvalidoException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 262718
 */
public class Estudiante {
    
    private String matricula;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private String direccion;

    private List<Double> calificaciones;
    private double promedio;

    public Estudiante(String matricula, String nombreCompleto, String telefono, String correoElectronico, String direccion) throws MatriculaInvalidaException, NombreInvalidoException, telefonoInvalidoException, correoInvalidoException, direccionVaciaException {
        if(!matricula.matches("^[a-zA-Z0-9]{4,10}$")){
            throw new MatriculaInvalidaException("La matricula solo puede contener de 4 a 10 caracteres alfanumericos");
        }
        if (!nombreCompleto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")){
            throw new NombreInvalidoException("Los nombres solo pueden contener letras y espacios");
        }
        if (!telefono.matches("^\\d{10}$")){
            throw new telefonoInvalidoException("Los numeros de telefono solo pueden contener numeros y espacios");
        }
        if(!correoElectronico.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            throw new correoInvalidoException("No es una direccion de correo valida");
        }
        if (direccion == null){
            throw new direccionVaciaException("La direccion no puede estar en blanco");
        }
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.calificaciones = new ArrayList<>();
        this.promedio = 0.0;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono()  {
        
        return telefono;
    }

    public String getCorreo()  {
        return correoElectronico;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public List<Double> getCalificaciones() {
        return calificaciones;
    }

    public double getPromedio() {
        return promedio;
    }
    
    public void setMatricula(String matricula) throws MatriculaInvalidaException {
        if (!matricula.matches("^[a-zA-Z0-9]{4,10}$")) {
            throw new MatriculaInvalidaException("La matricula solo puede contener de 4 a 10 caracteres alfanumericos");
        }
        this.matricula = matricula;
    }

    public void setNombreCompleto(String nombreCompleto) throws NombreInvalidoException {
        if (!nombreCompleto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new NombreInvalidoException("Los nombres solo pueden contener letras y espacios");
        }
        this.nombreCompleto= nombreCompleto;
    }

    public void setTelefono(String telefono) throws telefonoInvalidoException {
        if (!telefono.matches("^\\d{10}$")) {
            throw new telefonoInvalidoException("Los numeros de telefono solo pueden contener numeros y espacios");
        }
        this.telefono= telefono;
    }

    public void setCorreo(String correoElectronico) throws correoInvalidoException {
        if (!correoElectronico.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new correoInvalidoException("No es una direccion de correo valida");
        }
        this.correoElectronico = correoElectronico;
    }
    
    public void setDireccion(String direccion) throws direccionVaciaException {
        if (direccion == null) {
            throw new direccionVaciaException("La direccion no puede estar en blanco");
        }
        this.direccion = direccion;
    }

    public void setCalificaciones(List<Double> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    
    

    public void agregarCalificacion(double calificacion) {
        this.calificaciones.add(calificacion);
    }

    public void modificarCalificacion(int indice, double nuevaCalificacion) {
        if (indice >= 0 && indice < calificaciones.size()) {
            calificaciones.set(indice, nuevaCalificacion);
        }
    }

    public double calcularPromedioRecursivo() {
        if (calificaciones.isEmpty()) {
            this.promedio = 0.0;
            return this.promedio;
        }
        double sumaTotal = calcularSumaRecursiva(0);
        this.promedio = sumaTotal / calificaciones.size();
        return this.promedio;
    }

    private double calcularSumaRecursiva(int indice) {
        if (indice == calificaciones.size()) {
            return 0;
        }
        return calificaciones.get(indice) + calcularSumaRecursiva(indice + 1);
    }
}


class Accion {

    enum TipoAccion {
        REGISTRO_ESTUDIANTE, INSCRIPCION_CURSO, CALIFICACION_AGREGADA
    }

    TipoAccion tipo;
    String descripcion; 
    Object datosPrevios; 

    public Accion(TipoAccion tipo, String detalle, Object datosPrevios) {
        this.tipo = tipo;
        this.descripcion = detalle;
        this.datosPrevios = datosPrevios;
    }

    
}


class Pila<T> {

    private List<T> pila;

    public Pila() {
        this.pila = new ArrayList<>();
    }

    
    public void agregarAccion(T item) {
        pila.add(item);
    }

    public T DesaserAccion() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return pila.remove(pila.size() - 1);
    }


    public T accionAnterior() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return pila.get(pila.size() - 1);
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }
} 

class Nodo {

    Estudiante estudiante;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.derecha = null;
        this.izquierda = null;
    }
}


class estudiantesAVL {

    Nodo raiz;

    public estudiantesAVL() {
        raiz = null;
    }

    public boolean insertar(Estudiante nuevoEstudiante) {
        if (buscar(nuevoEstudiante.getMatricula()) != null) {
            return false; 
        }
        raiz = insertarRecursivo(raiz, nuevoEstudiante);
        return true;
    }

    private Nodo insertarRecursivo(Nodo nodo, Estudiante estudiante) {
        if (nodo == null) {
            return new Nodo(estudiante);
        }

        int comparacion = estudiante.getMatricula().compareTo(nodo.estudiante.getMatricula());

        if (comparacion < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, estudiante);
        } else if (comparacion > 0) {
            nodo.derecha = insertarRecursivo(nodo.derecha, estudiante);
        }
        

        return nodo;
    }

    /**
     * 2. Búsqueda de estudiante por matrícula. Complejidad temporal: O(log N)
     * en el caso promedio, O(N) en el peor caso.
     *
     * @param matricula La matrícula a buscar.
     * @return El objeto Estudiante si se encuentra, null si no.
     */
    public Estudiante buscar(String matricula) {
        return buscarRecursivo(raiz, matricula);
    }

    private Estudiante buscarRecursivo(Nodo nodo, String matricula) {
        if (nodo == null) {
            return null; 
        }

        int comparacion = matricula.compareTo(nodo.estudiante.getMatricula());

        if (comparacion == 0) {
            return nodo.estudiante; // Caso base: encontrado
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierda, matricula); // Buscar a la izquierda
        } else {
            return buscarRecursivo(nodo.derecha, matricula); // Buscar a la derecha
        }
    }

    /**
     * Obtiene una lista de todos los estudiantes para procesar promedios.
     */
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        inOrden(raiz, estudiantes);
        return estudiantes;
    }

    // Recorrido In-Orden para obtener todos los elementos (no ordenados por promedio)
    private void inOrden(Nodo nodo, List<Estudiante> lista) {
        if (nodo != null) {
            inOrden(nodo.izquierda, lista);
            lista.add(nodo.estudiante);
            inOrden(nodo.derecha, lista);
        }
    }

    // Método para eliminar un estudiante (necesario para la acción de deshacer un registro)
    public boolean eliminar(String matricula) {
        Estudiante encontrado = buscar(matricula);
        if (encontrado == null) {
            return false;
        }
        raiz = eliminarRecursivo(raiz, matricula);
        return true;
    }

    private Nodo eliminarRecursivo(Nodo nodo, String matricula) {
        if (nodo == null) {
            return nodo;
        }

        int comparacion = matricula.compareTo(nodo.estudiante.getMatricula());

        if (comparacion < 0) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, matricula);
        } else if (comparacion > 0) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, matricula);
        } else {
            // Nodo encontrado. Casos:
            if (nodo.izquierda == null) {
                return nodo.derecha;
            }
            if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            // Nodo con dos hijos: obtiene el sucesor in-orden (el menor de la subrama derecha)
            nodo.estudiante = getValorMinimo(nodo.derecha);

            // Eliminar el sucesor in-orden
            nodo.derecha = eliminarRecursivo(nodo.derecha, nodo.estudiante.getMatricula());
        }
        return nodo;
    }

    private Estudiante getValorMinimo(Nodo nodo) {
        Estudiante valorMinimo = nodo.estudiante;
        while (nodo.izquierda != null) {
            valorMinimo = nodo.izquierda.estudiante;
            nodo = nodo.izquierda;
        }
        return valorMinimo;
    }
}

// --- 5. AVL NODE y AVL para Estudiantes (Ordenado por Promedio) ---
/**
 * Nodo del Árbol AVL. La clave es el promedio y el valor es la referencia al
 * estudiante.
 */
class AVLNode {

    double promedio; // Clave para el ordenamiento
    Estudiante estudiante; // Referencia al estudiante
    int altura;
    AVLNode izquierda, derecha;

    public AVLNode(double promedio, Estudiante estudiante) {
        this.promedio = promedio;
        this.estudiante = estudiante;
        this.altura = 1;
        this.izquierda = this.derecha = null;
    }
}

/**
 * Árbol AVL para ordenar a los estudiantes por promedio.
 */
class EstudianteAVL {

    AVLNode raiz;

    public EstudianteAVL() {
        raiz = null;
    }

    // Funciones auxiliares
    /**
     * Obtiene la altura del nodo. Complejidad: O(1).
     */
    private int getAltura(AVLNode nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    /**
     * Actualiza la altura del nodo. Complejidad: O(1).
     */
    private void actualizarAltura(AVLNode nodo) {
        nodo.altura = 1 + Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
    }

    /**
     * Obtiene el factor de balanceo de un nodo. Complejidad: O(1).
     */
    private int getFactorBalanceo(AVLNode nodo) {
        return (nodo == null) ? 0 : getAltura(nodo.izquierda) - getAltura(nodo.derecha);
    }

    // Rotaciones AVL
    /**
     * Rotación simple a la derecha (LL). Complejidad: O(1).
     */
    private AVLNode rotacionDerecha(AVLNode y) {
        AVLNode x = y.izquierda;
        AVLNode T2 = x.derecha;

        // Realizar rotación
        x.derecha = y;
        y.izquierda = T2;

        // Actualizar alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x; // Nueva raíz
    }

    /**
     * Rotación simple a la izquierda (RR). Complejidad: O(1).
     */
    private AVLNode rotacionIzquierda(AVLNode x) {
        AVLNode y = x.derecha;
        AVLNode T2 = y.izquierda;

        // Realizar rotación
        y.izquierda = x;
        x.derecha = T2;

        // Actualizar alturas
        actualizarAltura(x);
        actualizarAltura(y);

        return y; // Nueva raíz
    }

    // Funcionalidad principal del AVL
    /**
     * Inserta un nuevo nodo en el AVL (Promedio, ReferenciaEstudiante).
     * Complejidad temporal: O(log N).
     *
     * @param promedio La clave (promedio).
     * @param estudiante La referencia al estudiante.
     */
    public void insertar(double promedio, Estudiante estudiante) {
        raiz = insertarRecursivo(raiz, promedio, estudiante);
    }

    private AVLNode insertarRecursivo(AVLNode nodo, double promedio, Estudiante estudiante) {
        // 1. Inserción BST estándar
        if (nodo == null) {
            return new AVLNode(promedio, estudiante);
        }

        // Si los promedios son iguales, usamos la matrícula como desempate (para mantener el BST)
        int comparacion = Double.compare(promedio, nodo.promedio);
        if (comparacion == 0) {
            comparacion = estudiante.getMatricula().compareTo(nodo.estudiante.getMatricula());
        }

        if (comparacion < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, promedio, estudiante);
        } else { // comparacion >= 0. Se inserta a la derecha para promedios iguales.
            nodo.derecha = insertarRecursivo(nodo.derecha, promedio, estudiante);
        }

        // 2. Actualizar altura del nodo actual
        actualizarAltura(nodo);

        // 3. Obtener el factor de balanceo
        int balanceo = getFactorBalanceo(nodo);

        // 4. Realizar Rotaciones si es necesario
        // Caso I: Izquierda Izquierda (LL)
        if (balanceo > 1 && comparacion < 0) {
            return rotacionDerecha(nodo);
        }

        // Caso II: Derecha Derecha (RR)
        if (balanceo < -1 && comparacion >= 0) {
            return rotacionIzquierda(nodo);
        }

        // Caso III: Izquierda Derecha (LR)
        if (balanceo > 1 && comparacion >= 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }

        // Caso IV: Derecha Izquierda (RL)
        if (balanceo < -1 && comparacion < 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    /**
     * 6.1. Recorrido In-Orden para listar estudiantes por promedio ascendente.
     * Complejidad temporal: O(N), donde N es el número de estudiantes.
     */
    public void inOrdenRecorrido() {
        if (raiz == null) {
            System.out.println("El AVL de promedios está vacío.");
            return;
        }
        System.out.println("\n--- Listado de Estudiantes Ordenados por Promedio Ascendente ---");
        inOrden(raiz);
        System.out.println("----------------------------------------------------------------");
    }

    private void inOrden(AVLNode nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierda);
            System.out.printf("| Promedio: %.2f | Matrícula: %s | Nombre: %s\n",
                    nodo.promedio, nodo.estudiante.getMatricula(), nodo.estudiante.getNombreCompleto());
            inOrden(nodo.derecha);
        }
    }
}

// --- 6. SISTEMA DE GESTIÓN DE ESTUDIANTES (MAIN) ---
public class SistemaGestionEstudiantes {

    // Estructuras de datos principales
    private estudiantesAVL bstEstudiantes; // BST ordenado por matrícula
    private Pila<Accion> pilaAcciones; // Pila para el historial de acciones

    public SistemaGestionEstudiantes() {
        this.bstEstudiantes = new estudiantesAVL();
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

    // --- DEMOSTRACIÓN DEL SISTEMA ---
    public static void main(String[] args) {
        SistemaGestionEstudiantes sistema = new SistemaGestionEstudiantes();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=======================================================");
            System.out.println("          Módulo de Gestión de Estudiantes");
            System.out.println("=======================================================");
            System.out.println("1. Registrar nuevo estudiante");
            System.out.println("2. Buscar estudiante por matrícula (BST)");
            System.out.println("3. Listar estudiantes ordenados por promedio (AVL)");
            System.out.println("4. Simular agregar calificación (para prueba de Pila)");
            System.out.println("5. Deshacer última acción (Pila)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0; // Opción inválida
            }

            switch (opcion) {
                case 1:
                    System.out.print("Matrícula: ");
                    String mat = scanner.nextLine().toUpperCase();
                    System.out.print("Nombre Completo: ");
                    String nom = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = scanner.nextLine();
                    System.out.print("Correo Electrónico: ");
                    String cor = scanner.nextLine();
                    System.out.print("Dirección Postal (Calle, No, Col, Ciudad): ");
                    String dir = scanner.nextLine();
                    sistema.registrarEstudiante(mat, nom, tel, cor, dir);
                    break;
                case 2:
                    System.out.print("Ingrese la matrícula a buscar: ");
                    String busqueda = scanner.nextLine().toUpperCase();
                    sistema.buscarEstudiante(busqueda);
                    break;
                case 3:
                    sistema.listarEstudiantesOrdenadosPorPromedio();
                    break;
                case 4:
                    System.out.print("Matrícula del estudiante: ");
                    String matCal = scanner.nextLine().toUpperCase();
                    System.out.print("Nueva calificación (0.0 a 10.0): ");
                    try {
                        double cal = Double.parseDouble(scanner.nextLine());
                        sistema.agregarCalificacion(matCal, cal);
                    } catch (NumberFormatException e) {
                        System.out.println("Calificación inválida.");
                    }
                    break;
                case 5:
                    sistema.deshacerUltimaAccion();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);

        scanner.close();
    }
    
}
