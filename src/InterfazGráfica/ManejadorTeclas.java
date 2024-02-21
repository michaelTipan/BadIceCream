package InterfazGráfica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La clase ManejadorTeclas implementa KeyListener y se encarga de gestionar las entradas del teclado.
 */
public class ManejadorTeclas implements KeyListener {

    // Variables booleanas para cada dirección posible
    public boolean arribaPresionado, abajoPresionado, izquierdaPresionado, derechaPresionado;

    /**
     * Este método se invoca cuando se presiona una tecla y luego se suelta. No se utiliza en esta implementación.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Este método se invoca cuando se presiona una tecla. Se utiliza para cambiar el estado de las variables de dirección.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode(); // Número de tecla presionada

        // Cambiar el estado de las variables de dirección en función de la tecla presionada
        if (codigo == KeyEvent.VK_W) {
            arribaPresionado = true;
        }
        if (codigo == KeyEvent.VK_S) {
            abajoPresionado = true;
        }
        if (codigo == KeyEvent.VK_A) {
            izquierdaPresionado = true;
        }
        if (codigo == KeyEvent.VK_D) {
            derechaPresionado = true;
        }
    }

    /**
     * Este método se invoca cuando se suelta una tecla. Se utiliza para cambiar el estado de las variables de dirección.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode(); // Número de tecla liberada

        // Cambiar el estado de las variables de dirección en función de la tecla liberada
        if (codigo == KeyEvent.VK_W) {
            arribaPresionado = false;
        }
        if (codigo == KeyEvent.VK_S) {
            abajoPresionado = false;
        }
        if (codigo == KeyEvent.VK_A) {
            izquierdaPresionado = false;
        }
        if (codigo == KeyEvent.VK_D) {
            derechaPresionado = false;
        }
    }
}
