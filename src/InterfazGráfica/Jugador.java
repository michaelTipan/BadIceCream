package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends Entidad {

    PanelJuego pj;
    ManejadorTeclas mt;

    public final int pantallaX;
    public final int pantallaY;

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

    public void establecerValoresPredeterminados() {
        mundoX = pj.tamañoTile * 8;
        mundoY = pj.tamañoTile * 12;
        velocidad = 4;
        direccion = "abajo"; // Dirección inicial del jugador (arriba)
    }

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

    public void actualizar() {
        if (mt.arribaPresionado == true || mt.abajoPresionado == true || mt.izquierdaPresionado == true || mt.derechaPresionado == true) {

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

    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
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
        g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
    }
}
