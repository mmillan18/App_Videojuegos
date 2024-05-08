package view;

import controller.ControllerVideoJuego;
import model.VideoJuego;
import model.VideoJuegoFisico;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class GUIAgregarFisico extends JFrame implements IGUIEstilos {

    private JTextField idTexto, nombreTexto, precioTexto, stockTexto, estadoTexto;
    private JLabel idLabel, nombreLabel, precioLabel, stockLabel,  fechaLanzamientoLabel, estadoLabel;
    private JButton guardarBTN;

    UtilDateModel model = new UtilDateModel();
    JDatePanelImpl datePanel = new JDatePanelImpl(model);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

    public GUIAgregarFisico(boolean esActualizar)  {

        JLabel titulo;
        if (esActualizar) {
            titulo = new JLabel("Actualizar juego fisico");
        } else {
            titulo = new JLabel("Agregar juego fisico");
        }
        Font fuenteActual = titulo.getFont();
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font(fuenteActual.getName(), fuenteActual.getStyle(), 20));
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new GridLayout(6,1));
        panelLabels.setBackground(COLOR);
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(6,1));
        panelTexto.setBackground(COLOR);

        setTitle("Agregar videojuego");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR);

        idLabel = new JLabel("Id: ");
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);
        precioLabel = new JLabel("Precio: ");
        precioLabel.setHorizontalAlignment(JLabel.CENTER);
        stockLabel = new JLabel("Stock");
        stockLabel.setHorizontalAlignment(JLabel.CENTER);
        fechaLanzamientoLabel= new JLabel("Fecha Lanzamiento: ");
        fechaLanzamientoLabel.setHorizontalAlignment(JLabel.CENTER);
        estadoLabel = new JLabel("Estado: ");
        estadoLabel.setHorizontalAlignment(JLabel.CENTER);


        idTexto = new JTextField();
        idTexto.setBorder(GRAY_BORDER);
        nombreTexto = new JTextField();
        nombreTexto.setBorder(GRAY_BORDER);
        precioTexto = new JTextField();
        precioTexto.setBorder(GRAY_BORDER);
        stockTexto = new JTextField();
        stockTexto.setBorder(GRAY_BORDER);
        //fechaLanzamientotexto = new JTextField();
        datePicker.setBorder(GRAY_BORDER);
        estadoTexto = new JTextField();
        estadoTexto.setBorder(GRAY_BORDER);


        guardarBTN = new JButton();
        if (esActualizar) {
            guardarBTN.setText("Actualizar");
        } else {
            guardarBTN.setText("Agregar");
        }


        panelLabels.add(idLabel);
        panelLabels.add(nombreLabel);
        panelLabels.add(precioLabel);
        panelLabels.add(stockLabel);
        panelLabels.add(fechaLanzamientoLabel);
        panelLabels.add(estadoLabel);

        panelTexto.add(idTexto);
        panelTexto.add(nombreTexto);
        panelTexto.add(precioTexto);
        panelTexto.add(stockTexto);
        panelTexto.add(datePicker);
        panelTexto.add(estadoTexto);

        add(titulo, BorderLayout.NORTH);
        add(panelLabels, BorderLayout.WEST);
        add(panelTexto, BorderLayout.CENTER);
        add(guardarBTN, BorderLayout.SOUTH);

        guardarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOActualizarVideoJuego(esActualizar);
            }
        });
    }

    private void agregarOActualizarVideoJuego(boolean esActualizar) {
        try {
            int idint = Integer.parseInt(idTexto.getText());
            String nombre = nombreTexto.getText();
            double precioDouble = Double.parseDouble(precioTexto.getText());
            int stockint = Integer.parseInt(stockTexto.getText());
            Date date = (Date) datePicker.getModel().getValue();
            LocalDate fechaLanzamiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String estado = estadoTexto.getText();

            if (esActualizar) {
                VideoJuegoFisico videojuegoFisicoActual = new VideoJuegoFisico(idint, nombre, precioDouble, stockint, fechaLanzamiento, estado);
                videojuegoFisicoActual.setId(idint);
                videojuegoFisicoActual.setNombre(nombre);
                videojuegoFisicoActual.setPrecio(precioDouble);
                videojuegoFisicoActual.setStock(stockint);
                videojuegoFisicoActual.setEstado(estado);
                videojuegoFisicoActual.setFechaLanzamiento(fechaLanzamiento);

                ControllerVideoJuego.actualizarVideojuego(videojuegoFisicoActual);
            } else {

                VideoJuego nuevoVideoJuego = new VideoJuegoFisico(idint, nombre, precioDouble, stockint, fechaLanzamiento, estado);


                ControllerVideoJuego.agregarVideoJuego(nuevoVideoJuego);
                JOptionPane.showMessageDialog(this, "Videojuego agregado exitosamente: ", "Exito", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al " + (esActualizar ? "actualizar" : "agregar") + " el videojuego: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cargarDatosFisico(VideoJuegoFisico videojuegoFisico) {
        idTexto.setText(String.valueOf(videojuegoFisico.getId()));
        nombreTexto.setText(videojuegoFisico.getNombre());
        precioTexto.setText(String.format("%.2f", videojuegoFisico.getPrecio()));
        stockTexto.setText(String.valueOf(videojuegoFisico.getStock()));
        estadoTexto.setText(videojuegoFisico.getEstado());
        LocalDate fechaLanzamiento = videojuegoFisico.getFechaLanzamiento();
        model.setDate(fechaLanzamiento.getYear(), fechaLanzamiento.getMonthValue() - 1, fechaLanzamiento.getDayOfMonth());
        model.setSelected(true);

    }

}
