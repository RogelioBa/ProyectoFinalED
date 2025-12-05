package MenuPrincipal;

import GestionCursos.GestionCursos;
import Estudiante.SistemaGestionEstudiantes;
import Estudiante.Estudiante;
import Excepciones.CursoNoEncontradoException;
import Excepciones.EstudianteYaInscritoException;

import javax.swing.*;
import java.awt.*;

/**
 * PanelInscribirEstudiante
 * ------------------------
 * Panel gráfico para inscribir un estudiante en un curso.
 *
 * Este panel contiene:
 * - Campo de matrícula del estudiante.
 * - Campo de clave del curso.
 * - Botón "Inscribir" para ejecutar la acción.
 * - Área de texto para mostrar resultados.
 *
 * Al presionar el botón:
 * 1. Se busca el estudiante en el BST.
 * 2. Se llama a {@link GestionCursos#inscribirEstudiante(Estudiante, String)}.
 * 3. Se muestra si quedó inscrito o en lista de espera.
 *
 * @author Roberto
 * @version 2.0
 */
public class PanelInscribirEstudiante extends JPanel 
{

    private JTextField txtMatricula, txtClaveCurso;
    private JButton btnInscribir;
    private JTextArea resultado;

    private SistemaGestionEstudiantes sistema;
    private GestionCursos gestionCursos;

    public PanelInscribirEstudiante(SistemaGestionEstudiantes sistema, GestionCursos gestionCursos) 
    {
        this.sistema = sistema;
        this.gestionCursos = gestionCursos;
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campos
        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        panelSuperior.add(new JLabel("Matrícula del estudiante:"));
        txtMatricula = new JTextField();
        panelSuperior.add(txtMatricula);

        panelSuperior.add(new JLabel("Clave del curso:"));
        txtClaveCurso = new JTextField();
        panelSuperior.add(txtClaveCurso);

        btnInscribir = new JButton("Inscribir");
        panelSuperior.add(new JLabel()); // espacio vacío
        panelSuperior.add(btnInscribir);

        // Área de resultados
        resultado = new JTextArea();
        resultado.setEditable(false);
        resultado.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fuente más legible

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Acción del botón
        btnInscribir.addActionListener(e -> 
        {
            String matricula = txtMatricula.getText().trim();
            String claveCurso = txtClaveCurso.getText().trim();

            // Validación visual básica
            if(matricula.isEmpty() || claveCurso.isEmpty())
            {
                resultado.setText("⚠️ Por favor llene todos los campos.");
                return;
            }

            try 
            {
                Estudiante estudiante = sistema.bstEstudiantes.buscar(matricula);

                if (estudiante == null) 
                {
                    resultado.setText("Error: Estudiante con matrícula " + matricula + " no encontrado.");
                    return;
                }
                
                String mensajeResultado = gestionCursos.inscribirEstudiante(estudiante, claveCurso);
                
                resultado.setText(mensajeResultado);

            } 
            catch (CursoNoEncontradoException ex) 
            {
                resultado.setText("Error: No se encontró el curso con clave " + claveCurso);
            } 
            catch (EstudianteYaInscritoException ex) 
            {
                resultado.setText("Aviso: " + ex.getMessage());
            } 
            catch (Exception ex) 
            {
                resultado.setText("Error inesperado: " + ex.getMessage());
            }
        });
    }
}