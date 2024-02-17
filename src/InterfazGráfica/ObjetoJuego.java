package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjetoJuego {

    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;
    public int mundoX, mundoY;
    public Rectangle areaSolida = new Rectangle(0,0,48,48);
    public int areaSolidaPredeterminadaX = 0;
    public int getAreaSolidaPredeterminadaY = 0;

    public void dibujar(Graphics2D g2, PanelJuego pj) {

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
