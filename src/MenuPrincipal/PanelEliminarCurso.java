package MenuPrincipal;

import GestionCursos.GestionCursos;
import Excepciones.CursoNoEncontradoException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelEliminarCurso
 * ------------------
 * Panel gráfico para eliminar un curso del catálogo.
 *
 * Este panel contiene:
 * - Un campo de texto para ingresar la clave del curso.
 * - Un botón "Eliminar" para ejecutar la acción.
 * - Un área de texto para mostrar mensajes de confirmación o error.
 *
 * Al presionar el botón, se invoca el método
 * {@link GestionCursos#eliminarCurso(String)} que elimina el curso
 * del catálogo. Si la clave no existe, se lanza la excepción
 * {@link CursoNoEncontradoException}.
 *
 * El resultado se muestra en el área de texto del panel.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelEliminarCurso extends JPanel {

    // Campos de entrada y salida
    private JTextField txtClave;
    private JButton btnEliminar;
    private JTextArea resultado;

    // Referencia al sistema de gestión de cursos
    private GestionCursos gestionCursos;

    /**
     * Constructor del panel de eliminación de cursos.
     *
     * @param gestionCursos Instancia de {@link GestionCursos} que gestiona
     *                      la lógica de eliminación de cursos.
     */
    public PanelEliminarCurso(GestionCursos gestionCursos) {
        this.gestionCursos = gestionCursos;
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campo y botón
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Clave del curso:"));
        txtClave = new JTextField();
        panelSuperior.add(txtClave);

        btnEliminar = new JButton("Eliminar");
        panelSuperior.add(btnEliminar);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con integración a la lógica
        btnEliminar.addActionListener(e -> {
            String clave = txtClave.getText();

            if (clave == null || clave.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Clave inválida");
                return;
            }

            try {
                gestionCursos.eliminarCurso(clave);
                resultado.setText(" Curso eliminado correctamente: " + clave);
            } catch (CursoNoEncontradoException ex) {
                resultado.setText(" Error: No se encontró el curso con clave " + clave);
            } catch (Exception ex) {
                resultado.setText(" Error inesperado: " + ex.getMessage());
            }
        });
    }
}