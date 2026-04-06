import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemaSimplePOS extends JFrame {

    static class Producto {
        String nombre;
        int cantidad;
        double precio;

        Producto(String nombre, int cantidad, double precio) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return nombre + " (stock: " + cantidad + ", S/." + precio + ")";
        }
    }

    private final DefaultListModel<Producto> inventarioModel = new DefaultListModel<>();
    private final JList<Producto> inventarioList = new JList<>(inventarioModel);

    private final DefaultListModel<String> carritoModel = new DefaultListModel<>();
    private final JList<String> carritoList = new JList<>(carritoModel);

    private double totalVenta = 0;
    private JLabel totalLabel = new JLabel("Total: S/. 0.00");

    private String usuarioActual = "Usuario1";

    public SistemaSimplePOS() {
        super("Sistema Supermercado - POS Simple");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        getContentPane().add(panelPrincipal);

        // Panel Inventario
        JPanel panelInv = new JPanel(new BorderLayout());
        panelInv.setBorder(BorderFactory.createTitledBorder("Inventario"));
        panelInv.add(new JScrollPane(inventarioList), BorderLayout.CENTER);

        JButton btnAddProducto = new JButton("Agregar Producto");
        btnAddProducto.addActionListener(this::agregarProducto);
        panelInv.add(btnAddProducto, BorderLayout.SOUTH);

        panelPrincipal.add(panelInv, BorderLayout.WEST);

        // Panel Venta
        JPanel panelVenta = new JPanel(new BorderLayout());
        panelVenta.setBorder(BorderFactory.createTitledBorder("Venta"));

        panelVenta.add(new JScrollPane(carritoList), BorderLayout.CENTER);

        JPanel subVenta = new JPanel(new GridLayout(2, 1));
        subVenta.add(totalLabel);

        JPanel botones = new JPanel();

        JButton btnVender = new JButton("Vender Producto");
        btnVender.addActionListener(this::venderProducto);

        JButton btnCerrar = new JButton("Cerrar Venta (Guardar Boleta)");
        btnCerrar.addActionListener(e -> cerrarVentaYGuardar());

        // ---------------------------------------------------
        // BOTÓN NUEVO: BOLETA GENERAL
        // ---------------------------------------------------
        JButton btnBoletaGeneral = new JButton("Boleta General");
        btnBoletaGeneral.addActionListener(e -> generarBoletaGeneral());

        botones.add(btnVender);
        botones.add(btnCerrar);
        botones.add(btnBoletaGeneral);

        subVenta.add(botones);
        panelVenta.add(subVenta, BorderLayout.SOUTH);

        panelPrincipal.add(panelVenta, BorderLayout.CENTER);
    }

    // MÉTODO NUEVO: GENERAR BOLETA GENERAL
    private void generarBoletaGeneral() {

        StringBuilder sb = new StringBuilder();

        sb.append("RESUMEN GENERAL DEL SISTEMA\n");
        sb.append("SUPERMERCADO JULIACA\n");
        sb.append("---------------------------------------------\n");

        LocalDateTime ahora = LocalDateTime.now();
        String fechaHora = ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        sb.append("Fecha y Hora: ").append(fechaHora).append("\n");
        sb.append("Usuario: ").append(usuarioActual).append("\n");
        sb.append("---------------------------------------------\n");

        // --- VENTAS REGISTRADAS ---
        sb.append("\nVENTAS REALIZADAS:\n");
        sb.append("---------------------------------------------\n");

        double totalGeneral = 0;

        if (carritoModel.isEmpty()) {
            sb.append("No hay ventas en el carrito.\n");
        } else {
            for (int i = 0; i < carritoModel.size(); i++) {
                sb.append(carritoModel.get(i)).append("\n");

                // Extraer subtotal de la línea
                String linea = carritoModel.get(i);
                try {
                    double subtotal = Double.parseDouble(
                            linea.substring(linea.lastIndexOf(" ")).replace("S/.", "").trim()
                    );
                    totalGeneral += subtotal;
                } catch (Exception ex) {
                    // ignorar
                }
            }
        }

        sb.append("\nTOTAL GENERAL: S/. ")
                .append(String.format("%.2f", totalGeneral))
                .append("\n");

        sb.append("---------------------------------------------\n");

        // --- INVENTARIO COMPLETO ---
        sb.append("\nINVENTARIO COMPLETO:\n");
        sb.append("---------------------------------------------\n");

        for (int i = 0; i < inventarioModel.size(); i++) {
            Producto p = inventarioModel.get(i);
            sb.append(p.nombre)
                    .append(" | Stock: ").append(p.cantidad)
                    .append(" | Precio: S/. ").append(p.precio)
                    .append("\n");
        }

        sb.append("---------------------------------------------\n");
        sb.append("FIN DEL REPORTE GENERAL\n");

        // GUARDAR REPORTE
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar Boleta General");
        int sel = chooser.showSaveDialog(this);

        if (sel == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(sb.toString());
                JOptionPane.showMessageDialog(this,
                        "Boleta general guardada correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al guardar boleta general:\n" + ex.getMessage());
            }
        }
    }

    private void agregarProducto(ActionEvent e) {
        JTextField nombreF = new JTextField();
        JTextField cantidadF = new JTextField();
        JTextField precioF = new JTextField();
        Object[] mensaje = {
                "Nombre:", nombreF,
                "Cantidad:", cantidadF,
                "Precio (S/.):", precioF
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Registrar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreF.getText();
                int cantidad = Integer.parseInt(cantidadF.getText());
                double precio = Double.parseDouble(precioF.getText());

                Producto p = new Producto(nombre, cantidad, precio);
                inventarioModel.addElement(p);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad o precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void venderProducto(ActionEvent e) {
        Producto seleccionado = inventarioList.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto del inventario.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cantidadStr = JOptionPane.showInputDialog(this,
                "Cantidad a vender (stock: " + seleccionado.cantidad + "):");
        if (cantidadStr == null) return;

        try {
            int cant = Integer.parseInt(cantidadStr);
            if (cant > seleccionado.cantidad) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = cant * seleccionado.precio;
            totalVenta += subtotal;
            carritoModel.addElement(seleccionado.nombre + " x " + cant + " = S/. " + String.format("%.2f", subtotal));

            seleccionado.cantidad -= cant;
            inventarioList.repaint();
            totalLabel.setText("Total: S/. " + String.format("%.2f", totalVenta));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarVentaYGuardar() {
        if (carritoModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en la venta.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SUPERMERCADO JULIACA\n");
        sb.append("Boleta de Venta\n");
        sb.append("------------------------------\n");

        LocalDateTime ahora = LocalDateTime.now();
        String fechaHora = ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        sb.append("Fecha y Hora: ").append(fechaHora).append("\n");
        sb.append("Usuario: ").append(usuarioActual).append("\n");
        sb.append("------------------------------\n");

        for (int i = 0; i < carritoModel.size(); i++) {
            sb.append(carritoModel.get(i)).append("\n");
        }

        sb.append("------------------------------\n");
        sb.append("TOTAL: S/. ").append(String.format("%.2f", totalVenta)).append("\n");
        sb.append("------------------------------\n");
        sb.append("¡Gracias por su compra!\n");

        String textoBoleta = sb.toString();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar boleta…");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(textoBoleta);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar boleta:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            try (BufferedWriter invWriter = new BufferedWriter(new FileWriter("inventario_actualizado.txt"))) {
                invWriter.write("INVENTARIO ACTUALIZADO\n");
                invWriter.write("Fecha: " + fechaHora + "\n");
                invWriter.write("---------------------------\n");

                for (int i = 0; i < inventarioModel.size(); i++) {
                    Producto p = inventarioModel.get(i);
                    invWriter.write(p.nombre + " | Stock: " + p.cantidad + " | Precio: S/. " + p.precio + "\n");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar inventario:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(this, "Venta cerrada.\nBoleta guardada.\nInventario actualizado.");

            carritoModel.clear();
            totalVenta = 0;
            totalLabel.setText("Total: S/. 0.00");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaSimplePOS ventana = new SistemaSimplePOS();
            ventana.setVisible(true);
        });
    }
}