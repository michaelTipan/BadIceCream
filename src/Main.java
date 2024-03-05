import InterfazGráfica.*;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar Ventana
        ventana.setResizable(false); // No se puede smodificar el tamaño de la ventana
        ventana.setTitle("BadIceCream"); // Título del juego

        PanelJuego panelJuego = new PanelJuego();
        ventana.add(panelJuego);
        ventana.pack();

        ventana.setLocationRelativeTo(null); // Centrar ventana
        ventana.setVisible(true); // Mostrar ventana

        panelJuego.configurarJuego(); // Configurar juego
        panelJuego.iniciarHiloJuego();
    }
}