package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelMostrarInscritos
 * ---------------------
 * Panel para mostrar la lista de estudiantes inscritos en un curso.
 * Valida la clave del curso antes de invocar el módulo de gestión.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelMostrarInscritos extends JPanel {

    private JTextField txtClaveCurso;
    private JButton btnMostrar;
    private JTextArea resultado;

    /**
     * Constructor del panel de inscritos.
     */
    public PanelMostrarInscritos() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Clave del curso:"));
        txtClaveCurso = new JTextField();
        panelSuperior.add(txtClaveCurso);

        btnMostrar = new JButton("Mostrar inscritos");
        panelSuperior.add(btnMostrar);

        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validaciones
        btnMostrar.addActionListener(e -> {
            String claveCurso = txtClaveCurso.getText();

            if (!ValidadorEntradas.esClaveCursoValida(claveCurso)) {
                JOptionPane.showMessageDialog(this, "Clave de curso inválida");
                return;
            }

            // Aquí se conectará con GestionCursos
            resultado.setText("Lista de inscritos en curso " + claveCurso + ":\n[pendiente]");
        });
    }
}