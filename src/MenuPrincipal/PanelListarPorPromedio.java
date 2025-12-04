package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelListarPorPromedio
 * ----------------------
 * Panel para listar estudiantes ordenados por promedio.
 * Valida que existan estudiantes antes de mostrar resultados.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelListarPorPromedio extends JPanel {

    private JButton btnListar;
    private JTextArea resultado;

    /**
     * Constructor del panel de listado por promedio.
     */
    public PanelListarPorPromedio() {
        setLayout(new BorderLayout(10, 10));

        btnListar = new JButton("Listar estudiantes por promedio");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnListar, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnListar.addActionListener(e -> {
            // Aquí se conectará con el AVL de promedios
            boolean hayEstudiantes = true; // cambiar por lógica real

            if (!hayEstudiantes) {
                JOptionPane.showMessageDialog(this, "No hay estudiantes registrados");
                return;
            }

            resultado.setText("Listado de estudiantes ordenados por promedio:\n[pendiente]");
        });
    }
}