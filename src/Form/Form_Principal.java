/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Clases.DatabaseConnection;
import Entidad.Avion;
import Entidad.Pasajero;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dairo Arenas
 */
public class Form_Principal extends javax.swing.JFrame {
    HashMap<String, String> hashmap = new HashMap<>();
    /**
     * Creates new form Form_Principal
     */
    JCheckBox[] checkBoxes;
    DefaultTableModel model_;
    
    String url = "jdbc:sqlserver://localhost:1433;";
    String database = "SkyVoyage";
    String user = "sa";
    String password = "12345";
    int nAsiento;
    
    DatabaseConnection conn = new DatabaseConnection(url,database,user,password);
    
    Avion avion = new Avion();
    
    public Form_Principal() {
        this.checkBoxes = new JCheckBox[0];
        initComponents();
        //this.checkBoxesA = new JCheckBox[]{A1, A2, A3, A4, A5};
        //this.checkBoxesB = new JCheckBox[]{B1, B2, B3, B4, B5};g
        this.setLocationRelativeTo(null);
       
        
        try(Connection con = conn.Connection()){
            if(con != null ){
                System.out.println("Conexión exitosa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Hola
    public void AsignarPasajero(HashMap hashmap,Pasajero pasajero) {
        // Crear el DefaultTableModel
        model_ = TableModel(); // Asegúrate de que TableModel() retorne el modelo correctamente

        // Llenar el modelo con los datos del pasajero
        LLenarModel(model_, pasajero); 

        // Establecer el modelo en la tabla
        TablePasajero.setModel(model_);

        // Actualizar los asientos en el HashMap según el pasajero
        avion.LlenarAsiento(hashmap, pasajero);
        
        avion.VisualizarHashMap(hashmap);
        
    }
    
    public DefaultTableModel TableModel(){
        DefaultTableModel model = (DefaultTableModel) TablePasajero.getModel();

            // Si no has añadido las columnas antes, puedes hacerlo aquí (solo una vez)
            if (model.getColumnCount() == 0) {
                model.addColumn("Documento");
                model.addColumn("Nombre");
                model.addColumn("Asiento");
            }
        return model;    
    }
    
    public void LLenarModel(DefaultTableModel model,Pasajero pasajero){
        model.addRow(new Object[]{
            pasajero.getDocumento(), 
            pasajero.getNombre(), 
            pasajero.getAsiento()
        });
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
        jPanel1 = new javax.swing.JPanel();
        Avion = new javax.swing.JLabel();
        BtnAsignar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablePasajero = new javax.swing.JTable();
        BtnEliminar = new javax.swing.JButton();
        PanelCheckBox = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nAsientos = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(Avion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 230));

        BtnAsignar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BtnAsignar.setText("Asignar Asiento");
        BtnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsignarActionPerformed(evt);
            }
        });

        TablePasajero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Nombre", "Asiento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TablePasajero);

        BtnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cantidad Asientos");

        nAsientos.setText("0");

        btnGenerar.setBackground(new java.awt.Color(153, 255, 153));
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(153, 255, 153));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCheckBoxLayout = new javax.swing.GroupLayout(PanelCheckBox);
        PanelCheckBox.setLayout(PanelCheckBoxLayout);
        PanelCheckBoxLayout.setHorizontalGroup(
            PanelCheckBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCheckBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCheckBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCheckBoxLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelCheckBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nAsientos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCheckBoxLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(38, 38, 38))))
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelCheckBoxLayout.setVerticalGroup(
            PanelCheckBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCheckBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsignarActionPerformed
    
        // Verificar que el hashmap no esté vacío
        if (hashmap == null || hashmap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay asientos disponibles para asignar.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir si no hay datos
        }

        // Crear y mostrar el formulario de pasajero
        Form_Pasajero pasajero = new Form_Pasajero(hashmap, Form_Principal.this);
        pasajero.setVisible(true);
        pasajero.setLocationRelativeTo(null);
        pasajero.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_BtnAsignarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int filaseleccionada = TablePasajero.getSelectedRow();
        String asientoPasajero;
        //tableEstudiante.remove
        model_ = TableModel();
        
        asientoPasajero = model_.getValueAt(filaseleccionada, 2).toString();
        
        hashmap.put(asientoPasajero,"0");
        
        avion.ValidarAsiento(hashmap,checkBoxes);
        //System.out.println(asientoPasajero);
        if(filaseleccionada != -1){
            model_.removeRow(filaseleccionada);
        }
        JOptionPane.showMessageDialog(null, "Registro eliminado");
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // Recuperar el número de asientos del campo de texto
        nAsiento = Integer.parseInt(nAsientos.getText());

        // Eliminar checkboxes existentes y limpiar el HashMap
        avion.EliminarAvion(hashmap, checkBoxes, Avion);

        // Generar nuevos checkboxes para los asientos
        avion.GenerarAvion(nAsiento, checkBoxes, Avion);

        // Llenar los checkboxes con la información del HashMap
        avion.LlenarAsiento(nAsiento, hashmap);

        // Actualizar la interfaz
        Avion.revalidate(); // Refresca el contenedor
        Avion.repaint();    // Asegura que se dibuje correctamente
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        // Validar y actualizar el estado de los JCheckBox según el HashMap
        avion.ValidarAsiento(hashmap,checkBoxes);
    }//GEN-LAST:event_btnActualizarActionPerformed

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
    private javax.swing.JLabel Avion;
    private javax.swing.JButton BtnAsignar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JPanel PanelCheckBox;
    private javax.swing.JTable TablePasajero;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nAsientos;
    // End of variables declaration//GEN-END:variables
}
