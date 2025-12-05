package MenuPrincipal;

import Estudiante.SistemaGestionEstudiantes;
import GestionCursos.GestionCursos;
import Solicitudes.SolicitudesDeCalificacion;

import javax.swing.*;
import java.awt.*;

/**
 * MenuPrincipal
 * -------------
 * Ventana principal con CardLayout para navegar entre los módulos:
 * - Inscribir estudiantes en cursos
 * - Mostrar inscritos
 * - Mostrar lista de espera
 * - Procesar solicitudes de calificación
 *
 * @author Roberto
 * @version 2.1
 */
public class MenuPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenedor;

    // Referencias a lógica
    private SistemaGestionEstudiantes sistema;
    private GestionCursos gestionCursos;
    private SolicitudesDeCalificacion colaSolicitudes;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión Académica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar lógica
        try {
            sistema = new SistemaGestionEstudiantes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar el sistema de estudiantes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        gestionCursos = new GestionCursos();
        colaSolicitudes = new SolicitudesDeCalificacion();

        // Layout principal
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Crear paneles
        PanelInscribirEstudiante panelInscribir = new PanelInscribirEstudiante(sistema, gestionCursos);
        PanelMostrarInscritos panelInscritos = new PanelMostrarInscritos(gestionCursos);
        PanelMostrarListaEspera panelListaEspera = new PanelMostrarListaEspera(gestionCursos);
        PanelProcesarSolicitud panelProcesar = new PanelProcesarSolicitud(sistema, colaSolicitudes);

        // Agregar paneles al contenedor
        panelContenedor.add(panelInscribir, "INSCRIBIR");
        panelContenedor.add(panelInscritos, "INSCRITOS");
        panelContenedor.add(panelListaEspera, "ESPERA");
        panelContenedor.add(panelProcesar, "PROCESAR");

        // Barra de navegación
        JPanel barraNavegacion = new JPanel(new FlowLayout());
        JButton btnInscribir = new JButton("Inscribir Estudiante");
        JButton btnInscritos = new JButton("Mostrar Inscritos");
        JButton btnEspera = new JButton("Lista de Espera");
        JButton btnProcesar = new JButton("Procesar Solicitudes");

        barraNavegacion.add(btnInscribir);
        barraNavegacion.add(btnInscritos);
        barraNavegacion.add(btnEspera);
        barraNavegacion.add(btnProcesar);

        // Acciones de navegación
        btnInscribir.addActionListener(e -> cardLayout.show(panelContenedor, "INSCRIBIR"));
        btnInscritos.addActionListener(e -> cardLayout.show(panelContenedor, "INSCRITOS"));
        btnEspera.addActionListener(e -> cardLayout.show(panelContenedor, "ESPERA"));
        btnProcesar.addActionListener(e -> cardLayout.show(panelContenedor, "PROCESAR"));

        // Estructura de la ventana
        setLayout(new BorderLayout());
        add(barraNavegacion, BorderLayout.NORTH);
        add(panelContenedor, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}