package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelRotarRol
 * -------------
 * Panel para rotar el rol de tutor/líder entre los estudiantes.
 * Valida que existan estudiantes en la lista circular antes de rotar.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelRotarRol extends JPanel {

    private JButton btnRotar;
    private JTextArea resultado;

    /**
     * Constructor del panel de rotación de roles.
     */
    public PanelRotarRol() {
        setLayout(new BorderLayout(10, 10));

        btnRotar = new JButton("Rotar rol de tutor/líder");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnRotar, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnRotar.addActionListener(e -> {
            // Aquí se conectará con la lista circular simple
            boolean hayEstudiantes = true; // cambiar por lógica real

            if (!hayEstudiantes) {
                JOptionPane.showMessageDialog(this, "No hay estudiantes para rotar roles");
                return;
            }

            resultado.setText("Nuevo tutor/líder asignado:\n[pendiente]");
        });
    }
}