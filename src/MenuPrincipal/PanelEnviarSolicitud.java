package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelEnviarSolicitud
 * --------------------
 * Panel para enviar una solicitud de calificación.
 * Valida que la matrícula del estudiante y la solicitud sean correctas.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelEnviarSolicitud extends JPanel {

    private JTextField txtMatricula, txtSolicitud;
    private JButton btnEnviar;

    /**
     * Constructor del panel de envío de solicitudes de calificación.
     */
    public PanelEnviarSolicitud() {
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Matrícula del estudiante:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Solicitud de calificación:"));
        txtSolicitud = new JTextField();
        add(txtSolicitud);

        btnEnviar = new JButton("Enviar solicitud");
        add(new JLabel()); // espacio vacío
        add(btnEnviar);

        // Acción del botón con validaciones
        btnEnviar.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            String solicitud = txtSolicitud.getText();

            if (!ValidadorEntradas.esMatriculaValida(matricula)) {
                JOptionPane.showMessageDialog(this, "Matrícula inválida");
                return;
            }
            if (!ValidadorEntradas.esSolicitudValida(solicitud)) {
                JOptionPane.showMessageDialog(this, "La solicitud no puede estar vacía");
                return;
            }

            // Aquí se conectará con ProcesadorCalificaciones
            JOptionPane.showMessageDialog(this,
                "Solicitud enviada para estudiante " + matricula + ": " + solicitud);
        });
    }
}