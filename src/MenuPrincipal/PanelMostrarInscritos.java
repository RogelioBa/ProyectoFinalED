package MenuPrincipal;

import GestionCursos.GestionCursos;
import GestionCursos.Curso;
import Estudiante.Estudiante;
import Excepciones.CursoNoEncontradoException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelMostrarInscritos
 * ---------------------
 * Panel gráfico para mostrar los estudiantes inscritos en un curso.
 *
 * Usa la estructura {@link Listas.ListaEnlazadaSimple} para recorrer
 * los estudiantes inscritos.
 *
 * @author Roberto
 * @version 2.1
 */
public class PanelMostrarInscritos extends JPanel {

    private JTextField txtClaveCurso;
    private JButton btnMostrar;
    private JTextArea resultado;

    private GestionCursos gestionCursos;

    public PanelMostrarInscritos(GestionCursos gestionCursos) {
        this.gestionCursos = gestionCursos;
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campo y botón
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Clave del curso:"));
        txtClaveCurso = new JTextField();
        panelSuperior.add(txtClaveCurso);

        btnMostrar = new JButton("Mostrar inscritos");
        panelSuperior.add(btnMostrar);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón
        btnMostrar.addActionListener(e -> {
            String claveCurso = txtClaveCurso.getText();

            try {
                Curso curso = gestionCursos.buscarCurso(claveCurso);

                if (curso == null) {
                    throw new CursoNoEncontradoException(claveCurso);
                }

                if (curso.getInscritos().estaVacia()) {
                    resultado.setText("No hay estudiantes inscritos en este curso.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("--- INSCRITOS EN ").append(curso.getNombre()).append(" ---\n");
                    for (int i = 0; i < curso.getInscritos().getTamanio(); i++) {
                        Estudiante est = curso.getInscritos().get(i);
                        sb.append(est.getMatricula())
                          .append(" - ")
                          .append(est.getNombreCompleto())
                          .append("\n");
                    }
                    resultado.setText(sb.toString());
                }

            } catch (CursoNoEncontradoException ex) {
                resultado.setText(" Error: No se encontró el curso con clave " + claveCurso);
            } catch (Exception ex) {
                resultado.setText(" Error inesperado: " + ex.getMessage());
            }
        });
    }
}