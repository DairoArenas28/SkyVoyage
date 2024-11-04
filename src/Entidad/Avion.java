/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author Dairo Arenas
 */
public class Avion {

    int asientos;
    
    
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
    
    public static void LlenarAsiento(HashMap hashmap,Pasajero pasajero){
        hashmap.put(pasajero.getAsiento(),"1");
    }
    
    public static void LlenarAsiento(int nAsientos,HashMap hashmap){
        // Ciclo para recorrer las checkboxes
        for (int i = 0; i < nAsientos; i++) {
            // Asigna "0" a las entradas A0, A1, ..., A4 (disponible)
            hashmap.put("A" + i, "0"); // Asignar "0" para indicar que está disponible
            hashmap.put("B" + i, "0"); // Asignar "0" para indicar que está disponible
            
        }
    }
    
    public void GenerarAvion(int nAsiento,JCheckBox[] checkBoxes, JLabel Avion){
        checkBoxes = new JCheckBox[nAsiento];
        Avion.setLayout(new FlowLayout()); 
        for(int i = 0; i < nAsiento; i++){
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setSelected(false);
            checkBoxes[i].setEnabled(false);
            checkBoxes[i].setName("Asiento"+(i+1));

            //checkBoxes[i].setLocation(50, 150);
            Avion.add(checkBoxes[i]);
        }
        
    }
    
    public void EliminarAvion(HashMap hashmap, JCheckBox[] checkBoxes, JLabel Avion){
        Avion.setLayout(new FlowLayout()); 
        if (checkBoxes.length > 0) {
            for (JCheckBox checkBox : checkBoxes) {
                Avion.remove(checkBox); // Quita el checkbox del contenedor
            }
            Avion.revalidate(); // Actualiza el contenedor después de quitar componentes
            Avion.updateUI();// Refresca el contenedor en pantalla
            hashmap.clear(); // Limpia el HashMap
        }
    }
}
