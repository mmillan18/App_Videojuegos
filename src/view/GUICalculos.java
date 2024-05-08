package view;

import controller.ControllerVideoJuego;
import model.IDescuentoAplicable;
import model.VideoJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculos extends JFrame implements IGUIEstilos{

    private JLabel idLabel, nombreLabel, titulo;
    private JTextField idTexto, nombreTexto;
    private JButton aplicarDescuentoBTN;
    private JButton calcularTotalBTN;

    private GUIListar guiListar;

    public GUICalculos() {
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

        titulo = new JLabel("<html><div style='text-align: center;'>Ingrese el Id o el Nombre del juego Físico para aplicar descuento</div></html>");
        Font fuenteActual = titulo.getFont();
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font(fuenteActual.getName(), fuenteActual.getStyle(), 20));

        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new GridLayout(2, 1));
        panelLabels.setBackground(COLOR);
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(2, 1));
        panelTexto.setBackground(COLOR);

        setTitle("Selecciona Aplicar descuento o hacer calculo Total");
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

        aplicarDescuentoBTN = new JButton("Aplicar Descuento");
        calcularTotalBTN = new JButton("Calcular Total");

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

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(aplicarDescuentoBTN);
        panelBotones.add(calcularTotalBTN);


        add(panelBotones, BorderLayout.SOUTH);

        aplicarDescuentoBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarDescuento();
            }
        });
        calcularTotalBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });
    }

    public void setGuiListar(GUIListar guiListar) {
        this.guiListar = guiListar;
    }

    private void calcularTotal() {
        double total = ControllerVideoJuego.calcularTotalPrecio();
        JOptionPane.showMessageDialog(this, "El precio total de los videjuego es: " + total, "Total Calculado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void aplicarDescuento() {
        VideoJuego videoJuego = null;

        if (!idTexto.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idTexto.getText().trim());
                videoJuego = ControllerVideoJuego.buscarVideoJuego(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID proporcionado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (!nombreTexto.getText().trim().isEmpty()) {
            videoJuego = ControllerVideoJuego.buscarVideoJuegoPorNombre(nombreTexto.getText().trim());
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un ID o un nombre para buscar el videojuego.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Procede si se encontró el videojuego.
        if (videoJuego != null) {
            if (videoJuego instanceof IDescuentoAplicable) {
                ((IDescuentoAplicable) videoJuego).aplicarDescuento();
                ControllerVideoJuego.actualizarVideojuego(videoJuego);

                JOptionPane.showMessageDialog(this, "Precio total después del descuento: " + videoJuego.getPrecio(), "Precio Total", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Este videojuego no admite descuentos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el videojuego con el ID o nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}





