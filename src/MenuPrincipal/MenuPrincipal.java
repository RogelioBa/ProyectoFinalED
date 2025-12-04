package MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JPanel panelCentral;
    private CardLayout cardLayout;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Estudiantes - Proyecto Final");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        // Paneles vacíos por ahora
        panelCentral.add(new JLabel("Bienvenido al sistema"), "Inicio");

        // Eventos de submenús (solo muestran mensajes por ahora)
        itemAgregarEstudiante.addActionListener(e -> mostrarMensaje("Agregar estudiante"));
        itemBuscarEstudiante.addActionListener(e -> mostrarMensaje("Buscar estudiante por matrícula"));
        itemListarPorPromedio.addActionListener(e -> mostrarMensaje("Listar estudiantes ordenados por promedio"));

        itemAgregarCurso.addActionListener(e -> mostrarMensaje("Agregar curso"));
        itemEliminarCurso.addActionListener(e -> mostrarMensaje("Eliminar curso"));
        itemListarCursos.addActionListener(e -> mostrarMensaje("Listar cursos"));

        itemInscribirEstudiante.addActionListener(e -> mostrarMensaje("Inscribir estudiante en curso"));
        itemMostrarInscritos.addActionListener(e -> mostrarMensaje("Mostrar inscritos de un curso"));
        itemMostrarListaEspera.addActionListener(e -> mostrarMensaje("Mostrar lista de espera"));

        itemEnviarSolicitud.addActionListener(e -> mostrarMensaje("Enviar solicitud de calificación"));
        itemProcesarSolicitud.addActionListener(e -> mostrarMensaje("Procesar siguiente solicitud"));

        itemDeshacer.addActionListener(e -> mostrarMensaje("Deshacer última acción"));

        itemRotarRol.addActionListener(e -> mostrarMensaje("Rotar rol de tutor/líder"));

        add(panelCentral);
        setVisible(true);
    }

    // Método auxiliar para mostrar mensajes temporales
    private void mostrarMensaje(String titulo) {
        JOptionPane.showMessageDialog(this, "Funcionalidad pendiente: " + titulo);
        // Aquí se conectará el panel o formulario correspondiente
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}