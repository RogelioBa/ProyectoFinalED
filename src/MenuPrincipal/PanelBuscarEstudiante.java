package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelBuscarEstudiante
 * ---------------------
 * Este panel permite buscar un estudiante por matrícula en el sistema.
 * Muestra los datos completos del estudiante si existe, o un mensaje
 * de error si no se encuentra.
 *
 * @author Roberto
 * @version 1.0
 */
public class PanelBuscarEstudiante extends JPanel {

    private JTextField txtMatricula;
    private JButton btnBuscar;
    private JTextArea resultado;

    /**
     * Constructor del panel de búsqueda de estudiantes.
     * Inicializa los componentes gráficos y define la acción del botón.
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

        // Acción del botón
        btnBuscar.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            // Aquí se conectará con SistemaGestionEstudiantes
            resultado.setText("Resultado de búsqueda para matrícula: " + matricula);
        });
    }
}