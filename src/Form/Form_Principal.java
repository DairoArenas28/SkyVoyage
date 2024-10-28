/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Clases.ConnectionDB;
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
    JCheckBox[] checkBoxesA;
    JCheckBox[] checkBoxesB; 
    DefaultTableModel model_;
    
    String database = "Facturacion";
    String user = "sa";
    String password = "12345";
    
    ConnectionDB conn = new ConnectionDB(database,user,password);
    
    public Form_Principal() {
        initComponents();
        this.checkBoxes = new JCheckBox[100];
        //this.checkBoxesA = new JCheckBox[]{A1, A2, A3, A4, A5};
        //this.checkBoxesB = new JCheckBox[]{B1, B2, B3, B4, B5};
        this.setLocationRelativeTo(null);
        conn.Connection();
        
        GenerarAvion();
        LlenarAsiento(hashmap);
        
        
/*for(int j = 0; j < 5; j++){
            System.out.print(hashmap.get("A"+j));
            System.out.print(hashmap.get("B"+j));
        }*/
        
    }
    public final void DesmarcarCheckBoxes(JCheckBox[] checkBoxesA,JCheckBox[] checkBoxesB) {
        // Desmarcar todos los checkboxes en checkBoxesA
        for (JCheckBox checkBox : checkBoxesA) {
            if (checkBox != null) {
                checkBox.setSelected(false);
            }
        }

        // Desmarcar todos los checkboxes en checkBoxesB
        for (JCheckBox checkBox : checkBoxesB) {
            if (checkBox != null) {
                checkBox.setSelected(false);
            }
        }
    }
    public final void ValidarAsiento(HashMap hashmap, JCheckBox[] checkBoxesA, JCheckBox[] checkBoxesB){
        for (int i = 0; i < 5; i++) {
            if (hashmap.get("A" + i).equals("0")) { 
                checkBoxesA[i].setSelected(false); // Deselecciona si es "0"
            } else {
                checkBoxesA[i].setSelected(true); // Selecciona si es "1"
            }

            // Verificar y asignar el estado de los checkboxes de B
            if (hashmap.get("B" + i).equals("0")) { 
                checkBoxesB[i].setSelected(false); // Deselecciona si es "0"
            } else {
                checkBoxesB[i].setSelected(true); // Selecciona si es "1"
            }
        }

    }
    
    public static void LlenarAsiento(HashMap hashmap){
        // Ciclo para recorrer las checkboxes
        for (int i = 0; i < 5; i++) {
            // Asigna "0" a las entradas A0, A1, ..., A4 (disponible)
            hashmap.put("A" + i, "0"); // Asignar "0" para indicar que está disponible
            hashmap.put("B" + i, "0"); // Asignar "0" para indicar que está disponible
            
        }
    }
    
    public void GenerarAvion(){
        Avion.setLayout(new FlowLayout()); 
        for(int i = 0; i < 100; i++){
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setSelected(false);
            checkBoxes[i].setEnabled(false);
            checkBoxes[i].setName("Asiento"+(i+1));

            //checkBoxes[i].setLocation(50, 150);
            Avion.add(checkBoxes[i]);
        }
        
    }
    
    public static void LlenarAsiento(HashMap hashmap,Pasajero pasajero){
        hashmap.put(pasajero.getAsiento(),"1");
    }
    
    public void AsignarPasajero(HashMap hashmap,Pasajero pasajero) {
        // Crear el DefaultTableModel
        model_ = TableModel(); // Asegúrate de que TableModel() retorne el modelo correctamente

        // Llenar el modelo con los datos del pasajero
        LLenarModel(model_, pasajero); 

        // Establecer el modelo en la tabla
        TablePasajero.setModel(model_);

        // Actualizar los asientos en el HashMap según el pasajero
        LlenarAsiento(hashmap, pasajero);

        // Validar y actualizar el estado de los JCheckBox según el HashMap
        ValidarAsiento(hashmap,checkBoxesA,checkBoxesB);
        
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

        jPanel1 = new javax.swing.JPanel();
        Avion = new javax.swing.JLabel();
        BtnAsignar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablePasajero = new javax.swing.JTable();
        BtnEliminar = new javax.swing.JButton();
        PanelCheckBox = new javax.swing.JPanel();

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

        javax.swing.GroupLayout PanelCheckBoxLayout = new javax.swing.GroupLayout(PanelCheckBox);
        PanelCheckBox.setLayout(PanelCheckBoxLayout);
        PanelCheckBoxLayout.setHorizontalGroup(
            PanelCheckBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );
        PanelCheckBoxLayout.setVerticalGroup(
            PanelCheckBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BtnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(PanelCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
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
        
        ValidarAsiento(hashmap,checkBoxesA,checkBoxesB);
        //System.out.println(asientoPasajero);
        if(filaseleccionada != -1){
            model_.removeRow(filaseleccionada);
        }
        JOptionPane.showMessageDialog(null, "Registro eliminado");
    }//GEN-LAST:event_BtnEliminarActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
