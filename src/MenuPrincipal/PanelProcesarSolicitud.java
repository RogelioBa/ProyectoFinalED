package MenuPrincipal;

import Estudiante.SistemaGestionEstudiantes;
import Estudiante.Estudiante;
import Solicitudes.SolicitudCalificacion;
import Solicitudes.SolicitudesDeCalificacion;
import Excepciones.ColaException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelProcesarSolicitud
 * ----------------------
 * Panel gráfico para procesar solicitudes de calificación.
 *
 * Este panel contiene:
 * - Un botón "Procesar solicitud" para desencolar la siguiente petición.
 * - Un área de texto para mostrar los resultados.
 *
 * Al presionar el botón, se extrae un objeto
 * {@link SolicitudCalificacion} desde la cola
 * {@link SolicitudesDeCalificacion} y se aplica la operación:
 * - Si la solicitud no tiene índice → inserción de calificación.
 * - Si la solicitud tiene índice → modificación de calificación.
 *
 * El resultado se muestra en el área de texto del panel.
 *
 * @author Roberto
 * @version 2.1
 */
public class PanelProcesarSolicitud extends JPanel {

    // Botón de acción y área de resultados
    private JButton btnProcesar;
    private JTextArea resultado;

    // Referencias al sistema y a la cola
    private SistemaGestionEstudiantes sistema;
    private SolicitudesDeCalificacion<SolicitudCalificacion> colaSolicitudes;

    /**
     * Constructor del panel de procesamiento de solicitudes.
     *
     * @param sistema Instancia de {@link SistemaGestionEstudiantes} que gestiona
     *                la lógica de estudiantes y calificaciones.
     * @param colaSolicitudes Instancia de {@link SolicitudesDeCalificacion}
     *                        que almacena las solicitudes pendientes.
     */
    public PanelProcesarSolicitud(SistemaGestionEstudiantes sistema,
                                  SolicitudesDeCalificacion<SolicitudCalificacion> colaSolicitudes) {
        this.sistema = sistema;
        this.colaSolicitudes = colaSolicitudes;
        setLayout(new BorderLayout(10, 10));

        // Botón
        btnProcesar = new JButton("Procesar solicitud");
        add(btnProcesar, BorderLayout.NORTH);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con integración a la lógica
        btnProcesar.addActionListener(e -> {
            try {
                if (colaSolicitudes.empty()) {
                    resultado.setText("⚠ No hay solicitudes en la cola.");
                    return;
                }

                SolicitudCalificacion solicitud = colaSolicitudes.desencolar();
                String matricula = solicitud.getMatricula();
                Estudiante estudiante = sistema.bstEstudiantes.buscar(matricula);

                if (estudiante == null) {
                    resultado.setText("❌ Error: Estudiante con matrícula " + matricula + " no encontrado.");
                    return;
                }

                if (solicitud.getIndice() == null) {
                    // Inserción de calificación (convertimos float → double)
                    estudiante.agregarCalificacion((double) solicitud.getCalificacion());
                    resultado.setText("✔ Calificación agregada:\n" +
                            "Matrícula: " + matricula + "\n" +
                            "Nueva calificación: " + solicitud.getCalificacion());
                } else {
                    // Modificación de calificación
                    int indice = solicitud.getIndice();
                    if (indice >= 0 && indice < estudiante.getCalificaciones().size()) {
                        estudiante.getCalificaciones().set(indice, (double) solicitud.getCalificacion());
                        resultado.setText("✔ Calificación modificada:\n" +
                                "Matrícula: " + matricula + "\n" +
                                "Índice: " + indice + "\n" +
                                "Nuevo valor: " + solicitud.getCalificacion());
                    } else {
                        resultado.setText("❌ Error: Índice inválido para modificar calificación.");
                    }
                }
            } catch (ColaException ex) {
                resultado.setText("❌ Error: " + ex.getMessage());
            } catch (Exception ex) {
                resultado.setText("⚠ Error inesperado: " + ex.getMessage());
            }
        });
    }
}