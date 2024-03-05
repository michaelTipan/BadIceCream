package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase ObjetoJuego representa un objeto genérico en el juego.
 */
public class ObjetoJuego {

    // Imágenes asociadas con el objeto
    public BufferedImage imagen, imagen2, imagen3;

    // Nombre del objeto
    public String nombre;

    // Indica si el objeto puede colisionar
    public boolean colision = false;

    // Indica si el objeto es visible
    public boolean visible = true;

    // Coordenadas del objeto en el mundo del juego
    public int mundoX, mundoY;

    // Área sólida del objeto
    public Rectangle areaSolida = new Rectangle(0,0,48,48);

    // Coordenadas predeterminadas del área sólida
    public int areaSolidaPredeterminadaX = 0;
    public int getAreaSolidaPredeterminadaY = 0;

    // Gestor de imágenes
    GestorImagen gImagen = new GestorImagen();

    /**
     * Método para dibujar el objeto en el panel de juego.
     * @param g2 Objeto Graphics2D para dibujar el objeto.
     * @param pj PanelJuego en el que se dibuja el objeto.
     */
    public void dibujar(Graphics2D g2, PanelJuego pj) {
        // Verificar si el objeto es visible
        if (visible) {
            // Calcular las coordenadas de la pantalla
            int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
            int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

            // Verificar si el objeto está dentro del campo de visión del jugador
            if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                    mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                    mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                    mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
                // Dibujar la imagen del objeto
                g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
            }
        }
    }
}
