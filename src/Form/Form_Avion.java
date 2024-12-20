/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import java.sql.*;
import Clases.DatabaseConnection;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Dairo Arenas
 */
public class Form_Avion extends javax.swing.JFrame {

    /**
     * Creates new form Form_Avion
     */
    DatabaseConnection dbConn;
    public Form_Avion(DatabaseConnection dbConn_) {
        initComponents();
        this.dbConn = dbConn_;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sPlaca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fEntrada = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        hEntrada = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        fSalida = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        hSalida = new javax.swing.JFormattedTextField();
        nCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Placa");

        jLabel2.setText("Fecha Entrada");

        fEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel3.setText("Hora");

        hEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        hEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hEntradaActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha Salida");

        fSalida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel5.setText("Hora");

        hSalida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));

        jLabel6.setText("Cantidad Asientos");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sPlaca)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(hEntrada)))
                    .addComponent(nCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(sPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(nCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hEntradaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String splaca = sPlaca.getText();
            String fentrada = fEntrada.getText();
            String hentrada = hEntrada.getText();
            String fsalida = fSalida.getText();
            String hsalida = hSalida.getText();
            int ncantidad = Integer.parseInt(nCantidad.getText());

            // Validación de campos
            if (splaca.isEmpty() || fentrada.isEmpty() || hentrada.isEmpty() || fsalida.isEmpty() || hsalida.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben ser llenados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (ncantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de asientos debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parseo de fecha y hora
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalDateTime fechaEntrada = LocalDateTime.of(LocalDate.parse(fentrada, dateFormatter), LocalTime.parse(hentrada, timeFormatter));
            LocalDateTime fechaSalida = LocalDateTime.of(LocalDate.parse(fsalida, dateFormatter), LocalTime.parse(hsalida, timeFormatter));

            // Crear el mapa con los valores de las columnas
            Map<String, Object> columnValues = new HashMap<>();
            columnValues.put("Placa", splaca);
            columnValues.put("Asiento", ncantidad);
            columnValues.put("FechaEntrada", java.sql.Timestamp.valueOf(fechaEntrada));
            columnValues.put("FechaSalida", java.sql.Timestamp.valueOf(fechaSalida));

            // Intento de insertar el registro en la tabla Avion
            if (dbConn.insertarRegistro("Avion", columnValues)) {
                // Obtener el ID del avión recién insertado
                //String query = "SELECT SCOPE_IDENTITY()"; // Devuelve el último ID insertado en la conexión actual
                String query = "SELECT IDENT_CURRENT('Avion')";
                int avionId = -1;
                try (java.sql.Statement stmt = dbConn.Connection().createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
                        avionId = rs.getInt(1); // Obtener el ID del avión
                        System.out.println("ID del avión insertado con IDENT_CURRENT: " + avionId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Ahora insertar los asientos en la tabla AvionDetalle
                if (avionId != -1) {
                    // Generar los detalles de los asientos
                    char[] letrasAsientos = {'A', 'B', 'C', 'D', 'E', 'F'}; // Supongamos que tienes 6 letras por fila
                    int numFilas = (ncantidad + letrasAsientos.length - 1) / letrasAsientos.length; // Asegura que se cubren todas las filas, incluso si no es múltiplo exacto de 6
                    int asientoNumero = 1;

                    // Insertar en la tabla AvionDetalle para cada asiento
                    for (char letra : letrasAsientos) { // Recorre las letras de manera continua
                        for (int fila = 0; fila < numFilas; fila++) {
                            if (asientoNumero > ncantidad) {
                                break; // Sale del ciclo si ya hemos asignado todos los asientos
                            }
                            String asientoLetra = String.valueOf(letra);
                            int asientoNumeroFila = asientoNumero++;

                            Map<String, Object> detalleColumnValues = new HashMap<>();
                            detalleColumnValues.put("AvionId", avionId);
                            detalleColumnValues.put("AsientoLetra", asientoLetra);
                            detalleColumnValues.put("AsientoNumero", asientoNumeroFila);

                            // Insertar el detalle del asiento en la tabla
                            boolean insertado = dbConn.insertarRegistro("AvionDetalle", detalleColumnValues);
                            if (!insertado) {
                                JOptionPane.showMessageDialog(this, "Error al insertar el asiento " + asientoLetra + asientoNumeroFila, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }

                JOptionPane.showMessageDialog(this, "Avión y detalles de asientos guardados exitosamente.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el avión.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Avion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Avion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Avion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Avion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Form_Avion(dbConn).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JFormattedTextField fEntrada;
    private javax.swing.JFormattedTextField fSalida;
    private javax.swing.JFormattedTextField hEntrada;
    private javax.swing.JFormattedTextField hSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nCantidad;
    private javax.swing.JTextField sPlaca;
    // End of variables declaration//GEN-END:variables
}
