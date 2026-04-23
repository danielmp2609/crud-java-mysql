package com.daniel.crud.view;

import com.daniel.crud.model.Usuario;
import com.daniel.crud.service.UsuarioService;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioView extends JFrame {

    private JTextField txtNombre, txtCorreo, txtId;
    private JTable tabla;
    private DefaultTableModel modelo;

    private UsuarioService service = new UsuarioService();

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
    }

    private boolean validarCampos() {

        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!correo.matches(regex)) {
            JOptionPane.showMessageDialog(this, "Correo inválido (formato incorrecto)");
            return false;
        }

        return true;
    }

    public UsuarioView() {

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtCorreo = new JTextField();

        txtId.setPreferredSize(new Dimension(200, 30));
        txtNombre.setPreferredSize(new Dimension(200, 30));
        txtCorreo.setPreferredSize(new Dimension(200, 30));

        txtId.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCorreo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setTitle("CRUD Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(15, 15));

        // 🔷 PANEL FORMULARIO
        JPanel panelForm = new JPanel(new GridLayout(3, 2));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Usuario"));
        ((JPanel) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);

        JButton btnTema = new JButton("Claro");

        JPanel panelTema = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTema.add(btnTema);

        add(panelTema, BorderLayout.PAGE_END);

        add(panelForm, BorderLayout.NORTH);

        // 🔷 TABLA
        modelo = new DefaultTableModel(new String[] { "ID", "Nombre", "Correo" }, 0);
        tabla = new JTable(modelo);

        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(52, 73, 94));
        tabla.getTableHeader().setForeground(Color.WHITE);

        tabla.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();

                if (fila != -1) {
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                    txtCorreo.setText(tabla.getValueAt(fila, 2).toString());
                }
            }
        });
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // 🔷 BOTONES
        JPanel panelBotones = new JPanel(new BorderLayout());

        JPanel left = new JPanel(new GridLayout(0, 1));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JButton btnAgregar = new JButton("Agregar");
        JButton btnListar = new JButton("Listar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");

        left.add(btnAgregar);
        left.add(Box.createVerticalStrut(20));
        left.add(btnListar);
        left.add(Box.createVerticalStrut(20));
        left.add(btnActualizar);
        left.add(Box.createVerticalStrut(20));
        left.add(btnEliminar);
        left.add(Box.createVerticalStrut(20));
        left.add(btnBuscar);
        
        btnAgregar.setBackground(new Color(46, 204, 113));
        btnAgregar.setForeground(Color.WHITE);

        btnListar.setBackground(new Color(255, 174, 66));
        btnListar.setForeground(Color.WHITE);
        
        btnActualizar.setBackground(new Color(52, 152, 219));
        btnActualizar.setForeground(Color.WHITE);
        
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        
        btnBuscar.setBackground(new Color(155, 89, 182));
        btnBuscar.setForeground(Color.WHITE);
        
        btnAgregar.setFocusPainted(false);
        btnListar.setFocusPainted(false);
        btnActualizar.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        btnBuscar.setFocusPainted(false);
        
        JPanel right = new JPanel(new GridLayout(0, 1));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.add(btnTema);
        
        panelBotones.add(left, BorderLayout.WEST);
        panelBotones.add(right, BorderLayout.EAST);
        
        btnTema.setFocusPainted(false);
        btnTema.putClientProperty("JButton.buttonType", "roundRect");
        
        add(panelBotones, BorderLayout.EAST);

        // 🔥 EVENTOS
        
        btnAgregar.addActionListener(e -> {
            if (!validarCampos())
                return;
            
            Usuario u = new Usuario(
                txtNombre.getText(),
                txtCorreo.getText());
            service.crearUsuario(u);
            listarUsuarios();
            limpiarCampos();
        });

        btnListar.addActionListener(e -> listarUsuarios());

        btnActualizar.addActionListener(e -> {
            service.listarUsuarios();
            listarUsuarios();
            limpiarCampos();
        });

        btnEliminar.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que deseas eliminar este usuario?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(txtId.getText());
                service.eliminarUsuario(id);
                listarUsuarios();
                limpiarCampos();
            }
        });

        
        btnTema.addActionListener(e -> {
            try {
                if (UIManager.getLookAndFeel() instanceof FlatDarkLaf) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    btnTema.setText("Claro");
                    btnTema.setForeground(Color.DARK_GRAY);

                } else {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    btnTema.setText("Oscuro");
                    btnTema.setForeground(Color.WHITE);

                }

                SwingUtilities.updateComponentTreeUI(this);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }); 
        

        listarUsuarios();

        btnBuscar.addActionListener(e -> {

            String nombre = txtNombre.getText();

            List<Usuario> lista = service.buscarPorNombre(nombre);

            modelo.setRowCount(0);

            for (Usuario u : lista) {
                modelo.addRow(new Object[] {
                        u.getId(),
                        u.getNombre(),
                        u.getCorreo()
                });
            }
        });
    }

    private void listarUsuarios() {
        modelo.setRowCount(0);
        List<Usuario> lista = service.listarUsuarios();

        for (Usuario u : lista) {
            modelo.addRow(new Object[] {
                    u.getId(),
                    u.getNombre(),
                    u.getCorreo()
            });
        }
    }

}
