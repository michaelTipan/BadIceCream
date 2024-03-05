package InterfazGráfica;

import java.awt.image.BufferedImage;

/**
 * La clase Tile representa un azulejo (tile) en el juego.
 * Contiene una imagen y una bandera para indicar si hay colisión con el azulejo.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class Tile {

    /**
     * Constructor de la clase Tile.
     * @param imagen La imagen asociada al azulejo.
     * @param colision Indica si el azulejo tiene colisión.
     */
    public BufferedImage imagen;
    public boolean colision = false;
}
