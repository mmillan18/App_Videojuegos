package view;

import controller.ControllerVideoJuego;
import model.VideoJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBuscar extends JFrame implements IGUIEstilos {

    private JLabel idLabel, nombreLabel, titulo;
    private JTextField idTexto, nombreTexto;
    private JButton buscarBTN;

    public GUIBuscar(){
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

        titulo = new JLabel("Ingrese el Id o el Nombre del juego a buscar");
        Font fuenteActual = titulo.getFont();
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font(fuenteActual.getName(), fuenteActual.getStyle(), 20));

        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new GridLayout(2,1));
        panelLabels.setBackground(COLOR);
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(2,1));
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
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = idTexto.getText();
                String nombre = nombreTexto.getText();

                if (!idStr.isEmpty()) {
                    buscarJuegoPorId(idStr);
                } else if (!nombre.isEmpty()) {
                    buscarPorNombre(nombre);
                } else {
                    JOptionPane.showMessageDialog(GUIBuscar.this, "Por favor, introduzca un ID o un nombre para eliminar.", "Información Incompleta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }


    public void buscarJuegoPorId(String idStr){
        try {
            int id = Integer.parseInt(String.valueOf(idStr));
            VideoJuego videoJuego = ControllerVideoJuego.buscarVideoJuego(id);
            JOptionPane.showMessageDialog(this, "Videojuego encontrado " + videoJuego.toString(), "Exito", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "ID inválido, no existe el video juego.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void buscarPorNombre(String nombre) {
        try {
            VideoJuego videoJuego = ControllerVideoJuego.buscarVideoJuegoPorNombre(nombre);
            JOptionPane.showMessageDialog(this, "Videojuego encontrado " + videoJuego.toString(), "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID inválido, no existe el video juego.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
