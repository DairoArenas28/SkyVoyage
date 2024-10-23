/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package skyvoyage;

import Clases.Pasajero;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Dairo Arenas
 */
public class SkyVoyage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int fil = 5;
        int col = 2;
        int avion[][] = new int[fil][col];
        
        String nombre;
        String documento;
        Scanner scan = new Scanner(System.in);
        
        HashMap<String, String> hashmap = new HashMap<>();
        for(int i = 0; i < 5; i++){
            hashmap.put("A"+i, "0");
            hashmap.put("B"+i, "0");
        }
        for(int j = 0; j < 5; j++){
            System.out.print(hashmap.get("A"+j));
            System.out.print(hashmap.get("B"+j));
        }
        //System.out.println(hashmap.get("A1"));
        System.out.println("Digita el nombre del pasajero");
        nombre = scan.next();
        
        System.out.println("Digita el documento del pasajero");
        documento = scan.next();
        
        Pasajero pasajero = new Pasajero(nombre,documento,"");
        
        System.out.println("Puestos disponibles");
         
        for(int i = 0; i < fil; i++){
            for(int j = 0; j < col; j++){
                avion[i][j] = 0;
            }
        }
        
        for(int i = 0; i < fil; i++){
            for(int j = 0; j < col; j++){
                 System.out.print(avion[i][j]);
            }
            System.out.println(" ");
        }
        
        System.out.println("Selecciona ubicacion de ");
    }
    
}
