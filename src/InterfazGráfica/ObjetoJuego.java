package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase ObjetoJuego representa un objeto genérico en el juego.
 */
public class ObjetoJuego {

    public BufferedImage imagen; // Imagen del objeto
    public String nombre; // Nombre del objeto
    public boolean colision = false; // Indica si el objeto es colisionable
    public int mundoX, mundoY; // Posición del objeto en el mundo del juego
    public Rectangle areaSolida = new Rectangle(0,0,48,48); // Área sólida del objeto para las colisiones
    public int areaSolidaPredeterminadaX = 0; // Posición X predeterminada del área sólida
    public int getAreaSolidaPredeterminadaY = 0; // Posición Y predeterminada del área sólida

    /**
     * Dibuja el objeto en el panel del juego.
     * @param g2 Instancia de Graphics2D para dibujar el objeto.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public void dibujar(Graphics2D g2, PanelJuego pj) {

        // Calcular la posición del objeto en la pantalla en función de la posición del jugador
        int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
        int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

        // Dibujar el objeto solo si está dentro de la vista del jugador
        if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
            g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
        }
    }
}
