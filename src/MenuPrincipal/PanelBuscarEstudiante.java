package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelBuscarEstudiante
 * ---------------------
 * Panel para buscar un estudiante por matrícula en el sistema.
 * Incluye validación de matrícula antes de invocar el módulo de gestión.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelBuscarEstudiante extends JPanel {

    private JTextField txtMatricula;
    private JButton btnBuscar;
    private JTextArea resultado;

    /**
     * Constructor del panel de búsqueda de estudiantes.
     */
    public PanelBuscarEstudiante() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        panelSuperior.add(txtMatricula);

        btnBuscar = new JButton("Buscar");
        panelSuperior.add(btnBuscar);

        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnBuscar.addActionListener(e -> {
            String matricula = txtMatricula.getText();

            if (!ValidadorEntradas.esMatriculaValida(matricula)) {
                JOptionPane.showMessageDialog(this, "Matrícula inválida");
                return;
            }

            // Aquí se conectará con SistemaGestionEstudiantes
            resultado.setText("Resultado de búsqueda para matrícula: " + matricula);
        });
    }
}