package MenuPrincipal;

import Estudiante.SistemaGestionEstudiantes;
import Excepciones.MatriculaInvalidaException;
import Excepciones.NombreInvalidoException;
import Excepciones.telefonoInvalidoException;
import Excepciones.correoInvalidoException;
import Excepciones.direccionVaciaException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelAgregarEstudiante
 * ----------------------
 * Panel gráfico para registrar un nuevo estudiante en el sistema.
 * 
 * Este panel contiene campos de texto para capturar:
 * - Matrícula
 * - Nombre completo
 * - Teléfono
 * - Correo electrónico
 * - Dirección
 * 
 * Al presionar el botón "Registrar", se validan los datos y se invoca
 * el método {@link SistemaGestionEstudiantes#registrarEstudiante(String, String, String, String, String)}
 * para insertar el estudiante en el BST y registrar la acción en la pila.
 * 
 * En caso de error de validación o excepción, se muestra un mensaje
 * en pantalla mediante {@link JOptionPane}.
 * 
 * @author Roberto
 * @version 2.0
 */
public class PanelAgregarEstudiante extends JPanel {

    // Campos de entrada
    private JTextField txtMatricula, txtNombre, txtTelefono, txtCorreo, txtDireccion;
    private JButton btnRegistrar;

    // Referencia al sistema de gestión de estudiantes
    private SistemaGestionEstudiantes sistema;

    /**
     * Constructor del panel de registro de estudiantes.
     * 
     * @param sistema Instancia de {@link SistemaGestionEstudiantes} que gestiona
     *                la lógica de inserción y validación de estudiantes.
     */
    public PanelAgregarEstudiante(SistemaGestionEstudiantes sistema) {
        this.sistema = sistema;
        setLayout(new GridLayout(6, 2, 10, 10));

        // Etiquetas y campos
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

        // Botón de acción
        btnRegistrar = new JButton("Registrar");
        add(new JLabel()); // espacio vacío
        add(btnRegistrar);

        // Acción del botón con integración a la lógica
        btnRegistrar.addActionListener(e -> {
            try {
                sistema.registrarEstudiante(
                    txtMatricula.getText(),
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText(),
                    txtDireccion.getText()
                );
                JOptionPane.showMessageDialog(this, "✔ Estudiante registrado correctamente");
            } catch (MatriculaInvalidaException | NombreInvalidoException |
                     telefonoInvalidoException | correoInvalidoException |
                     direccionVaciaException ex) {
                JOptionPane.showMessageDialog(this, " Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, " Error inesperado: " + ex.getMessage());
            }
        });
    }
}