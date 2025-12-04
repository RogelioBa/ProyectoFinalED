package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelListarCursos
 * -----------------
 * Panel para mostrar todos los cursos registrados en el sistema.
 * Valida que existan cursos antes de mostrar resultados.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelListarCursos extends JPanel {

    private JButton btnListar;
    private JTextArea resultado;

    /**
     * Constructor del panel de listado de cursos.
     */
    public PanelListarCursos() {
        setLayout(new BorderLayout(10, 10));

        btnListar = new JButton("Listar cursos");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnListar, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnListar.addActionListener(e -> {
            // Aquí se conectará con GestionCursos
            boolean hayCursos = true; // cambiar por lógica real

            if (!hayCursos) {
                JOptionPane.showMessageDialog(this, "No hay cursos registrados");
                return;
            }

            resultado.setText("Listado de cursos:\n[pendiente]");
        });
    }
}