package MenuPrincipal;

import GestionCursos.GestionCursos;
import GestionCursos.Curso;
import Estudiante.Estudiante;
import Excepciones.CursoNoEncontradoException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelMostrarListaEspera
 * -----------------------
 * Panel gráfico para mostrar los estudiantes en lista de espera de un curso.
 *
 * Usa la estructura {@link Listas.ListaDobleCircular} para obtener
 * el listado de estudiantes en espera.
 *
 * @author Roberto
 * @version 2.1
 */
public class PanelMostrarListaEspera extends JPanel 
{

    private JTextField txtClaveCurso;
    private JButton btnMostrar;
    private JTextArea resultado;

    private GestionCursos gestionCursos;

    public PanelMostrarListaEspera(GestionCursos gestionCursos) {
        this.gestionCursos = gestionCursos;
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campo y botón
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Clave del curso:"));
        txtClaveCurso = new JTextField();
        panelSuperior.add(txtClaveCurso);

        btnMostrar = new JButton("Mostrar lista de espera");
        panelSuperior.add(btnMostrar);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón
        btnMostrar.addActionListener(e -> 
        {
            String claveCurso = txtClaveCurso.getText();
            try 
            {
                String listado = gestionCursos.obtenerListaEspera(claveCurso);
                resultado.setText(listado);
            } 
            catch (CursoNoEncontradoException ex) 
            {
                resultado.setText("Error: " + ex.getMessage());
            } 
            catch (Exception ex) 
            {
                resultado.setText("Error inesperado: " + ex.getMessage());
            }
        });
    }
}