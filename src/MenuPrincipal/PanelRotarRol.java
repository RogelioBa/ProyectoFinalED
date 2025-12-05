package MenuPrincipal;

import Listas.ListaCircularSimple;
import Estudiante.SistemaGestionEstudiantes;
import Estudiante.Estudiante;
import javax.swing.*;
import java.awt.*;

public class PanelRotarRol extends JPanel {

    private JTextField txtMatricula;
    private JButton btnAgregar, btnRotar;
    private JTextArea resultado;

    // Dependencias
    private ListaCircularSimple listaCircular;
    private SistemaGestionEstudiantes sistema; 

    public PanelRotarRol(ListaCircularSimple listaCircular, SistemaGestionEstudiantes sistema) {
        this.listaCircular = listaCircular;
        this.sistema = sistema;
        
        setLayout(new BorderLayout(10, 10));

        // --- Panel Superior: Agregar a la lista ---
        JPanel panelControles = new JPanel(new GridLayout(2, 1, 5, 5));
        
        // Zona de agregar
        JPanel panelAgregar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAgregar.add(new JLabel("Matrícula para agregar rol:"));
        txtMatricula = new JTextField(10);
        panelAgregar.add(txtMatricula);
        btnAgregar = new JButton("Agregar a Roles");
        panelAgregar.add(btnAgregar);
        
        // Zona de rotar
        btnRotar = new JButton(">>> ROTAR TURNO (Siguiente Líder) >>>");
        btnRotar.setBackground(new Color(200, 230, 255)); // Color para destacar
        
        panelControles.add(panelAgregar);
        panelControles.add(btnRotar);

        // --- Área de Resultados ---
        resultado = new JTextArea();
        resultado.setEditable(false);
        resultado.setFont(new Font("SansSerif", Font.BOLD, 14));
        resultado.setBorder(BorderFactory.createTitledBorder("Líder / Tutor Actual"));

        add(panelControles, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);


        // 1. Botón Agregar a la lista circular
        btnAgregar.addActionListener(e -> {
            String mat = txtMatricula.getText().trim();
            if(mat.isEmpty()) return;

            // Buscamos en la base de datos general (BST)
            Estudiante est = sistema.bstEstudiantes.buscar(mat);
            
            if (est != null) {
                listaCircular.agregar(est);
                JOptionPane.showMessageDialog(this, "Agregado a la rotación: " + est.getNombreCompleto());
                txtMatricula.setText("");
                actualizarPantalla(); 
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 2. Botón Rotar
        btnRotar.addActionListener(e -> {
            if (listaCircular.estaVacia()) {
                JOptionPane.showMessageDialog(this, "La lista de roles está vacía.\nPrimero agrega estudiantes.", "Lista Vacía", JOptionPane.WARNING_MESSAGE);
                return;
            }
            listaCircular.rotar(); 
            actualizarPantalla();
        });
    }
    
    private void actualizarPantalla() {
        Estudiante actual = listaCircular.obtenerTutorActual();
        if (actual != null) {
            resultado.setText("\n   LÍDER ACTUAL: \n   " + 
                              actual.getNombreCompleto() + "\n   (" + actual.getMatricula() + ")");
        }
    }
}