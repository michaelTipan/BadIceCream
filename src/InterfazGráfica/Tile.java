package InterfazGráfica;

import java.awt.image.BufferedImage;

/**
 * La clase Tile representa un tile (o celda) en un mapa de juego.
 */
public class Tile {

    // Imagen asociada con el tile
    public BufferedImage imagen;

    // Indica si el tile puede colisionar
    public boolean colision = false;
}
