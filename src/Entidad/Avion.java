/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author Dairo Arenas
 */
public class Avion {

    int asientos;
    static String[] abecedario = new String[]{
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    
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
    
    public void VisualizarHashMap(HashMap<String, String> hashmap) {
    // Verificamos si el HashMap está vacío
    if (hashmap.isEmpty()) {
        System.out.println("El HashMap está vacío.");
    } else {
        // Iteramos sobre las entradas del HashMap
        for (Map.Entry<String, String> entry : hashmap.entrySet()) {
            String clave = entry.getKey();
            String valor = entry.getValue();
            System.out.println("Clave: " + clave + ", Valor: " + valor);
        }
    }
}
    
    public final void ValidarAsiento(HashMap<String, String> hashmap, JCheckBox[] checkBoxes) {
        int maxAsientos = 25; // Número máximo de asientos por letra
        int maxLetras = abecedario.length; // Número total de letras en el abecedario

        for (int i = 0; i < checkBoxes.length; i++) {
            // Asegúrate de que i esté dentro de los límites del abecedario
            if (i / maxAsientos < maxLetras) {
                String letra = abecedario[i / maxAsientos];
                int numero = i % maxAsientos;
                String key = letra + numero;

                // Deshabilitar el checkbox
                checkBoxes[i].setEnabled(false); // Deshabilitar el checkbox

                if (hashmap.containsKey(key)) {
                    // Seleccionar el checkbox si el valor es "1"
                    checkBoxes[i].setSelected("1".equals(hashmap.get(key)));
                } else {
                    // Asegurarse de que esté deseleccionado si la clave no está en el hashmap
                    checkBoxes[i].setSelected(false);
                }
            }
        }
    }
    
    public static void LlenarAsiento(HashMap hashmap,Pasajero pasajero){
        System.out.println(pasajero.getAsiento());
        hashmap.put(pasajero.getAsiento(),"1");
    }
    
    public static void LlenarAsiento(int nAsientos,HashMap hashmap){
        // Ciclo para recorrer las checkboxes
        for (int i = 0; i < nAsientos; i++) {
            String letra = abecedario[i / 25];
            int numero = i % 25;
            // Asigna "0" a las entradas A0, A1, ..., A4 (disponible)
            hashmap.put(letra + numero, "0"); // Asignar "0" para indicar que está disponible
        }
    }
    
   public void GenerarAvion(int nAsiento, JCheckBox[] checkBoxes, JLabel avion) {
        checkBoxes = new JCheckBox[nAsiento];
        // Cambia el layout a GridLayout, especificando cuántas filas deseas
        int filas = 10; // Número de filas que quieres en tu disposición
        int columnas = (int) Math.ceil((double) nAsiento / filas); // Calcular el número de columnas necesarias
        avion.setLayout(new GridLayout(filas, columnas)); 
        for (int i = 0; i < nAsiento; i++) {
            String letra = abecedario[i / 25];
            int numero = i % 25;
            checkBoxes[i] = new JCheckBox(); // Crear un nuevo JCheckBox

            // Asignar el nombre
            String nombre = letra + numero;
            checkBoxes[i].setName(nombre);
            checkBoxes[i].setText(nombre); // También puedes mostrar el nombre en el checkbox
            checkBoxes[i].setEnabled(false); // Deshabilitar el checkbox
            avion.add(checkBoxes[i]);

            // Imprimir el nombre para verificar
            System.out.println("Checkbox creado: " + nombre);
        }

        // Aquí puedes llamar a ValidarAsiento con tu HashMap
        HashMap<String, String> hashmap = new HashMap<>();
        // Asegúrate de llenar el hashmap con las claves y valores necesarios aquí.
        ValidarAsiento(hashmap, checkBoxes);
    }
    
    public void EliminarAvion(HashMap<String, String> hashmap, JCheckBox[] checkBoxes, JLabel avion) {
         // Quita el layout actual
        avion.removeAll(); 

        // Quita los checkboxes del contenedor
        if (checkBoxes != null) {
            for (JCheckBox checkBox : checkBoxes) {
                avion.remove(checkBox);
            }
        }

        avion.revalidate(); // Actualiza el contenedor después de quitar componentes
        avion.repaint();    // Refresca el contenedor en pantalla
        hashmap.clear();    // Limpia el HashMap
    }
}
