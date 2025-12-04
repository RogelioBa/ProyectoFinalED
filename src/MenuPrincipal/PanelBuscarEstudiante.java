package MenuPrincipal;

import Estudiante.SistemaGestionEstudiantes;

import javax.swing.*;
import java.awt.*;

/**
 * PanelBuscarEstudiante
 * ---------------------
 * Panel gráfico para buscar un estudiante en el sistema por matrícula.
 * 
 * Este panel contiene:
 * - Un campo de texto para ingresar la matrícula.
 * - Un botón "Buscar" para ejecutar la acción.
 * - Un área de texto para mostrar los resultados.
 * 
 * Al presionar el botón, se invoca el método
 * {@link SistemaGestionEstudiantes#buscarEstudiante(String)} que realiza la búsqueda
 * en el árbol binario de búsqueda (BST) de estudiantes.
 * 
 * El resultado se muestra en el área de texto. Si no se encuentra el estudiante,
 * se indica en el panel.
 * 
 * @author Roberto
 * @version 2.0
 */
public class PanelBuscarEstudiante extends JPanel {

    // Campos de entrada y salida
    private JTextField txtMatricula;
    private JButton btnBuscar;
    private JTextArea resultado;

    // Referencia al sistema de gestión de estudiantes
    private SistemaGestionEstudiantes sistema;

    /**
     * Constructor del panel de búsqueda de estudiantes.
     * 
     * @param sistema Instancia de {@link SistemaGestionEstudiantes} que gestiona
     *                la lógica de búsqueda de estudiantes.
     */
    public PanelBuscarEstudiante(SistemaGestionEstudiantes sistema) {
        this.sistema = sistema;
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campo y botón
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        panelSuperior.add(txtMatricula);

        btnBuscar = new JButton("Buscar");
        panelSuperior.add(btnBuscar);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con integración a la lógica
        btnBuscar.addActionListener(e -> {
            String matricula = txtMatricula.getText();

            if (matricula == null || matricula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Matrícula inválida");
                return;
            }

            // Capturar salida del sistema en el área de texto
            java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
            java.io.PrintStream ps = new java.io.PrintStream(buffer);
            java.io.PrintStream oldOut = System.out;
            System.setOut(ps);

            sistema.buscarEstudiante(matricula);

            System.out.flush();
            System.setOut(oldOut);

            resultado.setText(buffer.toString());
        });
    }
}