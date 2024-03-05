import InterfazGráfica.*;
import javax.swing.JFrame;

/**
 * Clase principal que inicia el juego.
 */
public class Main {
    public static void main(String[] args) {
        // Crear una nueva ventana de juego
        JFrame ventana = new JFrame();

        // Configurar la ventana para que se cierre cuando se presione el botón de cierre
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Evitar que la ventana sea redimensionable
        ventana.setResizable(false);

        // Establecer el título de la ventana
        ventana.setTitle("BadIceCream");

        // Crear un nuevo panel de juego
        PanelJuego panelJuego = new PanelJuego();

        // Agregar el panel de juego a la ventana
        ventana.add(panelJuego);

        // Ajustar el tamaño de la ventana para que se ajuste a sus subcomponentes
        ventana.pack();

        // Centrar la ventana en la pantalla
        ventana.setLocationRelativeTo(null);

        // Hacer visible la ventana
        ventana.setVisible(true);

        // Configurar el juego
        panelJuego.configurarJuego();

        // Iniciar el hilo del juego
        panelJuego.iniciarHiloJuego();
    }
}
