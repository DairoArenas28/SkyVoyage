/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Dairo Arenas
 */
public class DatabaseConnection {
    private String url;    
    private String database;
    private String user;
    private String password;
    private String urlFinal;

    public DatabaseConnection(String url_,String database_, String user_, String password_ ) {
        this.url = url_;
        this.database = database_;
        this.user = user_;
        this.password = password_;
        this.urlFinal = url + "database=" + database + ";encrypt=true;trustServerCertificate=true;loginTimeout=30;";
    }

    public Connection Connection() throws SQLException{
       return DriverManager.getConnection(urlFinal, user, password);
    }
}
