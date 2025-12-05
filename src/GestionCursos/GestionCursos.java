package GestionCursos;

import Diccionario.Diccionario;
import Estudiante.Estudiante;
import Excepciones.CursoNoEncontradoException;
import Excepciones.EstudianteYaInscritoException;

/**
 * Clase controladora para la gestion del catalogo de cursos
 * Maneja la logica de negocio para agregar, eliminar, buscar y listar cursos,
 * asi como la inscripcion de estudiantes
 * * Estructura de datos utilizada: Diccionario (Tabla Hash)
 *
 * @author Roger Jr
 */
public class GestionCursos 
{
    /**
     * Diccionario que almacena los cursos usando su clave alfanumerica como identificador
     */
    private Diccionario<String, Curso> catalogoCursos;

    /**
     * Constructor de la clase
     * Inicializa el diccionario con una capacidad inicial definida
     */
    public GestionCursos() 
    {
        this.catalogoCursos = new Diccionario<>(100);
    }

    /**
     * Agrega un nuevo curso al catalogo
     * Verifica previamente que la clave no exista para evitar duplicados
     * * @param clave Clave unica del curso
     * @param nombre Nombre descriptivo del curso
     * @param capacidad Numero maximo de estudiantes permitidos
     * @return true si el curso se agrego correctamente, false si la clave ya existia
     */
    public boolean agregarCurso(String clave, String nombre, int capacidad) 
    {
        if (catalogoCursos.recuperar(clave) != null) 
        {
            return false;
        }

        Curso nuevoCurso = new Curso(clave, nombre, capacidad);
        catalogoCursos.agregar(clave, nuevoCurso);
        
        return true;
    }

    /**
     * Elimina un curso del catalogo mediante su clave
     * * @param clave La clave del curso a eliminar
     * @throws CursoNoEncontradoException Si no existe un curso con esa clave
     */
    public void eliminarCurso(String clave) throws CursoNoEncontradoException 
    {
        boolean eliminado = catalogoCursos.eliminar(clave);
        
        if (!eliminado) 
        {
            throw new CursoNoEncontradoException(clave);
        }
    }
    
    /**
     * Busca un curso especifico en el catalogo
     * Util para consultar detalles o verificar existencia antes de otras operaciones
     * * @param clave La clave del curso a buscar
     * @return El objeto Curso encontrado, o null si no existe
     */
    public Curso buscarCurso(String clave) 
    {
        return catalogoCursos.recuperar(clave);
    }

    /**
     * Imprime en consola todos los cursos registrados
     * Recorre la estructura interna del diccionario
     * * Complejidad: O(n) donde n es la capacidad del diccionario
     */
    public void listarCursos() 
    {
        System.out.println("--- CATALOGO DE CURSOS ---");
        catalogoCursos.imprimirTabla();
    }
    
    /**
     * Gestiona la inscripcion de un estudiante a un curso especifico
     * Aplica la logica de negocio definida en el proyecto:
     * 1 Si hay cupo -> Inscribe en lista simple
     * 2 Si no hay cupo -> Agrega a lista de espera (circular doble)
     * * @param estudiante El objeto estudiante a inscribir
     * @param claveCurso La clave del curso destino
     * @return Un mensaje de texto describiendo el resultado (Inscrito o Lista de Espera)
     * @throws CursoNoEncontradoException Si la clave proporcionada no corresponde a ningun curso
     * @throws EstudianteYaInscritoException Si el estudiante ya se encuentra en el curso (inscrito o espera)
     */
    public String inscribirEstudiante(Estudiante estudiante, String claveCurso) 
            throws CursoNoEncontradoException, EstudianteYaInscritoException 
    {
        Curso curso = catalogoCursos.recuperar(claveCurso);
        if (curso == null) 
        {
            throw new CursoNoEncontradoException(claveCurso);
        }
        if (curso.estaInscrito(estudiante)) 
        {
            throw new EstudianteYaInscritoException(estudiante.getNombreCompleto(), curso.getNombre());
        }
        if (curso.hayCupo()) 
        {
            curso.registrarInscrito(estudiante);
            return "INSCRITO: El estudiante fue aceptado en el curso " + curso.getNombre();
        } 
        else 
        {
            curso.agregarAEspera(estudiante);
            return "LISTA DE ESPERA: El curso esta lleno Estudiante formado en espera";
        }
    }

    /**
     * Obtiene una representacion en texto de la lista de espera de un curso
     * Utilizado para cumplir el requerimiento de mostrar los N primeros de la espera
     * * @param claveCurso La clave del curso a consultar
     * @return Un String formateado con los estudiantes en espera
     * @throws CursoNoEncontradoException Si el curso no existe
     */
    public String obtenerListaEspera(String claveCurso) throws CursoNoEncontradoException 
    {
        Curso curso = catalogoCursos.recuperar(claveCurso);
        
        if (curso == null) 
        {
            throw new CursoNoEncontradoException(claveCurso);
        }
        
        return curso.getListaEspera().obtenerListado(20);
    }
}