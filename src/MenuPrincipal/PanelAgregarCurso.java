package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelAgregarCurso
 * -----------------
 * Panel para registrar un nuevo curso en el sistema.
 * Incluye validaciones de clave y nombre del curso.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelAgregarCurso extends JPanel {

    private JTextField txtClave, txtNombre;
    private JButton btnAgregar;

    /**
     * Constructor del panel de registro de cursos.
     */
    public PanelAgregarCurso() {
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Clave del curso:"));
        txtClave = new JTextField();
        add(txtClave);

        add(new JLabel("Nombre del curso:"));
        txtNombre = new JTextField();
        add(txtNombre);

        btnAgregar = new JButton("Agregar curso");
        add(new JLabel()); // espacio vacío
        add(btnAgregar);

        // Acción del botón con validaciones
        btnAgregar.addActionListener(e -> {
            String clave = txtClave.getText();
            String nombre = txtNombre.getText();

            if (!ValidadorEntradas.esClaveCursoValida(clave)) {
                JOptionPane.showMessageDialog(this, "Clave de curso inválida");
                return;
            }
            if (!ValidadorEntradas.esNombreCursoValido(nombre)) {
                JOptionPane.showMessageDialog(this, "El nombre del curso no puede estar vacío");
                return;
            }

            // Aquí se conectará con GestionCursos
            JOptionPane.showMessageDialog(this,
                "Curso agregado:\n" + nombre + " (" + clave + ")");
        });
    }
}