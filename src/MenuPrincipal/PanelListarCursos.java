package MenuPrincipal;

import GestionCursos.GestionCursos;

import javax.swing.*;
import java.awt.*;

/**
 * PanelListarCursos
 * -----------------
 * Panel gráfico para mostrar todos los cursos registrados en el catálogo.
 *
 * Este panel contiene:
 * - Un botón "Listar cursos" para ejecutar la acción.
 * - Un área de texto para mostrar los resultados.
 *
 * Al presionar el botón, se invoca el método
 * {@link GestionCursos#listarCursos()} que imprime el catálogo de cursos.
 *
 * La salida se captura y se muestra en el área de texto del panel.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelListarCursos extends JPanel {

    // Botón de acción y área de resultados
    private JButton btnListar;
    private JTextArea resultado;

    // Referencia al sistema de gestión de cursos
    private GestionCursos gestionCursos;

    /**
     * Constructor del panel de listado de cursos.
     *
     * @param gestionCursos Instancia de {@link GestionCursos} que gestiona
     *                      la lógica de listado de cursos.
     */
    public PanelListarCursos(GestionCursos gestionCursos) {
        this.gestionCursos = gestionCursos;
        setLayout(new BorderLayout(10, 10));

        // Botón
        btnListar = new JButton("Listar cursos");
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

            gestionCursos.listarCursos();

            System.out.flush();
            System.setOut(oldOut);

            resultado.setText(buffer.toString());
        });
    }
}