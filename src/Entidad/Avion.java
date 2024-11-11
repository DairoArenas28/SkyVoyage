/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import Clases.DatabaseConnection.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Dairo Arenas
 */
public class Avion {

    private int id;
    private String placa;
    private LocalDateTime FechaEntrada;
    private LocalDateTime FechaSalida;
    private int Asiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime FechaEntrada) {
        this.FechaEntrada = FechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(LocalDateTime FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public int getAsiento() {
        return Asiento;
    }

    public void setAsiento(int Asiento) {
        this.Asiento = Asiento;
    }
    
    
     public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    public static LocalDateTime parseFecha(String fechaStr) {
        return LocalDateTime.parse(fechaStr, formatter);
    }

    // Usar en tu RowMapper
    public static RowMapper<Avion> rowMapper() {
        return (rs) -> {
            Avion avion = new Avion();
            avion.setId(rs.getInt("Id"));
            avion.setPlaca(rs.getString("Placa"));
            
            // Parsear fechas con el nuevo formato
            avion.setFechaEntrada(parseFecha(rs.getString("FechaEntrada")));
            avion.setFechaSalida(parseFecha(rs.getString("FechaSalida")));
            
            avion.setAsiento(rs.getInt("Asiento"));
            return avion;
        };
    }

    
}
