package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelMostrarListaEspera
 * -----------------------
 * Panel para mostrar la lista de espera de un curso lleno.
 * Valida la clave del curso antes de invocar la lista doble circular.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelMostrarListaEspera extends JPanel {

    private JTextField txtClaveCurso;
    private JButton btnMostrar;
    private JTextArea resultado;

    /**
     * Constructor del panel de lista de espera.
     */
    public PanelMostrarListaEspera() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Clave del curso:"));
        txtClaveCurso = new JTextField();
        panelSuperior.add(txtClaveCurso);

        btnMostrar = new JButton("Mostrar lista de espera");
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

            // Aquí se conectará con la lista doble circular de espera
            resultado.setText("Lista de espera en curso " + claveCurso + ":\n[pendiente]");
        });
    }
}