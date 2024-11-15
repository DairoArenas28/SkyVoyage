/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Clases.DatabaseConnection;
import Entidad.Avion;
import Entidad.AvionDetalle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    DatabaseConnection dbConn;
    Avion avionSeleccionado;
    int avionId;
    String letraAsiento;
    int numeroAsiento;
    /**
     * Creates new form Form_Pasajero
     * @param dbConn_
     */
    public Form_Pasajero(DatabaseConnection dbConn_) {
        try {
            initComponents();
            this.dbConn = dbConn_;
            List<Avion> aviones = dbConn.obtenerRegistros("Avion", Avion.rowMapper());
            // Limpiar el JComboBox antes de llenarlo
            cAvion.removeAllItems();

            // Llenar el JComboBox con los aviones (puedes elegir mostrar la placa o el ID)
            for (Avion avion : aviones) {
                // Puedes agregar el ID o la placa del avión
                cAvion.addItem(
                        "Id: " + avion.getId() +
                        " Placa: " + avion.getPlaca() + 
                        " Fecha Entrada: " + avion.getFechaEntrada() + 
                        " Fecha Salida: " + avion.getFechaSalida() 
                );
            }

            // Agrega el ActionListener para capturar el elemento seleccionado
            cAvion.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Verifica si hay algún elemento seleccionado en el JComboBox
            int indiceSeleccionado = cAvion.getSelectedIndex();
            if (indiceSeleccionado != -1) {
                // Obtén el avión correspondiente al índice seleccionado
                avionSeleccionado = aviones.get(indiceSeleccionado);
                avionId = avionSeleccionado.getId();
                // Muestra el id del avión seleccionado
                System.out.println("ID del avión seleccionado: " + avionId );
                try {
                    // Obtener los detalles de los asientos para el avión seleccionado
                    List<AvionDetalle> detalles = dbConn.obtenerRegistrosPorColumna(
                        "AvionDetalle", "AvionId", avionSeleccionado.getId(), 
                        new AvionDetalle.AvionDetalleRowMapper()
                    );
                    detalles.sort(Comparator.comparingInt(AvionDetalle::getAsientoNumero) // Ordenar primero por numero
                            .thenComparing(AvionDetalle::getAsientoLetra)); // Luego por letra
                    // Limpiar el JComboBox de asientos antes de llenarlo
                    cAsiento.removeAllItems();

                    // Llenar el JComboBox con los asientos disponibles
                    for (AvionDetalle avionDetalle : detalles) {
                        if (!avionDetalle.isInactivo()) { // Solo asientos activos
                            // Muestra la letra y número del asiento en el JComboBox
                            String asientoInfo = avionDetalle.getAsientoLetra() + avionDetalle.getAsientoNumero();
                            cAsiento.addItem(asientoInfo);

                            // Imprimir en consola para verificar
                            //System.out.println("Asiento disponible: " + asientoInfo);
                        }
                    }

                    // Mensaje en caso de que no haya asientos disponibles
                    if (cAsiento.getItemCount() == 0) {
                        cAsiento.addItem("No hay asientos disponibles");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Form_Pasajero.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            // Suponiendo que el JComboBox es cAsiento
        cAsiento.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Obtener el valor seleccionado
                    String asientoSeleccionado = (String) cAsiento.getSelectedItem();

                    // Mostrar el asiento seleccionado
                    System.out.println("Asiento seleccionado: " + asientoSeleccionado);

                    // Separar la letra y el número del asiento
                    if (asientoSeleccionado != null && !asientoSeleccionado.isEmpty()) {
                        // Usar expresión regular para separar la letra (primer carácter) y el número
                        letraAsiento = asientoSeleccionado.replaceAll("[^A-Za-z]", ""); // Extrae la letra
                        String numero = asientoSeleccionado.replaceAll("[^0-9]", ""); // Extrae el número

                        // Convertir el número a entero
                        numeroAsiento = Integer.parseInt(numero);

                        // Mostrar los resultados en consola
                        //System.out.println("Letra del asiento: " + letraAsiento);
                        //System.out.println("Número del asiento: " + numeroAsiento);

                        // Ahora puedes usar la letra y el número del asiento, por ejemplo, para asignarlo al pasajero
                    }
                }
            }
        });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.formPrincipal, "Error al inicializar el formulario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Renderizador personalizado para desactivar las opciones
    /*private void aplicarRenderizador(JComboBox<String> comboBox, boolean isLetras) {
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
    }*/

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

        txtDocumento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cAvion = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        cAsiento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        nEdad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Documento");

        jLabel2.setText("Nombre");

        jLabel3.setText("Avion");

        cAvion.setSelectedItem(hashmap);

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cAsiento.setSelectedItem(hashmap);

        jLabel4.setText("Asiento Disponible");

        jLabel5.setText("Apellido");

        jLabel6.setText("Edad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre)
                    .addComponent(txtDocumento)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(cAvion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cAsiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtApellido)
                    .addComponent(nEdad))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cAvion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String documento = txtDocumento.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int edad = Integer.parseInt(nEdad.getText());

            if (documento.equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor, digita un documento", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            
            if (nombre.equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor, digita un nombre", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            
            if (apellido.equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor, digita un documento", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            
            // Crear el mapa con los valores de las columnas
            Map<String, Object> columnValues = new HashMap<>();
            columnValues.put("Documento", documento);
            columnValues.put("Nombre", nombre);
            columnValues.put("Apellido", apellido);
            columnValues.put("Edad", edad);
            
            if(dbConn.insertarRegistro("Pasajero", columnValues)){
                String query = "SELECT IDENT_CURRENT('Pasajero')";
                int pasajeroId = -1;
                try (java.sql.Statement stmt = dbConn.Connection().createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
                        pasajeroId = rs.getInt(1); // Obtener el ID del avión
                        //System.out.println("ID del avión insertado con IDENT_CURRENT: " + pasajeroId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(pasajeroId != -1){
                    // Generar la consulta de actualización
                    String qry = "UPDATE AvionDetalle SET Inactivo = 1,PasajeroId = ? WHERE AvionId = ? AND AsientoLetra = ? AND AsientoNumero = ?";

                    // Ejecutar la actualización
                    try {
                        System.out.println("Pasajero: " + pasajeroId + " Avion: " + avionId + " Letra " + letraAsiento + " Numero: " + numeroAsiento);
                        boolean actualizado = dbConn.actualizarRegistro(qry, pasajeroId, avionId, letraAsiento, numeroAsiento);
                        if (actualizado) {
                            //System.out.println("El asiento fue actualizado exitosamente.");
                            JOptionPane.showMessageDialog(this, "El asiento fue asignado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            //System.out.println("No se pudo actualizar el asiento.");
                            JOptionPane.showMessageDialog(this, "No se pudo actualizar el asiento.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        //System.out.println("Error al actualizar el asiento: " + ex.getMessage());
                        JOptionPane.showMessageDialog(this, "Error al actualizar el asiento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el pasajero: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cAsiento;
    private javax.swing.JComboBox<String> cAvion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nEdad;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
