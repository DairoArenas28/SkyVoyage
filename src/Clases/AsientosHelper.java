/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Dairo Arenas
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AsientosHelper {
    
    public void generarCheckBoxesAsientos(JPanel contenedorPrincipal, List<String> nombresAsientos, List<String> asientosOcupados) {
        // Crea un panel con GridLayout para organizar los JCheckBox
        contenedorPrincipal.removeAll();
        JPanel panelAsientos = new JPanel();
        panelAsientos.setLayout(new GridLayout(0, 10, 5, 5)); // 10 columnas por fila

        // Genera un JCheckBox para cada nombre de asiento
        for (String nombreAsiento : nombresAsientos) {
            JCheckBox checkBox = new JCheckBox(nombreAsiento);  // Asigna el nombre del asiento

            // Si el asiento está en la lista de asientos ocupados, lo marca (check)
            if (asientosOcupados.contains(nombreAsiento)) {
                checkBox.setSelected(true);  // Marca el JCheckBox como seleccionado
            }

            checkBox.setEnabled(false);  // Deshabilita el JCheckBox para que no se pueda interactuar
            panelAsientos.add(checkBox);
        }

        // Coloca el panel de asientos en un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(panelAsientos);
        scrollPane.setPreferredSize(new Dimension(500, 300));  // Ajusta el tamaño del JScrollPane

        // Crea un panel principal donde se agregará tanto el JLabel como el JScrollPane
        contenedorPrincipal.setLayout(new BorderLayout());

        // Crea un JLabel para mostrar algún texto o título
        JLabel labelAsientos = new JLabel("Asientos del avión");
        labelAsientos.setHorizontalAlignment(SwingConstants.CENTER);  // Centra el texto
        contenedorPrincipal.add(labelAsientos, BorderLayout.NORTH);  // Agrega el JLabel en la parte superior

        // Agrega el JScrollPane (con el panel de asientos) al contenedor principal
        contenedorPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Actualiza el contenedor principal para que los cambios sean visibles
        contenedorPrincipal.revalidate();
        contenedorPrincipal.repaint();
    }
}
