/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.sql.*;
import Clases.DatabaseConnection.RowMapper;

/**
 *
 * @author Dairo Arenas
 */
public class AvionDetalle {
    private int id;
    private int avionId;
    private Integer pasajeroId; // Puede ser null si el asiento está libre
    private String asientoLetra;
    private int asientoNumero;
    private boolean inactivo; // true si el asiento está ocupado

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvionId() {
        return avionId;
    }

    public void setAvionId(int avionId) {
        this.avionId = avionId;
    }

    public Integer getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(Integer pasajeroId) {
        this.pasajeroId = pasajeroId;
    }

    public String getAsientoLetra() {
        return asientoLetra;
    }

    public void setAsientoLetra(String asientoLetra) {
        this.asientoLetra = asientoLetra;
    }

    public int getAsientoNumero() {
        return asientoNumero;
    }

    public void setAsientoNumero(int asientoNumero) {
        this.asientoNumero = asientoNumero;
    }

    public boolean isInactivo() {
        return inactivo;
    }

    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }
    
    // Clase estática interna para mapear ResultSet a AvionDetalle
    public static class AvionDetalleRowMapper implements RowMapper<AvionDetalle> {
        @Override
        public AvionDetalle mapRow(ResultSet rs) throws SQLException {
            AvionDetalle detalle = new AvionDetalle();
            detalle.setId(rs.getInt("Id"));
            detalle.setAvionId(rs.getInt("AvionId"));
            detalle.setPasajeroId(rs.getObject("PasajeroId") != null ? rs.getInt("PasajeroId") : null);
            detalle.setAsientoLetra(rs.getString("AsientoLetra"));
            detalle.setAsientoNumero(rs.getInt("AsientoNumero"));
            detalle.setInactivo(rs.getBoolean("Inactivo"));
            return detalle;
        }
    }
}
