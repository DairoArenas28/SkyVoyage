/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author Dairo Arenas
 */
public class PasajeroAvionInfo {
    private String documento;
    private String nombre;
    private String apellido;
    private String placaAvion;
    private String asiento;

    public PasajeroAvionInfo(String documento, String nombre, String apellido, String placaAvion, String asiento) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.placaAvion = placaAvion;
        this.asiento = asiento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPlacaAvion() {
        return placaAvion;
    }

    public void setPlacaAvion(String placaAvion) {
        this.placaAvion = placaAvion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    
}
