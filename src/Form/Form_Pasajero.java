/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Entidad.Pasajero;
import Form.Form_Principal;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dairo Arenas
 */
public class Form_Pasajero extends javax.swing.JFrame {
    String nombre;
    String documento;
    String asiento;
    HashMap<String, String> hashmap = new HashMap<>();
    Form_Principal formPrincipal;
    /**
     * Creates new form Form_Pasajero
     * @param hashmap_
     * @param principal
     */
    public Form_Pasajero(HashMap<String, String> hashmap_, Form_Principal principal) {
        try {
            this.formPrincipal = principal;
            initComponents();

            this.hashmap = hashmap_;

            
            // Llenar ComboAsientoAbecedario con letras (A-Z) según el HashMap
            for (char letra = 'A'; letra <= 'Z'; letra++) {
                String key = String.valueOf(letra);
                // Solo agregar si existen asientos de esa letra en el hashmap
                boolean hayAsientos = false;
                for (int numero = 0; numero < 25; numero++) { // Asientos del 0 al 24
                    String asientoKey = key + numero; // Combinación letra + número
                    if (hashmap.containsKey(asientoKey)) {
                        hayAsientos = true; // Al menos un asiento existe
                        break; // No es necesario seguir buscando
                    }
                }
                if (hayAsientos) {
                    ComboAsientoAbecedario.addItem(key); // Agregar letra si tiene asientos
                }
            }

            // Llenar ComboAsientoNumero con números del 0 al 24 según el HashMap
            for (int numero = 0; numero < 25; numero++) { // Cambié 0-24 para los números
                String key = String.valueOf(numero);
                // Solo agregar si existe en el hashmap al menos una letra
                boolean hayAsientos = false;
                for (char letra = 'A'; letra <= 'Z'; letra++) {
                    String asientoKey = letra + key; // Combinación letra + número
                    if (hashmap.containsKey(asientoKey)) {
                        hayAsientos = true; // Al menos un asiento existe
                        break; // No es necesario seguir buscando
                    }
                }
                if (hayAsientos) {
                    ComboAsientoNumero.addItem(key); // Agregar número si tiene asientos
                }
            }

            // Aplicar renderizador a ambos JComboBox
            aplicarRenderizador(ComboAsientoAbecedario, true);
            aplicarRenderizador(ComboAsientoNumero, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.formPrincipal, "Error al inicializar el formulario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Renderizador personalizado para desactivar las opciones
    private void aplicarRenderizador(JComboBox<String> comboBox, boolean isLetras) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value != null) {
                    String key = value.toString();
                    if (isLetras) {
                        // Para las letras, verificar los números asociados
                        boolean letraActiva = false;
                        for (int numero = 0; numero < 25; numero++) {
                            String asientoKey = key + numero; // Combinación letra + número
                            if (hashmap.containsKey(asientoKey) && "0".equals(hashmap.get(asientoKey))) {
                                letraActiva = true; // Al menos un número está activo
                                break;
                            }
                        }
                        // Deshabilitar la letra si no hay asientos disponibles
                        if (!letraActiva) {
                            c.setForeground(Color.GRAY); // Deshabilitar en gris
                        } else {
                            c.setForeground(Color.BLACK); // Activo en negro
                        }
                    } else {
                        // Para los números, verificar su propio estado
                        String asientoStatus = hashmap.getOrDefault(key, "0");
                        if ("1".equals(asientoStatus)) {
                            c.setForeground(Color.GRAY); // Deshabilitar en gris
                        } else {
                            c.setForeground(Color.BLACK); // Activo en negro
                        }
                    }
                } else {
                    c.setForeground(Color.BLACK); // Color predeterminado para elementos nulos
                }

                return c;
            }
        });
    }

//    // Método para ordenar los elementos del JComboBox alfabéticamente
//    private void ordenarComboBox(JComboBox<String> comboBox) {
//        // Obtener los elementos actuales en una lista
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < comboBox.getItemCount(); i++) {
//            items.add(comboBox.getItemAt(i));
//        }
//
//        // Ordenar la lista alfabéticamente
//        Collections.sort(items);
//
//        // Limpiar y volver a agregar los elementos ordenados
//        comboBox.removeAllItems();
//        for (String item : items) {
//            comboBox.addItem(item);
//        }
//    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtDocumento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TxtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboAsientoAbecedario = new javax.swing.JComboBox<>();
        BtnGuardar = new javax.swing.JButton();
        ComboAsientoNumero = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Documento");

        jLabel2.setText("Nombre");

        jLabel3.setText("Asientos disponibles");

        ComboAsientoAbecedario.setSelectedItem(hashmap);

        BtnGuardar.setText("GUARDAR");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        ComboAsientoNumero.setSelectedItem(hashmap);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtNombre)
                    .addComponent(TxtDocumento)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ComboAsientoAbecedario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboAsientoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboAsientoAbecedario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboAsientoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(BtnGuardar)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        try {
            String nombre = TxtNombre.getText();
            String documento = TxtDocumento.getText();

            if (nombre.equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor, digita un nombre", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            if (documento.equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor, digita un documento", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            } 

            // Verificar si el ComboBox de letras está habilitado y si se ha seleccionado un asiento
            if (!ComboAsientoAbecedario.isEnabled() || ComboAsientoAbecedario.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un asiento válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            } 

            // Verificar si el ComboBox de números está habilitado y si se ha seleccionado un número
            if (!ComboAsientoNumero.isEnabled() || ComboAsientoNumero.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un número de asiento válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            } 

            // Obtener los valores seleccionados
            String letraAsiento = (String) ComboAsientoAbecedario.getSelectedItem();
            String numeroAsiento = (String) ComboAsientoNumero.getSelectedItem();

            if (letraAsiento == null || numeroAsiento == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un asiento.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            // Combinar letra y número para verificar el estado
            String keyAsiento = letraAsiento + numeroAsiento;

            // Verificar el estado del asiento en el HashMap
            String asientoStatus = hashmap.getOrDefault(keyAsiento, "0");

            if ("1".equals(asientoStatus)) {
                // Si el asiento está marcado como deshabilitado, mostrar mensaje
                JOptionPane.showMessageDialog(this, "Este asiento no está disponible. Por favor, selecciona otro.", "Asiento No Disponible", JOptionPane.ERROR_MESSAGE);
                ComboAsientoAbecedario.setSelectedIndex(-1); // Resetea la selección
                return; 
            }

            // Si el asiento está disponible, crear el objeto Pasajero
            Pasajero pasajero = new Pasajero(nombre, documento, keyAsiento);
            formPrincipal.AsignarPasajero(hashmap, pasajero);
            this.hide(); // Cierra el formulario correctamente

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el pasajero: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Pasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Pasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Pasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Pasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Form_Pasajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JComboBox<String> ComboAsientoAbecedario;
    private javax.swing.JComboBox<String> ComboAsientoNumero;
    private javax.swing.JTextField TxtDocumento;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
