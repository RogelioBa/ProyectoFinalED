package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelAgregarEstudiante
 * ----------------------
 * Panel para registrar un nuevo estudiante en el sistema.
 * Incluye validaciones de matrícula, nombre, teléfono, correo y dirección.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelAgregarEstudiante extends JPanel {

    private JTextField txtMatricula, txtNombre, txtTelefono, txtCorreo, txtDireccion;
    private JButton btnRegistrar;

    /**
     * Constructor del panel de registro de estudiantes.
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

            if (!ValidadorEntradas.esMatriculaValida(matricula)) {
                JOptionPane.showMessageDialog(this, "Matrícula inválida");
                return;
            }
            if (!ValidadorEntradas.esNombreValido(nombre)) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
                return;
            }
            if (!ValidadorEntradas.esTelefonoValido(telefono)) {
                JOptionPane.showMessageDialog(this, "Teléfono inválido (solo números)");
                return;
            }
            if (!ValidadorEntradas.esCorreoValido(correo)) {
                JOptionPane.showMessageDialog(this, "Correo electrónico inválido");
                return;
            }
            if (!ValidadorEntradas.esDireccionValida(direccion)) {
                JOptionPane.showMessageDialog(this, "Dirección inválida");
                return;
            }

            // Aquí se conectará con SistemaGestionEstudiantes
            JOptionPane.showMessageDialog(this,
                "Estudiante registrado:\n" + nombre + " (" + matricula + ")");
        });
    }
}