package view;

import controller.ControllerUsuario;
import controller.ControllerVideoJuego;
import model.Usuario;
import model.VideoJuego;
import model.VideoJuegoDigital;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class GUIAgregarDigital extends JFrame implements IGUIEstilos {

    private JTextField idTexto, nombreTexto, precioTexto, stockTexto, claveActivacionTexto;
    private JLabel idLabel, nombreLabel, precioLabel, stockLabel, fechaLanzamientoLabel, claveActivacionLabel, usuarioLabel;
    private JButton guardarBTN;

    private JComboBox<Usuario> usuarioComboBox;

    UtilDateModel model = new UtilDateModel();
    JDatePanelImpl datePanel = new JDatePanelImpl(model);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

    public GUIAgregarDigital(boolean esActualizar) {

        JLabel titulo;
        if (esActualizar) {
            titulo = new JLabel("Actualizar juego digital");
        } else {
            titulo = new JLabel("Agregar juego digital");
        }
        Font fuenteActual = titulo.getFont();
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font(fuenteActual.getName(), fuenteActual.getStyle(), 20));
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new GridLayout(7, 1));
        panelLabels.setBackground(COLOR);
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(7, 1));
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
        fechaLanzamientoLabel = new JLabel("Fecha Lanzamiento: ");
        fechaLanzamientoLabel.setHorizontalAlignment(JLabel.CENTER);
        claveActivacionLabel = new JLabel("Clave: ");
        claveActivacionLabel.setHorizontalAlignment(JLabel.CENTER);
        usuarioLabel = new JLabel("Usuario: ");
        usuarioLabel.setHorizontalAlignment(JLabel.CENTER);

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
        claveActivacionTexto = new JTextField();
        claveActivacionTexto.setBorder(GRAY_BORDER);
        usuarioComboBox = new JComboBox<>();
        usuarioComboBox.setBorder(GRAY_BORDER);

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
        panelLabels.add(claveActivacionLabel);
        panelLabels.add(usuarioLabel);

        panelTexto.add(idTexto);
        panelTexto.add(nombreTexto);
        panelTexto.add(precioTexto);
        panelTexto.add(stockTexto);
        panelTexto.add(datePicker);
        panelTexto.add(claveActivacionTexto);
        panelTexto.add(usuarioComboBox);

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

        cargarUsuarios();



    }

    private void cargarUsuarios() {
        for (Usuario usuario : ControllerUsuario.listarUsuarios()) {
            usuarioComboBox.addItem(usuario);
        }
    }

    private void agregarOActualizarVideoJuego(boolean esActualizar) {
        try {
            int idint = Integer.parseInt(idTexto.getText());
            String nombre = nombreTexto.getText();
            double precioDouble = Double.parseDouble(precioTexto.getText());
            int stockint = Integer.parseInt(stockTexto.getText());
            Date date = (Date) datePicker.getModel().getValue();
            LocalDate fechaLanzamiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String claveActivacion = claveActivacionTexto.getText();


            Usuario selectedUser = (Usuario) usuarioComboBox.getSelectedItem();


            if (selectedUser == null) {
                throw new RuntimeException("Error: Debes seleccionar un usuario antes de agregar o actualizar el videojuego.");
            }

            if (esActualizar) {
                VideoJuegoDigital videojuegoDigitalActual = new VideoJuegoDigital(idint, nombre, precioDouble, stockint,  fechaLanzamiento, claveActivacion);
                videojuegoDigitalActual.setId(idint);
                videojuegoDigitalActual.setNombre(nombre);
                videojuegoDigitalActual.setPrecio(precioDouble);
                videojuegoDigitalActual.setStock(stockint);
                videojuegoDigitalActual.setClaveActivacion(claveActivacion);
                videojuegoDigitalActual.setFechaLanzamiento(fechaLanzamiento);

                videojuegoDigitalActual.setUsuario(selectedUser);


                ControllerVideoJuego.actualizarVideojuego(videojuegoDigitalActual);
                JOptionPane.showMessageDialog(GUIAgregarDigital.this, "Videojuego digital actualizado con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);


            } else {



                VideoJuego nuevoVideoJuego = new VideoJuegoDigital(idint, nombre, precioDouble, stockint,  fechaLanzamiento, claveActivacion);

                ((VideoJuegoDigital) nuevoVideoJuego).setUsuario(selectedUser);

                ControllerVideoJuego.agregarVideoJuego(nuevoVideoJuego);
                JOptionPane.showMessageDialog(this, "Videojuego agregado exitosamente: ", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al " + (esActualizar ? "actualizar" : "agregar") + " el videojuego: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarDatosDigital(VideoJuegoDigital videojuegoDigital) {

        idTexto.setText(String.valueOf(videojuegoDigital.getId()));
        nombreTexto.setText(videojuegoDigital.getNombre());
        precioTexto.setText(String.format("%.2f", videojuegoDigital.getPrecio()));
        stockTexto.setText(String.valueOf(videojuegoDigital.getStock()));
        claveActivacionTexto.setText(videojuegoDigital.getClaveActivacion());
        LocalDate fechaLanzamiento = videojuegoDigital.getFechaLanzamiento();
        model.setDate(fechaLanzamiento.getYear(), fechaLanzamiento.getMonthValue() - 1, fechaLanzamiento.getDayOfMonth());
        model.setSelected(true);

        try {
            idTexto.setText(String.valueOf(videojuegoDigital.getId()));
            nombreTexto.setText(videojuegoDigital.getNombre());
            precioTexto.setText(String.format("%.2f", videojuegoDigital.getPrecio()));
            stockTexto.setText(String.valueOf(videojuegoDigital.getStock()));
            claveActivacionTexto.setText(videojuegoDigital.getClaveActivacion());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < usuarioComboBox.getItemCount(); i++) {
            Usuario usuario = (Usuario) usuarioComboBox.getItemAt(i);
            if (usuario.getId() == videojuegoDigital.getUsuario().getId()) {
                usuarioComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

}


