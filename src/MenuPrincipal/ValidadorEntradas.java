package MenuPrincipal;

/**
 * ValidadorEntradas
 * -----------------
 * Clase utilitaria para validar entradas de texto en el sistema.
 * Contiene métodos estáticos para validar datos de estudiantes, cursos y calificaciones.
 *
 * @author Roberto
 * @version 2.0
 */
public class ValidadorEntradas {

    // ===== Estudiantes =====
    public static boolean esMatriculaValida(String matricula) {
        return matricula != null && !matricula.trim().isEmpty();
    }

    public static boolean esNombreValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d+");
    }

    public static boolean esDireccionValida(String direccion) {
        return direccion != null && direccion.trim().length() >= 5;
    }

    // ===== Cursos =====
    public static boolean esClaveCursoValida(String clave) {
        return clave != null && !clave.trim().isEmpty();
    }

    public static boolean esNombreCursoValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    // ===== Calificaciones =====
    public static boolean esCalificacionValida(String calificacion) {
        if (calificacion == null || !calificacion.matches("\\d+")) return false;
        int valor = Integer.parseInt(calificacion);
        return valor >= 0 && valor <= 100;
    }

    public static boolean esSolicitudValida(String solicitud) {
        return solicitud != null && !solicitud.trim().isEmpty();
    }
}