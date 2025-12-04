package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelInscribirEstudiante
 * ------------------------
 * Panel para inscribir un estudiante en un curso.
 * Valida matrícula y clave del curso antes de invocar el módulo de gestión.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelInscribirEstudiante extends JPanel {

    private JTextField txtMatricula, txtClaveCurso;
    private JButton btnInscribir;

    /**
     * Constructor del panel de inscripción de estudiantes.
     */
    public PanelInscribirEstudiante() {
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Matrícula del estudiante:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Clave del curso:"));
        txtClaveCurso = new JTextField();
        add(txtClaveCurso);

        btnInscribir = new JButton("Inscribir estudiante");
        add(new JLabel()); // espacio vacío
        add(btnInscribir);

        // Acción del botón con validaciones
        btnInscribir.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            String claveCurso = txtClaveCurso.getText();

            if (!ValidadorEntradas.esMatriculaValida(matricula)) {
                JOptionPane.showMessageDialog(this, "Matrícula inválida");
                return;
            }
            if (!ValidadorEntradas.esClaveCursoValida(claveCurso)) {
                JOptionPane.showMessageDialog(this, "Clave de curso inválida");
                return;
            }

            // Aquí se conectará con GestionCursos
            JOptionPane.showMessageDialog(this,
                "Estudiante " + matricula + " inscrito en curso " + claveCurso);
        });
    }
}