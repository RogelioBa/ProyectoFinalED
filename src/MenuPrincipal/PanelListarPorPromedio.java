package MenuPrincipal;

import Estudiante.SistemaGestionEstudiantes;

import javax.swing.*;
import java.awt.*;

/**
 * PanelListarPorPromedio
 * ----------------------
 * Panel gráfico para mostrar el listado de estudiantes ordenados por promedio.
 * 
 * Este panel contiene:
 * - Un botón "Listar por promedio" para ejecutar la acción.
 * - Un área de texto para mostrar los resultados.
 * 
 * Al presionar el botón, se invoca el método
 * {@link SistemaGestionEstudiantes#listarEstudiantesOrdenadosPorPromedio()}
 * que recorre el BST de estudiantes, recalcula los promedios y los inserta en
 * un AVL para listarlos en orden ascendente.
 * 
 * El resultado se captura y se muestra en el área de texto del panel.
 * 
 * @author Roberto
 * @version 2.0
 */
public class PanelListarPorPromedio extends JPanel {

    // Botón de acción y área de resultados
    private JButton btnListar;
    private JTextArea resultado;

    // Referencia al sistema de gestión de estudiantes
    private SistemaGestionEstudiantes sistema;

    /**
     * Constructor del panel de listado por promedio.
     * 
     * @param sistema Instancia de {@link SistemaGestionEstudiantes} que gestiona
     *                la lógica de ordenamiento y listado de estudiantes.
     */
    public PanelListarPorPromedio(SistemaGestionEstudiantes sistema) {
        this.sistema = sistema;
        setLayout(new BorderLayout(10, 10));

        // Botón
        btnListar = new JButton("Listar por promedio");
        add(btnListar, BorderLayout.NORTH);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con integración a la lógica
        btnListar.addActionListener(e -> {
            // Capturar salida del sistema en el área de texto
            java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
            java.io.PrintStream ps = new java.io.PrintStream(buffer);
            java.io.PrintStream oldOut = System.out;
            System.setOut(ps);

            sistema.listarEstudiantesOrdenadosPorPromedio();

            System.out.flush();
            System.setOut(oldOut);

            resultado.setText(buffer.toString());
        });
    }
}