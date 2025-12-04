package GestionCursos;

import Diccionario.Diccionario;
import Estudiante.Estudiante;
import Excepciones.CursoNoEncontradoException;
import Excepciones.EstudianteYaInscritoException;

/**
 *
 * @author Roger Jr
 */
public class GestionCursos 
{
    private Diccionario<String, Curso> catalogoCursos;

    public GestionCursos() 
    {
        this.catalogoCursos = new Diccionario<>(100);
    }

    /**
     * Agrega un nuevo curso al catálogo.
     */
    public boolean agregarCurso(String clave, String nombre, int capacidad) 
    {
        if (catalogoCursos.recuperar(clave) != null) 
        {
            System.out.println("Error: Ya existe un curso con la clave " + clave);
            return false;
        }

        Curso nuevoCurso = new Curso(clave, nombre, capacidad);

        catalogoCursos.agregar(clave, nuevoCurso);
        System.out.println("Curso agregado exitosamente: " + nombre);
        return true;
    }

    /**
     * Elimina un curso.
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
     * Busca un curso específico para ver sus detalles o inscribir alumnos.
     */
    public Curso buscarCurso(String clave) 
    {
        return catalogoCursos.recuperar(clave);
    }

    /**
     * Muestra todos los cursos registrados.
     */
    public void listarCursos() 
    {
        System.out.println("--- CATÁLOGO DE CURSOS ---");
        catalogoCursos.imprimirTabla();
    }
    
    /**
     * Inscribe a un estudiante en un curso.
     * 1. Busca el curso en el Diccionario.
     * 2. Si no existe, lanza error.
     * 3. Si ya está inscrito, lanza error.
     * 4. Si hay cupo -> Lo mete a la lista de inscritos.
     * 5. Si NO hay cupo -> Lo manda a la lista de espera.
     * * @param estudiante El objeto estudiante que viene del Árbol (Persona 4)
     * @param claveCurso La clave del curso donde se quiere meter (ej: "ED-001")
     * @throws CursoNoEncontradoException Si la clave está mal.
     * @throws EstudianteYaInscritoException Si el alumno ya está en lista.
     */
    public void inscribirEstudiante(Estudiante estudiante, String claveCurso) 
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
        } 
        else 
        {
            curso.agregarAEspera(estudiante);
        }
    }
}