/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author Dairo Arenas
 */
public class ConnectionDB {
    private String connectionURL;
    private Connection conn = null;

    PreparedStatement stmt = null;
    String sDriver = "com.mysql.jdbc.Driver";
    String sURL = "jdbc:mysql://localhost:3306/lineadecodigo";
    
    public ConnectionDB(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    
    public Connection Connection(){
        try{
            conn = DriverManager.getConnection(sURL,"root","");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
