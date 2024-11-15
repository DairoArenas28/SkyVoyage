/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Clases.AsientosHelper;
import Clases.DatabaseConnection;
import Entidad.Avion;
import Entidad.Pasajero;
import Entidad.PasajeroAvionInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dairo Arenas
 */
public class Form_Principal extends javax.swing.JFrame {
    /**
     * Creates new form Form_Principal
     */
    
    DefaultTableModel model_;
    
    String url = "jdbc:sqlserver://localhost:1433;";
    String database = "SkyVoyage";
    String user = "sa";
    String password = "12345";
    int nAsiento;
    Avion avionSeleccionado;
    int avionId;
    
    String letraAsiento;
    String numeroAsiento;
    
    DatabaseConnection dbConn = new DatabaseConnection(url,database,user,password);
    
    Avion avion = new Avion();
    
    public Form_Principal() {
        
        initComponents();
        ObtenerAvion();
        //this.checkBoxesA = new JCheckBox[]{A1, A2, A3, A4, A5};
        //this.checkBoxesB = new JCheckBox[]{B1, B2, B3, B4, B5};g
        this.setLocationRelativeTo(null);
        // Agrega el ActionListener para capturar el elemento seleccionado
        // Asegúrate de que dbConn esté correctamente inicializado antes de usarlo
        try {
            // Obtener los registros de la tabla "Avion" utilizando el rowMapper
            List<Avion> aviones = dbConn.obtenerRegistros("Avion", Avion.rowMapper());

            // Limpiar el JComboBox antes de llenarlo
            cAvion.removeAllItems();

            // Llenar el JComboBox con los aviones (puedes elegir mostrar la placa o el ID)
            for (Avion avion : aviones) {
                // Puedes agregar el ID o la placa del avión
                cAvion.addItem(
                        "Placa: " + avion.getPlaca() + 
                        " Fecha Entrada: " + avion.getFechaEntrada() + 
                        " Fecha Salida: " + avion.getFechaSalida() + 
                        " Asientos: " + avion.getAsiento()
                );
            }
            cAvion.addActionListener((java.awt.event.ActionEvent evt) -> {
                // Verifica si hay algún elemento seleccionado en el JComboBox
                int indiceSeleccionado = cAvion.getSelectedIndex();
                if (indiceSeleccionado != -1) {
                    // Obtén el avión correspondiente al índice seleccionado
                    avionSeleccionado = aviones.get(indiceSeleccionado);
                    avionId = avionSeleccionado.getId();
                    // Muestra el id del avión seleccionado
                    System.out.println("ID del avión seleccionado: " + avionId );
                    // Obtener los detalles de los asientos para el avión seleccionado
                    List<PasajeroAvionInfo> detalles = dbConn.obtenerInformacionPasajerosPorAvion(avionId);
                    detalles.sort(Comparator.comparing(PasajeroAvionInfo::getNombre));

                    llenarTablaPasajero(tablePasajero,detalles);

                    int cantidadAsientos = dbConn.obtenerCantidadAsientosPorAvion(avionId);

                    // Obtener los nombres de los asientos para el avión seleccionado
                    List<String> nombresAsientos = dbConn.obtenerNombresAsientosPorAvion(avionId);

                    // Obtener los asientos ocupados para el avión seleccionado
                    List<String> asientosOcupados = dbConn.obtenerAsientosOcupadosPorAvion(avionId);

                    // Generar los JCheckBox con los nombres de los asientos y actualizar el JLabel
                    AsientosHelper helper = new AsientosHelper();
                    helper.generarCheckBoxesAsientos(labelAsientos, nombresAsientos, asientosOcupados);
                }
            });
            // Mostrar los aviones obtenidos
            /*for (Avion avion : aviones) {
                System.out.println("ID: " + avion.getId());
                System.out.println("Placa: " + avion.getPlaca());
                System.out.println("Fecha Entrada: " + avion.getFechaEntrada());
                System.out.println("Fecha Salida: " + avion.getFechaSalida());
                System.out.println("Cantidad de Asientos: " + avion.getAsiento());
                System.out.println();
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void llenarTablaPasajero(JTable table, List<PasajeroAvionInfo> detalles) {
        // Define las columnas de la tabla
        String[] columnas = {"Documento", "Nombre", "Apellido", "Placa Avión", "Asiento"};

        // Crea el modelo de tabla con las columnas
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        // Llena el modelo con los datos de la lista de PasajeroAvionInfo
        for (PasajeroAvionInfo detalle : detalles) {
            Object[] fila = {
                detalle.getDocumento(),
                detalle.getNombre(),
                detalle.getApellido(),
                detalle.getPlacaAvion(),
                detalle.getAsiento()
            };
            model.addRow(fila);  // Agrega cada fila al modelo
        }

        // Asigna el modelo a la tabla
        table.setModel(model);
    }
    
    public void ObtenerAvion() {
        // Asegúrate de que dbConn esté correctamente inicializado antes de usarlo
        try {
            // Obtener los registros de la tabla "Avion" utilizando el rowMapper
            List<Avion> aviones = dbConn.obtenerRegistros("Avion", Avion.rowMapper());

            // Limpiar el JComboBox antes de llenarlo
            cAvion.removeAllItems();

            // Llenar el JComboBox con los aviones (puedes elegir mostrar la placa o el ID)
            for (Avion avion : aviones) {
                // Puedes agregar el ID o la placa del avión
                cAvion.addItem(
                        "Placa: " + avion.getPlaca() + 
                        " Fecha Entrada: " + avion.getFechaEntrada() + 
                        " Fecha Salida: " + avion.getFechaSalida() + 
                        " Asientos: " + avion.getAsiento()
                );
            }
            // Mostrar los aviones obtenidos
            /*for (Avion avion : aviones) {
                System.out.println("ID: " + avion.getId());
                System.out.println("Placa: " + avion.getPlaca());
                System.out.println("Fecha Entrada: " + avion.getFechaEntrada());
                System.out.println("Fecha Salida: " + avion.getFechaSalida());
                System.out.println("Cantidad de Asientos: " + avion.getAsiento());
                System.out.println();
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelAsientos = new javax.swing.JPanel();
        btnReasignar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePasajero = new javax.swing.JTable();
        btnEliminarAvion = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        cAvion = new javax.swing.JComboBox<>();
        btnEliminarPasajero = new javax.swing.JButton();
        btnAvion1 = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout labelAsientosLayout = new javax.swing.GroupLayout(labelAsientos);
        labelAsientos.setLayout(labelAsientosLayout);
        labelAsientosLayout.setHorizontalGroup(
            labelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        labelAsientosLayout.setVerticalGroup(
            labelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        getContentPane().add(labelAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 40, 740, 210));

        btnReasignar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReasignar.setText("Reasignar");
        btnReasignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReasignarActionPerformed(evt);
            }
        });
        getContentPane().add(btnReasignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 100, 30));

        tablePasajero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Edad", "Placa Avion", "Asiento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablePasajero);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 295, 740, 230));

        btnEliminarAvion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarAvion.setText("X");
        btnEliminarAvion.setPreferredSize(new java.awt.Dimension(81, 27));
        btnEliminarAvion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAvionActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarAvion, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 30, 30));

        btnIngresar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 100, 30));

        cAvion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cAvion.setToolTipText("");
        getContentPane().add(cAvion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, -1));

        btnEliminarPasajero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarPasajero.setText("Eliminar");
        btnEliminarPasajero.setPreferredSize(new java.awt.Dimension(81, 27));
        btnEliminarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPasajeroActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 100, 30));

        btnAvion1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAvion1.setText("Avion");
        btnAvion1.setPreferredSize(new java.awt.Dimension(81, 27));
        btnAvion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvion1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnAvion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 100, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReasignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReasignarActionPerformed
    
        
    }//GEN-LAST:event_btnReasignarActionPerformed

    private void btnEliminarAvionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAvionActionPerformed
        if (avionId != -1) {
            try {
                // Mostrar mensaje de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea eliminar al pasajero " + nombre + " con Documento " + documento + "?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION
                );

                boolean eliminarAvionDetalleId = dbConn.eliminarRegistroPorId("AvionDetalle", "AvionId", avionId);
                boolean eliminarAvion = dbConn.eliminarRegistroPorId("Avion", "Id", avionId);
                
                if(eliminarAvionDetalleId ){
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Form_Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Por favor, seleccione un pasajero para eliminar.",
                "Error",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnEliminarAvionActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
        Form_Pasajero pasajero = new Form_Pasajero(dbConn);
        pasajero.pack();
        pasajero.setVisible(true);
        pasajero.setLocationRelativeTo(null);
        pasajero.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnEliminarPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPasajeroActionPerformed
        int filaSeleccionada = tablePasajero.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el ID y nombre del pasajero seleccionado
            String documento = tablePasajero.getValueAt(filaSeleccionada, 0).toString(); // Columna ID (asumimos que es String)
            String nombre = tablePasajero.getValueAt(filaSeleccionada, 1).toString(); // Columna Nombre
            String asiento = tablePasajero.getValueAt(filaSeleccionada, 4).toString(); // Columna Nombre

            // Mostrar mensaje de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar al pasajero " + nombre + " con Documento " + documento + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    // Llamar al método para eliminar el registro de la base de datos
                    letraAsiento = asiento.replaceAll("[^A-Za-z]", ""); // Extrae la letra
                    numeroAsiento = asiento.replaceAll("[^0-9]", ""); // Extrae el número
                    boolean eliminarPasajero = dbConn.actualizarRegistroAvionDetalle(avionId,letraAsiento,numeroAsiento);
                    boolean eliminado = dbConn.eliminarRegistroPorColumna("Pasajero", "Documento", documento);

                    if (eliminado) {
                        // Eliminar la fila de la tabla
                        ((DefaultTableModel) tablePasajero.getModel()).removeRow(filaSeleccionada);
                        JOptionPane.showMessageDialog(
                            this,
                            "Pasajero eliminado correctamente.",
                            "Eliminación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar el pasajero. Verifique que el ID sea correcto.",
                            "Error al Eliminar",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Error al eliminar el pasajero: " + ex.getMessage(),
                        "Error de Base de Datos",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Por favor, seleccione un pasajero para eliminar.",
                "Error",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnEliminarPasajeroActionPerformed

    private void btnAvion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvion1ActionPerformed
        // TODO add your handling code here:
        Form_Avion avion = new Form_Avion(dbConn);
        avion.setVisible(true);
        avion.setLocationRelativeTo(null);
        avion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnAvion1ActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvion1;
    private javax.swing.JButton btnEliminarAvion;
    private javax.swing.JButton btnEliminarPasajero;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnReasignar;
    private javax.swing.JComboBox<String> cAvion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel labelAsientos;
    private javax.swing.JTable tablePasajero;
    // End of variables declaration//GEN-END:variables
}
