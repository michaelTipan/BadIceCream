package InterfazGráfica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManejadorTeclas implements KeyListener {

    public boolean arribaPresionado, abajoPresionado, izquierdaPresionado, derechaPresionado;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode(); // Número de tecla presionada

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

    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode(); // Número de tecla liberada

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
