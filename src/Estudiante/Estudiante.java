package Estudiante;

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

class NodoABB {

    Estudiante estudiante;
    NodoABB izquierda;
    NodoABB derecha;

    public NodoABB(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.derecha = null;
        this.izquierda = null;
    }
}


// class estudiantesABB {
//
//    NodoABB raiz;
//
//    public estudiantesABB() {
//        raiz = null;
//    }
//
//    public boolean insertar(Estudiante nuevoEstudiante) {
//        if (buscar(nuevoEstudiante.getMatricula()) != null) {
//            return false; 
//        }
//        raiz = insertarRecursivo(raiz, nuevoEstudiante);
//        return true;
//    }
//
//    private NodoABB insertarRecursivo(NodoABB nodo, Estudiante estudiante) {
//        if (nodo == null) {
//            return new NodoABB(estudiante);
//        }
//
//        int comparacion = estudiante.getMatricula().compareTo(nodo.estudiante.getMatricula());
//
//        if (comparacion < 0) {
//            nodo.izquierda = insertarRecursivo(nodo.izquierda, estudiante);
//        } else if (comparacion > 0) {
//            nodo.derecha = insertarRecursivo(nodo.derecha, estudiante);
//        }
//        
//
//        return nodo;
//    }
//
//
//    public Estudiante buscar(String matricula) {
//        return buscarRecursivo(raiz, matricula);
//    }
//
//    private Estudiante buscarRecursivo(NodoABB nodo, String matricula) {
//        if (nodo == null) {
//            return null; 
//        }
//
//        int comparacion = matricula.compareTo(nodo.estudiante.getMatricula());
//
//        if (comparacion == 0) {
//            return nodo.estudiante; 
//        } else if (comparacion < 0) {
//            return buscarRecursivo(nodo.izquierda, matricula); 
//        } else {
//            return buscarRecursivo(nodo.derecha, matricula); 
//        }
//    }
//
//    public List<Estudiante> obtenerTodosLosEstudiantes() {
//        List<Estudiante> estudiantes = new ArrayList<>();
//        inOrden(raiz, estudiantes);
//        return estudiantes;
//    }
//
//    private void inOrden(NodoABB nodo, List<Estudiante> lista) {
//        if (nodo != null) {
//            inOrden(nodo.izquierda, lista);
//            lista.add(nodo.estudiante);
//            inOrden(nodo.derecha, lista);
//        }
//    }
//
//    public boolean eliminar(String matricula) {
//        Estudiante encontrado = buscar(matricula);
//        if (encontrado == null) {
//            return false;
//        }
//        raiz = eliminarRecursivo(raiz, matricula);
//        return true;
//    }
//
//    private NodoABB eliminarRecursivo(NodoABB nodo, String matricula) {
//        if (nodo == null) {
//            return nodo;
//        }
//
//        int comparacion = matricula.compareTo(nodo.estudiante.getMatricula());
//
//        if (comparacion < 0) {
//            nodo.izquierda = eliminarRecursivo(nodo.izquierda, matricula);
//        } else if (comparacion > 0) {
//            nodo.derecha = eliminarRecursivo(nodo.derecha, matricula);
//        } else {
//            if (nodo.izquierda == null) {
//                return nodo.derecha;
//            }
//            if (nodo.derecha == null) {
//                return nodo.izquierda;
//            }
//
//            nodo.estudiante = getValorMinimo(nodo.derecha);
//
//            nodo.derecha = eliminarRecursivo(nodo.derecha, nodo.estudiante.getMatricula());
//        }
//        return nodo;
//    }
//
//    private Estudiante getValorMinimo(NodoABB nodo) {
//        Estudiante valorMinimo = nodo.estudiante;
//        while (nodo.izquierda != null) {
//            valorMinimo = nodo.izquierda.estudiante;
//            nodo = nodo.izquierda;
//        }
//        return valorMinimo;
//    }
//}

// --- 5. AVL NODE y AVL para Estudiantes (Ordenado por Promedio) ---
/**
 * Nodo del Árbol AVL. La clave es el promedio y el valor es la referencia al
 * estudiante.
 */

/**
 * Nodo del Árbol AVL.La clave es el promedio y el valor es la referencia al
 estudiante.
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