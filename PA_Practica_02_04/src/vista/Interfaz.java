package vista;

import controlador.GestionPersona;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import modelo.Persona;

public class Interfaz extends JFrame implements ActionListener {

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtFechaNac;
    private JLabel lbTamanio;
    private JTable tblAutos;
    private boolean editar;
    private String dato;

    private GestionPersona gp;

    public Interfaz() {

        setTitle("Gestion Persona");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(2, 2));

        editar = false;

        JButton boton1 = new JButton("Guardar");
        boton1.addActionListener(this);
        boton1.setActionCommand("btnGuardar");
        JButton boton2 = new JButton("Limpiar");
        boton2.addActionListener(this);
        boton2.setActionCommand("btnLimpiar");

        JPanel botonSalir = new JPanel();
        JButton boton3 = new JButton("Salir");
        boton3.addActionListener(this);
        boton3.setActionCommand("btnSalir");
        botonSalir.add(boton3);

        JButton boton1Panelel2 = new JButton("Guardar");
        boton1Panelel2.addActionListener(this);
        boton1Panelel2.setActionCommand("btnGuardarPanel2");
        JButton boton2Panelel2 = new JButton("Limpiar");
        boton2Panelel2.addActionListener(this);
        boton2Panelel2.setActionCommand("btnLimpiarPanel2");

        JButton boton3Panelel2 = new JButton("Buscar");
        boton3Panelel2.addActionListener(this);
        boton3Panelel2.setActionCommand("btnBuscarReg");

        JButton boton4Panelel2 = new JButton("Listar");
        boton4Panelel2.addActionListener(this);
        boton4Panelel2.setActionCommand("btnListar");

        JPanel panelPersona = new JPanel();
        panelPersona.setLayout(new GridBagLayout());
        panelPersona.setBorder(BorderFactory.createTitledBorder("Datos Persona"));
        getContentPane().add(panelPersona);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets.set(0, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPersona.add(new JLabel("Cedula: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtCedula = new JTextField(20);
        panelPersona.add(txtCedula, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPersona.add(new JLabel("Nombre: "), gbc);
        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelPersona.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPersona.add(new JLabel("Direccion: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtDireccion = new JTextField(20);
        panelPersona.add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPersona.add(new JLabel("Fecha de Naciemiento: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        txtFechaNac = new JTextField("00/00/0000");
        panelPersona.add(txtFechaNac, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPersona.add(new JLabel("Tamaño del archivo:"), gbc);
        lbTamanio = new JLabel();
        tamanioArchivo();
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelPersona.add(lbTamanio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelPersona.add(boton4Panelel2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JPanel btns = new JPanel(new FlowLayout());

        btns.add(boton1Panelel2);
        btns.add(boton2Panelel2);
        btns.add(boton3Panelel2);
        panelPersona.add(btns, gbc);

        JPanel panelAuto2 = new JPanel();
        panelAuto2.setLayout(new BorderLayout());
        panelAuto2.setBorder(BorderFactory.createTitledBorder("Listado Personas"));
        getContentPane().add(panelAuto2);

        tblAutos = new JTable();
        tblAutos.setModel(new ModelPersona());
        JScrollPane scrollPaneTable = new JScrollPane(tblAutos);
        panelAuto2.add(scrollPaneTable, BorderLayout.CENTER);
        panelAuto2.add(botonSalir, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        switch (evt.getActionCommand()) {
            case "btnSalir":
                btnSalir();
                break;
            case "btnGuardarPanel2":
                btnGuardarPanel2();
                break;
            case "btnLimpiarPanel2":
                btnLimpiarPanel2();
                break;
            case "btnBuscarReg":
                btnBuscarReg();
                break;
            case "btnListar":
                btnListar();
                break;
            default:
                break;
        }
    }

    public void btnSalir() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "Seguro desea salir?", "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        if (opcion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Siga trabajando",
                    "MSJ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void btnGuardarPanel2() {
        gp = new GestionPersona("src/archivos/personas.dat");
        if (editar) {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "Seguro desea remplazar? ", "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    gp.editarRegistro(Integer.parseInt(dato), txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtFechaNac.getText());
                    JOptionPane.showMessageDialog(this, "Dato sobre escrito",
                            "MSJ", JOptionPane.INFORMATION_MESSAGE);
                    tamanioArchivo();
                    tblAutos.setModel(new ModelPersona(gp.listarPersona()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            editar = false;
        }
        else {
            try {
                if (txtCedula.getText().equals("") || txtNombre.getText().equals("") || txtFechaNac.getText().equals("") || txtFechaNac.getText().equals("00/00/0000")) {
                    throw new Exception("Debe llenar todos los campos.");
                }
                gp.validarCedula(txtCedula.getText());
                gp.validaFechaNac(txtFechaNac.getText());

                gp.agregarPersona(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtFechaNac.getText());
                tamanioArchivo();
                JOptionPane.showMessageDialog(this, "Datos guardados", "Mensaje de información", JOptionPane.INFORMATION_MESSAGE);
                tblAutos.setModel(new ModelPersona(gp.listarPersona()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void btnLimpiarPanel2() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtFechaNac.setText("00/00/0000");

    }

    private void btnBuscarReg() {
        gp = new GestionPersona("src/archivos/personas.dat");

        dato = JOptionPane.showInputDialog(this, "Inserte el numero de registro a bsucar.");
        if (dato.equals("")) {
            JOptionPane.showMessageDialog(this, "No a ingresado ningun dato.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                gp.vailidaNumeros(dato);
                Persona persona = gp.buscaRegistro(Integer.parseInt(dato));
                txtNombre.setText(persona.getNombre());
                txtDireccion.setText(persona.getDireccion());
                txtCedula.setText(persona.getCedula());
                txtFechaNac.setText(persona.getFechaNac());

                editar = true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void tamanioArchivo() {
        gp = new GestionPersona("src/archivos/personas.dat");
        long t = gp.obtieneTamanio();
        lbTamanio.setText("" + t + " bytes");
    }

    private void btnListar() {
        gp = new GestionPersona("src/archivos/personas.dat");
        try {

            tblAutos.setModel(new ModelPersona(gp.listarPersona()));
            JOptionPane.showMessageDialog(this, "Datos actualizados.",
                    "MSJ", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
