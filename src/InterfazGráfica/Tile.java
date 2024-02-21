package InterfazGráfica;

import java.awt.image.BufferedImage;

/**
 * La clase Tile representa un tile (o celda) en el juego.
 * Un tile puede contener una imagen y un indicador de si hay colisión.
 */
public class Tile {

    public BufferedImage imagen; // La imagen del tile
    public boolean colision = false; // Indicador de si el tile es colisionable
}
