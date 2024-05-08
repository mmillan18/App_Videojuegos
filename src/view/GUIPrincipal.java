package view;

import controller.ControllerVideoJuego;
import model.EmpresaVideojuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIPrincipal extends JFrame implements IGUIEstilos {

    private JMenu opciones;
    private JMenu archivo;
    private JMenu ayuda;
    private JMenu usuario;
    private JMenu juegoFisico;
    private JMenu juegoDigital;
    private JMenuBar menuBar;
    private JLabel logo;
    private JMenuItem item0;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;
    private JMenuItem item5;
    private JMenuItem item6;
    private JMenuItem item7;
    private JMenuItem item8;
    private JMenuItem item9;
    private JMenuItem item10;
    private JMenuItem item11;
    private JMenuItem item12;
    private JMenuItem item13;
    private JMenuItem item14;

    private JMenuItem item15;
    private JMenuItem item16;
    private JMenuItem item17;


    private ControllerVideoJuego videojuego;

    private GUIListar guiListar;


    public GUIPrincipal() {

        //iniciamos los valores del color y el logo
        ImageIcon URLimagen = new ImageIcon(getClass().getResource("/data/img/logo_sin_fondo.png"));

        setTitle("Compra y venta de video juegos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //iniciamos opciones, autores, el menu y el logo
        opciones = new JMenu("Opciones juego");
        ayuda = new JMenu("Ayuda");
        archivo = new JMenu("Archivo");
        usuario = new JMenu("Usuario");
        juegoFisico = new JMenu("Juego fisico");
        juegoDigital = new JMenu("Juego digital");
        menuBar = new JMenuBar();
        logo = new JLabel(URLimagen);

        //iniciamos los items del menu opciones
        //juego fisico

        item0 = new JMenuItem("Agregar");
        item1 = new JMenuItem("Eliminar");
        item2 = new JMenuItem("Actualizar");
        item3 = new JMenuItem("Buscar");

        //juego digital
        item4 = new JMenuItem("Agregar");
        item5 = new JMenuItem("Eliminar");
        item6 = new JMenuItem("Actualizar");
        item7 = new JMenuItem("Buscar");

        //usuario
        item8 = new JMenuItem("Agregar");
        item9 = new JMenuItem("Eliminar");
        item10 = new JMenuItem("Actualizar");
        item11 = new JMenuItem("Listar");
        item12 = new JMenuItem("Buscar");


        item13 = new JMenuItem("Listar");
        item14 = new JMenuItem("Salir");
        item15 = new JMenuItem("Autores");
        item16 = new JMenuItem("Calculos");
        item17 = new JMenuItem("Empresa");



        //agregamos los items al menu opciones

        archivo.add(item14);

        //opciones.add(item1);
        //opciones.addSeparator();

        opciones.add(item16);
        opciones.add(item13);

        //fisico
        juegoFisico.add(item0);
        juegoFisico.addSeparator();
        juegoFisico.add(item1);
        juegoFisico.addSeparator();
        juegoFisico.add(item2);
        juegoFisico.addSeparator();
        juegoFisico.add(item3);
        juegoFisico.addSeparator();

        //digital
        juegoDigital.add(item4);
        juegoDigital.addSeparator();
        juegoDigital.add(item5);
        juegoDigital.addSeparator();
        juegoDigital.add(item6);
        juegoDigital.addSeparator();
        juegoDigital.add(item7);
        juegoDigital.addSeparator();

        //usuario
        usuario.add(item8);
        usuario.addSeparator();
        usuario.add(item9);
        usuario.addSeparator();
        usuario.add(item10);
        usuario.addSeparator();
        usuario.add(item11);
        usuario.addSeparator();
        usuario.add(item12);
        usuario.addSeparator();

        ayuda.add(item15);
        ayuda.addSeparator();
        ayuda.add(item17);
        ayuda.addSeparator();

        //agregamos el menu opciones y el menu autores al menuBar
        menuBar.add(archivo);
        menuBar.add(opciones);
        menuBar.add(usuario);
        menuBar.add(juegoFisico);
        menuBar.add(juegoDigital);
        menuBar.add(ayuda);

        //asignamos el menuBar
        setJMenuBar(menuBar);

        //agregamos el logo
        add(logo);

        //metodo para mostrar los autores
        activarOpciones();
    }

    public void activarOpciones() {
        item15.addActionListener(e -> {
            JOptionPane.showMessageDialog(GUIPrincipal.this,
                    "Jose Manuel Caicedo Perdomo\nJuan David Gonzalez\nSebastian Murillo\nMariana Millan\n\nv.0.1",
                    "Autores",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        item0.addActionListener(e -> {
            GUIAgregarFisico guiAgregarFisico = new GUIAgregarFisico(false);
            guiAgregarFisico.setVisible(true);
        });

        item4.addActionListener(e -> {
            //GUISeleccionar guiSeleccionar = new GUISeleccionar();
            //guiSeleccionar.setVisible(true);
            GUIAgregarDigital guiAgregarDigital = new GUIAgregarDigital(false);
            guiAgregarDigital.setVisible(true);
        });

        item1.addActionListener(e -> {
            GUIEliminar guiEliminar = new GUIEliminar();
            guiEliminar.setVisible(true);
        });

        item5.addActionListener(e -> {
            GUIEliminar guiEliminar = new GUIEliminar();
            guiEliminar.setVisible(true);
        });

        item2.addActionListener(e -> {
            GUIActualizar guiActualizar = new GUIActualizar();
            guiActualizar.setVisible(true);
        });

        item6.addActionListener(e -> {
            GUIActualizar guiActualizar = new GUIActualizar();
            guiActualizar.setVisible(true);
        });



        item3.addActionListener(e -> {
            GUIBuscar guiBuscar = new GUIBuscar();
            guiBuscar.setVisible(true);
        });

        item7.addActionListener(e -> {
            GUIBuscar guiBuscar = new GUIBuscar();
            guiBuscar.setVisible(true);
        });

        item14.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(GUIPrincipal.this, "¿Estás seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        item16.addActionListener(e -> {
            GUICalculos guiCalculos = new GUICalculos();
            guiCalculos.setGuiListar(guiListar);
            guiCalculos.setVisible(true);
        });

        item13.addActionListener(e -> {
            GUIListar guiListar = new GUIListar();
            guiListar.setVisible(true);
        });

        item8.addActionListener(e -> {
            GUIAgregarUsuario guiAgregarUsuario = new GUIAgregarUsuario(false);
            guiAgregarUsuario.setVisible(true);
        });

        item9.addActionListener(e -> {
            GUIEliminarUsuario guiEliminarUsuario = new GUIEliminarUsuario();
            guiEliminarUsuario.setVisible(true);
        });

        item10.addActionListener(e -> {
            GUIActualizarUsuario guiActualizarUsuario = new GUIActualizarUsuario();
            guiActualizarUsuario.setVisible(true);
        });


        item11.addActionListener(e -> {
            GUIListarUsuario guiListarUsuario = new GUIListarUsuario();
            guiListarUsuario.setVisible(true);
        });

        item12.addActionListener(e -> {
            GUIBuscarUsuario guiBuscarUsuario = new GUIBuscarUsuario();
            guiBuscarUsuario.setVisible(true);
        });


        item17.addActionListener(e -> {
            JOptionPane.showMessageDialog(GUIPrincipal.this, EmpresaVideojuego.getEmpresa().toString(),
                    "Informacion empresa", JOptionPane.INFORMATION_MESSAGE );
        });

    }

    public void setVideojuego(ControllerVideoJuego videojuego) {
        this.videojuego = videojuego;
    }
}





