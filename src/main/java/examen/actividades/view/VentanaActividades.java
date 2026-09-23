package examen.actividades.view;

import examen.actividades.controller.ActividadController;
import examen.actividades.model.Actividad;
import examen.actividades.model.TipoActividad;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class VentanaActividades extends JFrame {

    private final ActividadController controlador;


    private JPanel panel1;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtTarifaBase;
    private JTextField txtCupoTotal;
    private JTextField txtCodigoConsulta;
    private JComboBox<TipoActividad> cmbTipo;
    private JScrollPane scrollResultados;
    private JTable tablaActividades;
    private final DefaultTableModel modeloActividades = new DefaultTableModel(
            new String[]{"Código", "Nombre", "Tipo", "Tarifa base", "Tarifa final",
                    "Cupo total", "Inscritos", "Cupos disponibles"},
            0);
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnInscribir;
    private JButton btnMostrarTodas;
    private JButton btnLimpiar;
    private JButton btnGuardar;
    private JLabel lblMensaje;

    public VentanaActividades(ActividadController controlador) {
        this.controlador = controlador;


        for (TipoActividad tipo : TipoActividad.values()) {
            cmbTipo.addItem(tipo);
        }

        if (tablaActividades == null) {
            tablaActividades = new JTable(modeloActividades);
        }
        tablaActividades.setModel(modeloActividades);
        scrollResultados.setViewportView(tablaActividades);

        setContentPane(panel1);
        setTitle("Sistema de Actividades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conectarEventos();
        pack();
        setLocationRelativeTo(null);
    }


    private void conectarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnBuscar.addActionListener(e -> buscar());
        btnInscribir.addActionListener(e -> inscribir());
        btnMostrarTodas.addActionListener(e -> controlador.mostrarTodas());
        btnLimpiar.addActionListener(e -> limpiar());
        btnGuardar.addActionListener(e -> controlador.guardarDatos());
    }

    private void registrar() {
        try {
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            double tarifaBase = Double.parseDouble(txtTarifaBase.getText().trim());
            int cupoTotal = Integer.parseInt(txtCupoTotal.getText().trim());
            TipoActividad tipo = cmbTipo.getItemAt(cmbTipo.getSelectedIndex());

            controlador.registrarActividad(codigo, nombre, tarifaBase, cupoTotal, tipo);

            txtCodigo.setText("");
            txtNombre.setText("");
            txtTarifaBase.setText("");
            txtCupoTotal.setText("");
        } catch (NumberFormatException ex) {
            mostrarError("La tarifa base y el cupo total deben ser números.");
        }
    }

    private void buscar() {
        String codigo = txtCodigoConsulta.getText().trim();
        if (codigo.isEmpty()) {
            mostrarError("Ingrese el código de la actividad a buscar.");
            return;
        }
        controlador.buscarActividad(codigo);
    }

    private void inscribir() {
        String codigo = txtCodigoConsulta.getText().trim();
        if (codigo.isEmpty()) {
            mostrarError("Ingrese el código de la actividad donde inscribirse.");
            return;
        }
        controlador.inscribir(codigo);
    }

    private void limpiar() {
        modeloActividades.setRowCount(0);
        txtCodigoConsulta.setText("");
        mostrarInfo(" ");
    }


    public void mostrarResultados(List<Actividad> actividades) {
        modeloActividades.setRowCount(0);
        for (Actividad a : actividades) {
            modeloActividades.addRow(new Object[]{
                    a.getCodigo(),
                    a.getNombre(),
                    a.getTipo(),
                    a.getTarifaBase(),
                    a.calcularTarifaFinal(),
                    a.getCupoTotal(),
                    a.getInscritos(),
                    a.getCuposDisponibles()
            });
        }
    }

    public void mostrarInfo(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrar() {
        setVisible(true);
    }
}
