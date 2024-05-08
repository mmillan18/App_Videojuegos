package view;

import controller.ControllerUsuario;
import model.IActualizable;
import model.Usuario;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GUIListarUsuario extends JFrame implements IGUIEstilos, IActualizable {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ControllerUsuario controllerUsuario;
    private JTable tabla;

    public GUIListarUsuario() {
        setTitle("Listado usuarios");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COLOR);
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(COLOR);
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Listado usuarios");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        panelTitulo.add(titleLabel);
        add(panelTitulo, BorderLayout.NORTH);

        tabla = new JTable();
        tabla.setBackground(Color.lightGray);
        tabla.setForeground(Color.black);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        controllerUsuario = new ControllerUsuario();

        usuarios.addAll(ControllerUsuario.listarUsuarios());
        actualizarTabla();

        controllerUsuario.addActualizable(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");

        for (Usuario usuario : usuarios) {
            Object[] row = new Object[2];
            row[0] = usuario.getId();
            row[1] = usuario.getNombre();
            model.addRow(row);
        }

        tabla.setModel(model);
    }

    @Override
    public void actualizar() {
        usuarios.clear();
        usuarios.addAll(ControllerUsuario.listarUsuarios());
        actualizarTabla();
    }
}
