package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelProcesarSolicitud
 * ----------------------
 * Panel para procesar la siguiente solicitud de calificación en la cola FIFO.
 * Valida que existan solicitudes pendientes antes de procesar.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelProcesarSolicitud extends JPanel {

    private JButton btnProcesar;
    private JTextArea resultado;

    /**
     * Constructor del panel de procesamiento de solicitudes.
     */
    public PanelProcesarSolicitud() {
        setLayout(new BorderLayout(10, 10));

        btnProcesar = new JButton("Procesar siguiente solicitud");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnProcesar, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnProcesar.addActionListener(e -> {
            // Aquí se conectará con la cola de solicitudes
            boolean haySolicitudes = true; // cambiar por lógica real

            if (!haySolicitudes) {
                JOptionPane.showMessageDialog(this, "No hay solicitudes pendientes");
                return;
            }

            resultado.setText("Solicitud procesada:\n[pendiente]");
        });
    }
}