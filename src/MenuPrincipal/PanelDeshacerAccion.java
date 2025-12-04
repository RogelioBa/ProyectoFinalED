package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * PanelDeshacerAccion
 * -------------------
 * Panel para deshacer la última acción realizada en el sistema.
 * Valida que existan acciones en la pila antes de intentar revertir.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelDeshacerAccion extends JPanel {

    private JButton btnDeshacer;
    private JTextArea resultado;

    /**
     * Constructor del panel de deshacer acción.
     */
    public PanelDeshacerAccion() {
        setLayout(new BorderLayout(10, 10));

        btnDeshacer = new JButton("Deshacer última acción");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnDeshacer, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnDeshacer.addActionListener(e -> {
            // Aquí se conectará con la pila de acciones
            boolean hayAcciones = true; // cambiar por lógica real

            if (!hayAcciones) {
                JOptionPane.showMessageDialog(this, "No hay acciones para deshacer");
                return;
            }

            resultado.setText("Última acción deshecha:\n[pendiente]");
        });
    }
}