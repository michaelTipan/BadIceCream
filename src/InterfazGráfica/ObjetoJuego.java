package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase abstracta ObjetoJuego representa un objeto en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public abstract class ObjetoJuego {

    public BufferedImage imagen, imagen2, imagen3;
    public String nombre;
    public boolean colision = false;
    public boolean visible = true;
    public int mundoX, mundoY;
    public Rectangle areaSolida = new Rectangle(0,0,48,48);
    public int areaSolidaPredeterminadaX = 0;
    public int getAreaSolidaPredeterminadaY = 0;
    GestorImagen gImagen = new GestorImagen();

    /**
     * Dibuja el objeto en el panel de juego.
     * @param g2 Objeto Graphics2D para dibujar.
     * @param pj Referencia al panel de juego.
     */
    public void dibujar(Graphics2D g2, PanelJuego pj) {
        // Verificar si el objeto es visible
        if (visible) {
            int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
            int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

            if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                    mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                    mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                    mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
                g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
            }
        }
    }
}
