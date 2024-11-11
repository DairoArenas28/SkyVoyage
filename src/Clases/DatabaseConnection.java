/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dairo Arenas
 * @param <T>
 */
public class DatabaseConnection<T> {
    private String url;    
    private String database;
    private String user;
    private String password;
    private String urlFinal;
    private Connection conn;

    public DatabaseConnection(String url_,String database_, String user_, String password_ ){
        this.url = url_;
        this.database = database_;
        this.user = user_;
        this.password = password_;
        this.urlFinal = url + "database=" + database + ";encrypt=true;trustServerCertificate=true;loginTimeout=30;";
        try {
            this.conn = Connection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection Connection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(urlFinal, user, password);
            if (conn != null) {
                System.out.println("Conexión exitosa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
            throw e;
        }
        return conn;
    }
    
    public void cerrarConexion() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    
    public interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }
    
    public boolean insertarRegistro(String tableName, Map<String, Object> columnValues) throws SQLException {
        // Generar los nombres de las columnas y los placeholders para los valores
        String columns = String.join(", ", columnValues.keySet());
        String placeholders = String.join(", ", Collections.nCopies(columnValues.size(), "?"));

        // Crear la consulta dinámica
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            int index = 1;
            for (Object value : columnValues.values()) {
                pstmt.setObject(index++, value);
            }
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // Método genérico para obtener todos los registros de una tabla
    public List<T> obtenerRegistros(String tableName, RowMapper<T> mapper) throws SQLException {
        List<T> registros = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                registros.add(mapper.mapRow(rs));
            }
        }
        return registros;
    }

    // Método genérico para obtener varios registros en función de una columna
    public List<T> obtenerRegistrosPorColumna(String tableName, String columnName, Object value, RowMapper<T> mapper) throws SQLException {
        List<T> registros = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, value);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    registros.add(mapper.mapRow(rs));
                }
            }
        }
        return registros;
    }
    // Método genérico para obtener un registro por ID
    public T obtenerRegistroPorId(String tableName, String idColumnName, int id, RowMapper<T> mapper) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?";
        T registro = null;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    registro = mapper.mapRow(rs);
                }
            }
        }
        return registro;
    }

    // Método genérico para actualizar un registro (los campos deben ser definidos en el query)
    public boolean actualizarRegistro(String query, Object... params) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate() > 0;
        }
    }

    // Método genérico para eliminar un registro por ID
    public boolean eliminarRegistroPorId(String tableName, String idColumnName, int id) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
    
}
