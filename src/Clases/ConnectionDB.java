/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dairo Arenas
 */
public class ConnectionDB {
    private String database;
    private String user;
    private String password;

    public ConnectionDB(String database_, String user_, String password_ ) {
        this.database = database_;
        this.user = user_;
        this.password = password_;
    }
   
  
    String url = "jdbc:sqlserver://localhost:1433;database="+database;

    public void Connection(){
       try {
       Connection  connection = DriverManager.getConnection(url,user,password);
       if (connection != null) {
              System.out.println("Conexión exitosa a la base de datos.");
          }

       } catch (SQLException e) {
           //JOptionPane.showMessageDialog(null,"Error en la conexion " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           System.out.println(e.getMessage());
       }
    }
}
