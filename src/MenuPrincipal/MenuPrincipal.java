package MenuPrincipal;

import javax.swing.*;
import java.awt.*;


import Estudiante.SistemaGestionEstudiantes;
import GestionCursos.GestionCursos;
import Listas.ListaCircularSimple;
import Solicitudes.SolicitudesDeCalificacion;
import Deshacer.PilaAcciones;

public class MenuPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenedor;

    // === DEPENDENCIAS ===
    private SistemaGestionEstudiantes sistema;
    private GestionCursos gestionCursos;
    private ListaCircularSimple listaCircular;
    private SolicitudesDeCalificacion solicitudesCalificacion;
    private PilaAcciones pilaAcciones;

    public MenuPrincipal(SistemaGestionEstudiantes sistema,
                         GestionCursos gestionCursos,
                         ListaCircularSimple listaCircular,
                         SolicitudesDeCalificacion solicitudesCalificacion,
                         PilaAcciones pilaAcciones) {

        this.sistema = sistema;
        this.gestionCursos = gestionCursos;
        this.listaCircular = listaCircular;
        this.solicitudesCalificacion = solicitudesCalificacion;
        this.pilaAcciones = pilaAcciones;

        setTitle("Sistema Académico");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // ====================================
        //      REGISTRO DE TODOS LOS PANELES
        // ====================================

        panelContenedor.add(new PanelAgregarEstudiante(sistema), "AGREGAR_EST");
        panelContenedor.add(new PanelBuscarEstudiante(sistema), "BUSCAR");

        panelContenedor.add(new PanelAgregarCurso(gestionCursos), "AGREGAR_CURSO");
        panelContenedor.add(new PanelEliminarCurso(gestionCursos), "ELIMINAR_CURSO");
        panelContenedor.add(new PanelListarCursos(gestionCursos), "LISTAR_CURSOS");

        panelContenedor.add(new PanelInscribirEstudiante(sistema, gestionCursos), "INSCRIBIR");
        panelContenedor.add(new PanelMostrarInscritos(gestionCursos), "INSCRITOS");
        panelContenedor.add(new PanelMostrarListaEspera(gestionCursos), "ESPERA");

        // Panel que usa  COLA DE SOLICITUDES
        panelContenedor.add(new PanelEnviarSolicitud(solicitudesCalificacion), "ENVIAR_SOLICITUD");            
        panelContenedor.add(new PanelProcesarSolicitud(sistema, solicitudesCalificacion), "PROCESAR");
        // Panel de deshacer con pila 
        panelContenedor.add(new PanelDeshacerAccion(pilaAcciones), "DESHACER");

        panelContenedor.add(new PanelListarPorPromedio(sistema), "PROMEDIOS");
        panelContenedor.add(new PanelRotarRol(listaCircular), "ROTAR");

        // ====================================
        //          MENÚ SUPERIOR
        // ====================================
        JMenuBar menuBar = new JMenuBar();

        JMenu menuEst = new JMenu("Estudiantes");
        menuEst.add(crearItem("Registrar Estudiante", "AGREGAR_EST"));
        menuEst.add(crearItem("Buscar Estudiante", "BUSCAR"));

        JMenu menuCursos = new JMenu("Cursos");
        menuCursos.add(crearItem("Agregar Curso", "AGREGAR_CURSO"));
        menuCursos.add(crearItem("Eliminar Curso", "ELIMINAR_CURSO"));
        menuCursos.add(crearItem("Listar Cursos", "LISTAR_CURSOS"));

        JMenu menuIns = new JMenu("Inscripciones");
        menuIns.add(crearItem("Inscribir Estudiante", "INSCRIBIR"));
        menuIns.add(crearItem("Mostrar Inscritos", "INSCRITOS"));
        menuIns.add(crearItem("Lista de Espera", "ESPERA"));

        JMenu menuCal = new JMenu("Calificaciones");
        menuCal.add(crearItem("Enviar Solicitudes", "ENVIAR_SOLICITUD"));
        menuCal.add(crearItem("Procesar Solicitudes", "PROCESAR"));
        

        JMenu menuAcc = new JMenu("Acciones");
        menuAcc.add(crearItem("Deshacer Acción", "DESHACER"));

        JMenu menuRep = new JMenu("Reportes");
        menuRep.add(crearItem("Listar por Promedio", "PROMEDIOS"));
        menuRep.add(crearItem("Rotar Tutor/Líder", "ROTAR"));

        JMenu menuSalir = new JMenu("Salir");
        JMenuItem miSalir = new JMenuItem("Cerrar");
        miSalir.addActionListener(e -> System.exit(0));
        menuSalir.add(miSalir);

        menuBar.add(menuEst);
        menuBar.add(menuCursos);
        menuBar.add(menuIns);
        menuBar.add(menuCal);
        menuBar.add(menuAcc);
        menuBar.add(menuRep);
        menuBar.add(menuSalir);

        setJMenuBar(menuBar);

        add(panelContenedor, BorderLayout.CENTER);
        setVisible(true);
    }

    private JMenuItem crearItem(String texto, String panelClave) {
        JMenuItem item = new JMenuItem(texto);
        item.addActionListener(e -> cardLayout.show(panelContenedor, panelClave));
        return item;
    }
}