package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelAgregarEstudiante
 * ----------------------
 * Este panel permite registrar un nuevo estudiante en el sistema.
 * Contiene campos para matrícula, nombre, teléfono, correo y dirección.
 * Al presionar el botón "Registrar", se validan los datos y se envían
 * al módulo de gestión de estudiantes.
 *
 * @author Roberto
 * @version 1.0
 */
public class PanelAgregarEstudiante extends JPanel {

    private JTextField txtMatricula, txtNombre, txtTelefono, txtCorreo, txtDireccion;
    private JButton btnRegistrar;

    /**
     * Constructor del panel de registro de estudiantes.
     * Inicializa los componentes gráficos y define la acción del botón.
     */
    public PanelAgregarEstudiante() {
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Nombre completo:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        add(new JLabel("Correo electrónico:"));
        txtCorreo = new JTextField();
        add(txtCorreo);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        btnRegistrar = new JButton("Registrar");
        add(new JLabel()); // espacio vacío
        add(btnRegistrar);

        // Acción del botón
        btnRegistrar.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();

            // Aquí se conectará con SistemaGestionEstudiantes
            JOptionPane.showMessageDialog(this,
                "Estudiante registrado:\n" + nombre + " (" + matricula + ")");
        });
    }
}