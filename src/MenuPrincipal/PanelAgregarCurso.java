package MenuPrincipal;

import GestionCursos.GestionCursos;
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
public class PanelAgregarCurso extends JPanel 
{

    private JTextField txtClave, txtNombre, txtCapacidad;
    private JButton btnAgregar;
    private GestionCursos gestion;
    
    /**
     * Constructor del panel de registro de cursos.
     */
    public PanelAgregarCurso(GestionCursos gestionRecibida)
    {
        this.gestion = gestionRecibida;
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Clave del curso:"));
        txtClave = new JTextField();
        add(txtClave);

        add(new JLabel("Nombre del curso:"));
        txtNombre = new JTextField();
        add(txtNombre);
        
        add(new JLabel("Capacidad máxima:"));
        txtCapacidad = new JTextField();
        add(txtCapacidad);

        btnAgregar = new JButton("Agregar curso");
        add(new JLabel()); // espacio vacío
        add(btnAgregar);
        
        btnAgregar.addActionListener(e -> accionGuardar());
        
    }
    private void accionGuardar() 
    {
        // 1. Obtener datos 
        String clave = txtClave.getText().trim();
        String nombre = txtNombre.getText().trim();
        String capacidadStr = txtCapacidad.getText().trim();

        // 2. Validaciones de formulario
        if (clave.isEmpty() || nombre.isEmpty() || capacidadStr.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios.", 
                "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Validar que capacidad sea número
        int capacidad = 0;
        try {
            capacidad = Integer.parseInt(capacidadStr);
            if (capacidad <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "La capacidad debe ser mayor a 0.", 
                    "Error de Capacidad", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "La capacidad debe ser un número entero válido.", 
                "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si devuelve true es que se guardó, si es false es que ya existía.
        boolean guardado = gestion.agregarCurso(clave, nombre, capacidad);

        // 5. Retroalimentación al usuario
        if (guardado) {
            JOptionPane.showMessageDialog(this, 
                "Curso agregado exitosamente:\n" + nombre + " (" + clave + ")", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Error: Ya existe un curso con la clave '" + clave + "'.\nIntenta con otra clave.", 
                "Duplicado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtClave.setText("");
        txtNombre.setText("");
        txtCapacidad.setText("");
    }
}
