package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelEliminarCurso
 * ------------------
 * Panel para eliminar un curso existente del sistema.
 * Incluye validación de clave del curso antes de invocar el módulo de gestión.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelEliminarCurso extends JPanel {

    private JTextField txtClave;
    private JButton btnEliminar;

    /**
     * Constructor del panel de eliminación de cursos.
     */
    public PanelEliminarCurso() {
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Clave del curso:"));
        txtClave = new JTextField();
        add(txtClave);

        btnEliminar = new JButton("Eliminar curso");
        add(new JLabel()); // espacio vacío
        add(btnEliminar);

        // Acción del botón con validaciones
        btnEliminar.addActionListener(e -> {
            String clave = txtClave.getText();

            if (!ValidadorEntradas.esClaveCursoValida(clave)) {
                JOptionPane.showMessageDialog(this, "Clave de curso inválida");
                return;
            }

            // Aquí se conectará con GestionCursos
            JOptionPane.showMessageDialog(this,
                "Curso eliminado con clave: " + clave);
        });
    }
}