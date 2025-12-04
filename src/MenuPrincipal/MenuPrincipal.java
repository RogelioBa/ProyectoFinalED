package MenuPrincipal;

import GestionCursos.GestionCursos;
import javax.swing.*;
import java.awt.*;

/**
 * MenuPrincipal
 * -------------
 * Ventana principal del sistema de gestión de estudiantes.
 * Contiene la barra de menús y un panel central con CardLayout
 * para mostrar los diferentes módulos (Estudiantes, Cursos, etc.).
 *
 * @author Roberto
 * @version 2.0
 */
public class MenuPrincipal extends JFrame {

    private JPanel panelCentral;
    private CardLayout cardLayout;
    private GestionCursos gestionCursos;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Estudiantes - Proyecto Final");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        gestionCursos = new GestionCursos();
        // Menú superior
        JMenuBar menuBar = new JMenuBar();

        // Menú Estudiantes
        JMenu menuEstudiantes = new JMenu("Estudiantes");
        JMenuItem itemAgregarEstudiante = new JMenuItem("Agregar estudiante");
        JMenuItem itemBuscarEstudiante = new JMenuItem("Buscar por matrícula");
        JMenuItem itemListarPorPromedio = new JMenuItem("Listar ordenados por promedio");
        menuEstudiantes.add(itemAgregarEstudiante);
        menuEstudiantes.add(itemBuscarEstudiante);
        menuEstudiantes.add(itemListarPorPromedio);

        // Menú Cursos
        JMenu menuCursos = new JMenu("Cursos");
        JMenuItem itemAgregarCurso = new JMenuItem("Agregar curso");
        JMenuItem itemEliminarCurso = new JMenuItem("Eliminar curso");
        JMenuItem itemListarCursos = new JMenuItem("Listar cursos");
        menuCursos.add(itemAgregarCurso);
        menuCursos.add(itemEliminarCurso);
        menuCursos.add(itemListarCursos);

        // Menú Inscripciones
        JMenu menuInscripciones = new JMenu("Inscripciones");
        JMenuItem itemInscribirEstudiante = new JMenuItem("Inscribir estudiante en curso");
        JMenuItem itemMostrarInscritos = new JMenuItem("Mostrar inscritos de un curso");
        JMenuItem itemMostrarListaEspera = new JMenuItem("Mostrar lista de espera");
        menuInscripciones.add(itemInscribirEstudiante);
        menuInscripciones.add(itemMostrarInscritos);
        menuInscripciones.add(itemMostrarListaEspera);

        // Menú Calificaciones
        JMenu menuCalificaciones = new JMenu("Calificaciones");
        JMenuItem itemEnviarSolicitud = new JMenuItem("Enviar solicitud de calificación");
        JMenuItem itemProcesarSolicitud = new JMenuItem("Procesar siguiente solicitud");
        menuCalificaciones.add(itemEnviarSolicitud);
        menuCalificaciones.add(itemProcesarSolicitud);

        // Menú Acciones
        JMenu menuAcciones = new JMenu("Acciones");
        JMenuItem itemDeshacer = new JMenuItem("Deshacer última acción");
        menuAcciones.add(itemDeshacer);

        // Menú Reportes
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemRotarRol = new JMenuItem("Rotar rol de tutor/líder");
        menuReportes.add(itemRotarRol);

        // Agregar todos los menús a la barra
        menuBar.add(menuEstudiantes);
        menuBar.add(menuCursos);
        menuBar.add(menuInscripciones);
        menuBar.add(menuCalificaciones);
        menuBar.add(menuAcciones);
        menuBar.add(menuReportes);
        setJMenuBar(menuBar);

        // Panel central con CardLayout
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);

        // Paneles
        panelCentral.add(new JLabel("Bienvenido al sistema"), "Inicio");
        panelCentral.add(new PanelAgregarEstudiante(), "AgregarEstudiante");
        panelCentral.add(new PanelBuscarEstudiante(), "BuscarEstudiante");
        panelCentral.add(new PanelListarPorPromedio(), "ListarPorPromedio");

        panelCentral.add(new PanelAgregarCurso(gestionCursos), "AgregarCurso");
        panelCentral.add(new PanelEliminarCurso(), "EliminarCurso");
        panelCentral.add(new PanelListarCursos(), "ListarCursos");

        panelCentral.add(new PanelInscribirEstudiante(), "InscribirEstudiante");
        panelCentral.add(new PanelMostrarInscritos(), "MostrarInscritos");
        panelCentral.add(new PanelMostrarListaEspera(), "MostrarListaEspera");

        panelCentral.add(new PanelEnviarSolicitud(), "EnviarSolicitud");
        panelCentral.add(new PanelProcesarSolicitud(), "ProcesarSolicitud");

        panelCentral.add(new PanelDeshacerAccion(), "DeshacerAccion");
        panelCentral.add(new PanelRotarRol(), "RotarRol");

        // Eventos de submenús -> muestran el panel correspondiente
        itemAgregarEstudiante.addActionListener(e -> cardLayout.show(panelCentral, "AgregarEstudiante"));
        itemBuscarEstudiante.addActionListener(e -> cardLayout.show(panelCentral, "BuscarEstudiante"));
        itemListarPorPromedio.addActionListener(e -> cardLayout.show(panelCentral, "ListarPorPromedio"));

        itemAgregarCurso.addActionListener(e -> cardLayout.show(panelCentral, "AgregarCurso"));
        itemEliminarCurso.addActionListener(e -> cardLayout.show(panelCentral, "EliminarCurso"));
        itemListarCursos.addActionListener(e -> cardLayout.show(panelCentral, "ListarCursos"));

        itemInscribirEstudiante.addActionListener(e -> cardLayout.show(panelCentral, "InscribirEstudiante"));
        itemMostrarInscritos.addActionListener(e -> cardLayout.show(panelCentral, "MostrarInscritos"));
        itemMostrarListaEspera.addActionListener(e -> cardLayout.show(panelCentral, "MostrarListaEspera"));

        itemEnviarSolicitud.addActionListener(e -> cardLayout.show(panelCentral, "EnviarSolicitud"));
        itemProcesarSolicitud.addActionListener(e -> cardLayout.show(panelCentral, "ProcesarSolicitud"));

        itemDeshacer.addActionListener(e -> cardLayout.show(panelCentral, "DeshacerAccion"));
        itemRotarRol.addActionListener(e -> cardLayout.show(panelCentral, "RotarRol"));

        add(panelCentral);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}