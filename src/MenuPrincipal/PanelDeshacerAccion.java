package MenuPrincipal;

import Deshacer.PilaAcciones;
import Deshacer.Accion;
import Excepciones.PilaException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelDeshacerAccion
 * -------------------
 * Panel para deshacer la última acción realizada en el sistema.
 * Valida que existan acciones en la pila antes de intentar revertir.
 *
 * @author Roberto
 * @version 3.0
 */
public class PanelDeshacerAccion extends JPanel {

    private JButton btnDeshacer;
    private JTextArea resultado;

    // Referencia a la pila de acciones
    private PilaAcciones pila;

    /**
     * Constructor del panel de deshacer acción.
     *
     * @param pila instancia de {@link PilaAcciones} que almacena las acciones realizadas
     */
    public PanelDeshacerAccion(PilaAcciones pila) {
        this.pila = pila;
        setLayout(new BorderLayout(10, 10));

        btnDeshacer = new JButton("Deshacer última acción");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnDeshacer, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnDeshacer.addActionListener(e -> {
            try {
                if (pila.empty()) {
                    JOptionPane.showMessageDialog(this, "No hay acciones para deshacer");
                    return;
                }

                Accion accion = pila.pop();
                resultado.setText("✔ Última acción deshecha:\n" +
                        "Tipo: " + accion.getTipo() + "\n" +
                        "Valor previo: " + accion.getDatoPrevio() + "\n" +
                        "Valor nuevo: " + accion.getDatoNuevo());
            } catch (PilaException ex) {
                resultado.setText("❌ Error: " + ex.getMessage());
            } catch (Exception ex) {
                resultado.setText("⚠ Error inesperado: " + ex.getMessage());
            }
        });
    }
}