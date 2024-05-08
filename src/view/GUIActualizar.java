package view;

import controller.ControllerVideoJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.VideoJuego;
import model.VideoJuegoDigital;
import model.VideoJuegoFisico;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class GUIActualizar extends JFrame implements IGUIEstilos {

    private JLabel idLabel, nombreLabel, titulo;
    private JTextField idTexto, nombreTexto;
    private JButton buscarBTN;

    UtilDateModel model = new UtilDateModel();

    public GUIActualizar() {
        JPanel panelFinal = new JPanel();
        JPanel panelInvisible = new JPanel();
        JPanel panelInvisible2 = new JPanel();
        JPanel panelInvisible3 = new JPanel();
        JPanel panelInvisible4 = new JPanel();
        panelFinal.setLayout(new BorderLayout());
        panelFinal.setBackground(COLOR);
        panelInvisible.setPreferredSize(new Dimension(100, 150));
        panelInvisible.setBackground(COLOR);
        panelInvisible2.setPreferredSize(new Dimension(100, 100));
        panelInvisible2.setBackground(COLOR);
        panelInvisible3.setPreferredSize(new Dimension(100, 150));
        panelInvisible3.setBackground(COLOR);
        panelInvisible4.setPreferredSize(new Dimension(100, 150));
        panelInvisible4.setBackground(COLOR);

        titulo = new JLabel("Ingrese el Id o el Nombre del juego a actualizar");
        Font fuenteActual = titulo.getFont();
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font(fuenteActual.getName(), fuenteActual.getStyle(), 20));

        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new GridLayout(2, 1));
        panelLabels.setBackground(COLOR);
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(2, 1));
        panelTexto.setBackground(COLOR);

        setTitle("buscar video juego");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR);

        idLabel = new JLabel("Id: ");
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);

        idTexto = new JTextField();
        idTexto.setBorder(GRAY_BORDER);
        nombreTexto = new JTextField();
        nombreTexto.setBorder(GRAY_BORDER);

        buscarBTN = new JButton("Buscar");

        panelLabels.add(idLabel);
        panelLabels.add(nombreLabel);

        panelTexto.add(idTexto);
        panelTexto.add(nombreTexto);

        panelFinal.add(panelInvisible, BorderLayout.NORTH);
        panelFinal.add(panelLabels, BorderLayout.WEST);
        panelFinal.add(panelTexto, BorderLayout.CENTER);
        panelFinal.add(panelInvisible2, BorderLayout.EAST);
        panelFinal.add(panelInvisible3, BorderLayout.SOUTH);

        add(titulo, BorderLayout.NORTH);
        add(panelInvisible4, BorderLayout.WEST);
        add(panelFinal, BorderLayout.CENTER);
        add(buscarBTN, BorderLayout.SOUTH);

        buscarBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idStr = idTexto.getText().trim();
                String nombre = nombreTexto.getText().trim();

                if (!idStr.isEmpty()) {
                    buscarJuegoPorId(idStr);
                } else if (!nombre.isEmpty()) {
                    buscarPorNombre(nombre);
                } else {
                    JOptionPane.showMessageDialog(GUIActualizar.this, "Por favor, ingrese un ID o un nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void buscarJuegoPorId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            VideoJuego videojuego = ControllerVideoJuego.buscarVideoJuego(id);
            if (videojuego != null) {
                abrirGUIEdicion(videojuego);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un videojuego con el ID proporcionado.", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido, por favor ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarPorNombre(String nombre) {
        VideoJuego videojuego = ControllerVideoJuego.buscarVideoJuegoPorNombre(nombre);
        if (videojuego != null) {
            abrirGUIEdicion(videojuego);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un videojuego con el nombre proporcionado.", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
        }

    }
    private void abrirGUIEdicion(VideoJuego videojuego) {
        if (videojuego instanceof VideoJuegoDigital) {
            GUIAgregarDigital guiActualizarDigital = new GUIAgregarDigital(true);
            guiActualizarDigital.cargarDatosDigital((VideoJuegoDigital) videojuego);
            guiActualizarDigital.setVisible(true);
            dispose();
        } else if (videojuego instanceof VideoJuegoFisico) {
            GUIAgregarFisico guiActualizarFisico = new GUIAgregarFisico(true);
            guiActualizarFisico.cargarDatosFisico((VideoJuegoFisico) videojuego);
            guiActualizarFisico.setVisible(true);
            dispose();
        }
    }
}
