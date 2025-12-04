package MenuPrincipal;

import GestionCursos.GestionCursos;

import javax.swing.*;
import java.awt.*;

/**
 * PanelAgregarCurso
 * -----------------
 * Panel gráfico para registrar un nuevo curso en el catálogo.
 *
 * Este panel contiene campos de texto para capturar:
 * - Clave del curso
 * - Nombre del curso
 * - Capacidad máxima
 *
 * Al presionar el botón "Agregar", se invoca el método
 * {@link GestionCursos#agregarCurso(String, String, int)}
 * para insertar el curso en el catálogo.
 *
 * En caso de error (curso duplicado o datos inválidos), se muestra un mensaje
 * en pantalla mediante {@link JOptionPane}.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelAgregarCurso extends JPanel {

    // Campos de entrada
    private JTextField txtClave, txtNombre, txtCapacidad;
    private JButton btnAgregar;

    // Referencia al sistema de gestión de cursos
    private GestionCursos gestionCursos;

    /**
     * Constructor del panel de registro de cursos.
     *
     * @param gestionCursos Instancia de {@link GestionCursos} que gestiona
     *                      la lógica de inserción y validación de cursos.
     */
    public PanelAgregarCurso(GestionCursos gestionCursos) {
        this.gestionCursos = gestionCursos;
        setLayout(new GridLayout(4, 2, 10, 10));

        // Etiquetas y campos
        add(new JLabel("Clave del curso:"));
        txtClave = new JTextField();
        add(txtClave);

        add(new JLabel("Nombre del curso:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Capacidad máxima:"));
        txtCapacidad = new JTextField();
        add(txtCapacidad);

        // Botón de acción
        btnAgregar = new JButton("Agregar");
        add(new JLabel()); // espacio vacío
        add(btnAgregar);

        // Acción del botón con integración a la lógica
        btnAgregar.addActionListener(e -> {
            try {
                String clave = txtClave.getText();
                String nombre = txtNombre.getText();
                int capacidad = Integer.parseInt(txtCapacidad.getText());

                boolean agregado = gestionCursos.agregarCurso(clave, nombre, capacidad);

                if (agregado) {
                    JOptionPane.showMessageDialog(this, " Curso agregado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, " Error: Ya existe un curso con esa clave");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, " Error: La capacidad debe ser un número entero");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, " Error inesperado: " + ex.getMessage());
            }
        });
    }
}