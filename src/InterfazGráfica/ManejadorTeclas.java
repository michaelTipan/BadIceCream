package InterfazGráfica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La clase ManejadorTeclas gestiona los eventos de teclado en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class ManejadorTeclas implements KeyListener {

    public boolean arribaPresionado, abajoPresionado, izquierdaPresionado, derechaPresionado;
    PanelJuego pj;

    /**
     * Constructor de la clase ManejadorTeclas.
     * @param pj Referencia al panel de juego.
     */
    public ManejadorTeclas(PanelJuego pj) {
        this.pj = pj;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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
                }
                if (pj.interfazUsuario.numeroComando == 1) {
                    pj.estadoJuego = pj.estadoJugar;
                    pj.cargarGuardar.cargar();
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
            if (codigo == KeyEvent.VK_SPACE) pj.jugador.construirHielo();
            if (codigo == KeyEvent.VK_E) pj.jugador.destruirHielo();

        } else if (pj.estadoJuego == pj.estadoPausa) {
            if (codigo == KeyEvent.VK_P) pj.estadoJuego = pj.estadoJugar;
        } else if (pj.estadoJuego == pj.estadoGameOver) {
            estadoGameOver(codigo);
        }

    }

    /**
     * Gestiona las teclas cuando el juego está en el estado de game over.
     * @param codigo Número de tecla presionada.
     */
    private void estadoGameOver(int codigo) {
        if (codigo == KeyEvent.VK_ENTER) {
            if (pj.interfazUsuario.numeroComando == 0) {
                pj.estadoJuego = pj.estadoJugar;
                pj.reiniciarJuego(false);
            } else if (pj.interfazUsuario.numeroComando == 1) {
                pj.estadoJuego = pj.estadoMenu; // Salir del juego
                pj.reiniciarJuego(true);
            }
        }
        if (codigo == KeyEvent.VK_W) {
            pj.interfazUsuario.numeroComando--;
            if (pj.interfazUsuario.numeroComando < 0) pj.interfazUsuario.numeroComando = 1;
        }
        if (codigo == KeyEvent.VK_S) {
            pj.interfazUsuario.numeroComando++;
            if (pj.interfazUsuario.numeroComando > 1) pj.interfazUsuario.numeroComando = 0;
        }
        pj.interfazUsuario.dibujarPantallaGameOver();
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
