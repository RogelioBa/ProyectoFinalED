package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelListarPorPromedio
 * ----------------------
 * Este panel permite listar los estudiantes ordenados por promedio.
 * Utiliza un árbol AVL para mantener el orden ascendente de los promedios.
 * El resultado se muestra en un área de texto.
 *
 * @author Roberto
 * @version 1.0
 */
public class PanelListarPorPromedio extends JPanel {

    private JButton btnListar;
    private JTextArea resultado;

    /**
     * Constructor del panel de listado por promedio.
     * Inicializa los componentes gráficos y define la acción del botón.
     */
    public PanelListarPorPromedio() {
        setLayout(new BorderLayout(10, 10));

        btnListar = new JButton("Listar estudiantes por promedio");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnListar, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón
        btnListar.addActionListener(e -> {
            // Aquí se conectará con el AVL de promedios
            resultado.setText("Listado de estudiantes ordenados por promedio:\n[pendiente]");
        });
    }
}