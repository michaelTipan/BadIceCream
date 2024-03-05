package InterfazGráfica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La clase ManejadorTeclas implementa KeyListener y se encarga de gestionar las entradas del teclado.
 * Las entradas del teclado incluyen las teclas de movimiento (arriba, abajo, izquierda, derecha) y las teclas de acción (enter, p).
 */
public class ManejadorTeclas implements KeyListener {

    // Indica si las teclas de movimiento están presionadas.
    public boolean arribaPresionado, abajoPresionado, izquierdaPresionado, derechaPresionado;
    // Instancia de PanelJuego para acceder a las propiedades del juego.
    PanelJuego pj;

    /**
     * Constructor de la clase ManejadorTeclas.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public ManejadorTeclas(PanelJuego pj) {
        this.pj = pj;
    }

    /**
     * Método vacío que se invoca cuando se escribe una tecla.
     * Este método no se utiliza en este juego.
     * @param e Evento de teclado.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Maneja las entradas del teclado cuando se presiona una tecla.
     * Este método se encarga de actualizar el estado del juego y la dirección del jugador en función de la tecla que se presiona.
     * @param e Evento de teclado.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode(); // Número de tecla presionada

        // Estado Menu
        if (pj.estadoJuego == pj.estadoMenu) {
            if (codigo == KeyEvent.VK_W) {
                pj.interfazUsuario.numeroComando--;
                if (pj.interfazUsuario.numeroComando < 0) pj.interfazUsuario.numeroComando = 2;
            }

            if (codigo == KeyEvent.VK_S) {
                pj.interfazUsuario.numeroComando++;
                if (pj.interfazUsuario.numeroComando > 2) pj.interfazUsuario.numeroComando = 0;
            }
            if (codigo == KeyEvent.VK_ENTER) {
                if (pj.interfazUsuario.numeroComando == 0) {
                    pj.estadoJuego = pj.estadoJugar;
                    //pj.iniciarMusica
                }
                if (pj.interfazUsuario.numeroComando == 1) {
                    //
                }
                if (pj.interfazUsuario.numeroComando == 2) {
                    System.exit(0); // Salir del juego
                }

            }

        }

        // Estado Juego

        if (pj.estadoJuego == pj.estadoJugar) {

            if (codigo == KeyEvent.VK_W) arribaPresionado = true;
            if (codigo == KeyEvent.VK_S) abajoPresionado = true;
            if (codigo == KeyEvent.VK_A) izquierdaPresionado = true;
            if (codigo == KeyEvent.VK_D) derechaPresionado = true;
            if (codigo == KeyEvent.VK_P) pj.estadoJuego = pj.estadoPausa;

        } else if (pj.estadoJuego == pj.estadoPausa) {
            if (codigo == KeyEvent.VK_P) pj.estadoJuego = pj.estadoJugar;
        }
    }

    /**
     * Maneja las entradas del teclado cuando se libera una tecla.
     * Este método se encarga de actualizar el estado de las teclas de movimiento cuando se liberan.
     * @param e Evento de teclado.
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
