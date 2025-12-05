package MenuPrincipal;

import Listas.ListaCircularSimple;
import Estudiante.Estudiante;

import javax.swing.*;
import java.awt.*;

/**
 * PanelRotarRol
 * -------------
 * Panel para rotar el rol de tutor/líder entre los estudiantes.
 * Utiliza una lista circular simple para avanzar al siguiente estudiante.
 *
 * @author Roberto
 * @version 2.1
 */
public class PanelRotarRol extends JPanel {

    private JButton btnRotar;
    private JTextArea resultado;

    // Referencia a la lista circular de estudiantes
    private ListaCircularSimple listaCircular;

    /**
     * Constructor del panel de rotación de roles.
     *
     * @param listaCircular Instancia de {@link ListaCircularSimple} que contiene
     *                      a los estudiantes con rol asignado.
     */
    public PanelRotarRol(ListaCircularSimple listaCircular) {
        this.listaCircular = listaCircular;
        setLayout(new BorderLayout(10, 10));

        btnRotar = new JButton("Rotar rol de tutor/líder");
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(btnRotar, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón con validación
        btnRotar.addActionListener(e -> {
            if (listaCircular.estaVacia()) {
                JOptionPane.showMessageDialog(this, "No hay estudiantes para rotar roles");
                return;
            }

            // Avanzar al siguiente estudiante
            listaCircular.rotar();
            Estudiante tutor = listaCircular.obtenerTutorActual();

            resultado.setText("Nuevo tutor/líder asignado:\n" +
                    tutor.getMatricula() + " - " + tutor.getNombreCompleto());
        });
    }
}