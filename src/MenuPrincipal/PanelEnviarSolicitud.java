package MenuPrincipal;

import Solicitudes.SolicitudCalificacion;
import Solicitudes.SolicitudesDeCalificacion;

import javax.swing.*;
import java.awt.*;

/**
 * PanelEnviarSolicitud
 * --------------------
 * Panel gráfico para enviar solicitudes de calificación.
 *
 * Este panel contiene:
 * - Un campo de texto para ingresar la matrícula del estudiante.
 * - Un campo de texto para ingresar la calificación.
 * - Un botón "Enviar solicitud" para encolar la petición.
 *
 * Al presionar el botón, se crea un objeto
 * {@link SolicitudCalificacion} y se agrega a la cola
 * {@link SolicitudesDeCalificacion}.
 *
 * El resultado se muestra en un área de texto confirmando que la
 * solicitud fue encolada correctamente.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelEnviarSolicitud extends JPanel {

    // Campos de entrada
    private JTextField txtMatricula, txtCalificacion;
    private JButton btnEnviar;
    private JTextArea resultado;

    // Referencia a la cola de solicitudes
    private SolicitudesDeCalificacion<SolicitudCalificacion> colaSolicitudes;

    /**
     * Constructor del panel de envío de solicitudes.
     *
     * @param colaSolicitudes Instancia de {@link SolicitudesDeCalificacion}
     *                        que almacena las solicitudes de calificación.
     */
    public PanelEnviarSolicitud(SolicitudesDeCalificacion<SolicitudCalificacion> colaSolicitudes) {
        this.colaSolicitudes = colaSolicitudes;
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campos y botón
        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        panelSuperior.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        panelSuperior.add(txtMatricula);

        panelSuperior.add(new JLabel("Calificación:"));
        txtCalificacion = new JTextField();
        panelSuperior.add(txtCalificacion);

        btnEnviar = new JButton("Enviar solicitud");
        panelSuperior.add(new JLabel()); // espacio vacío
        panelSuperior.add(btnEnviar);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con integración a la lógica
        btnEnviar.addActionListener(e -> {
            try {
                String matricula = txtMatricula.getText();
                float calificacion = Float.parseFloat(txtCalificacion.getText());

                SolicitudCalificacion solicitud = new SolicitudCalificacion(matricula, calificacion);
                colaSolicitudes.encolar(solicitud);

                resultado.setText(" Solicitud encolada correctamente:\n" +
                        "Matrícula: " + matricula + "\n" +
                        "Calificación: " + calificacion);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, " Error: La calificación debe ser un número válido");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, " Error inesperado: " + ex.getMessage());
            }
        });
    }
}