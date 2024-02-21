package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * La clase Jugador extiende a la clase Entidad y se encarga de gestionar las acciones del jugador en el juego.
 */
public class Jugador extends Entidad {

    PanelJuego pj;
    ManejadorTeclas mt;

    public final int pantallaX;
    public final int pantallaY;

    /**
     * Constructor de la clase Jugador.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     * @param mt Instancia de ManejadorTeclas para gestionar las entradas del teclado.
     */
    public Jugador(PanelJuego pj, ManejadorTeclas mt) {
        this.pj = pj;
        this.mt = mt;

        pantallaX = pj.anchoPantalla / 2 - (pj.tamañoTile / 2); // Centrar jugador en la pantalla (x)
        pantallaY = pj.altoPantalla / 2 - (pj.tamañoTile / 2); // Centrar jugador en la pantalla (y)

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaPredetermindaX = areaSolida.x;
        areaSolidaPredetermindaY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;

        establecerValoresPredeterminados();
        obtenerImagenJugador();
    }

    /**
     * Establece los valores predeterminados para el jugador.
     */
    public void establecerValoresPredeterminados() {
        mundoX = pj.tamañoTile * 8;
        mundoY = pj.tamañoTile * 12;
        velocidad = 4;
        direccion = "abajo"; // Dirección inicial del jugador (arriba)
    }

    /**
     * Obtiene las imágenes del jugador a partir de archivos.
     */
    public void obtenerImagenJugador() {
        try {
            arriba1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            arriba2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            izquierda1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            derecha1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el estado del jugador basado en las entradas del teclado y las colisiones.
     */
    public void actualizar() {
        // Comprobar si alguna tecla está presionada
        if (mt.arribaPresionado == true || mt.abajoPresionado == true || mt.izquierdaPresionado == true || mt.derechaPresionado == true) {

            // Cambiar la dirección del jugador en función de la tecla presionada
            if (mt.arribaPresionado == true) {
                direccion = "arriba";
            } else if (mt.abajoPresionado == true) {
                direccion = "abajo";
            } else if (mt.izquierdaPresionado == true) {
                direccion = "izquierda";
            } else if (mt.derechaPresionado == true) {
                direccion = "derecha";
            }
        }

        colisionActivada = false;
        pj.comprobadorColisiones.comprobarTile(this);

        // Mover al jugador si no hay colisión
        if (colisionActivada == false) {
            switch (direccion) {
                case "arriba":
                    mundoY -= velocidad;
                    break;
                case "abajo":
                    mundoY += velocidad;
                    break;
                case "izquierda":
                    mundoX -= velocidad;
                    break;
                case "derecha":
                    mundoX += velocidad;
                    break;
            }

            // Cambiar el sprite del jugador para la animación
            contadorSprite++;
            if (contadorSprite > 12) {
                if (numSprite == 1) {
                    numSprite = 2;
                } else if (numSprite == 2) {
                    numSprite = 1;
                }
                contadorSprite = 0;
            }
        }
    }

    /**
     * Dibuja al jugador en el panel del juego.
     * @param g2 Instancia de Graphics2D para dibujar al jugador.
     */
    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
        // Seleccionar la imagen del jugador en función de la dirección y el sprite actual
        switch (direccion) {
            case "arriba":
                if (numSprite == 1) imagen = arriba1;
                if (numSprite == 2) imagen = arriba2;
                break;
            case "abajo":
                if (numSprite == 1) imagen = abajo1;
                if (numSprite == 2) imagen = abajo2;
                break;
            case "izquierda":
                if (numSprite == 1) imagen = izquierda1;
                if (numSprite == 2) imagen = izquierda2;
                break;
            case "derecha":
                if (numSprite == 1) imagen = derecha1;
                if (numSprite == 2) imagen = derecha2;
                break;
        }
        // Dibujar la imagen seleccionada en la posición del jugador en la pantalla
        g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
    }
}
