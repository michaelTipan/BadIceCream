package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * La clase abstracta Entidad representa entidades en el juego, como jugadores y enemigos.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public abstract class Entidad {

    PanelJuego pj;
    public int mundoX, mundoY;
    public int velocidad;
    public BufferedImage arriba1, arriba2, abajo1, abajo2, izquierda1, izquierda2, derecha1, derecha2;
    public String direccion = "abajo";
    public int contadorSprite = 0;
    public int numSprite = 1;
    public Rectangle areaSolida = new Rectangle(0, 0, 48, 48);
    public int areaSolidaPredetermindaX, areaSolidaPredetermindaY;
    public boolean colisionActivada = false;
    public int tipo;

    /**
     * Constructor de la clase Entidad.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public Entidad(PanelJuego pj) {
        this.pj = pj;
    }

    /**
     * Configura una imagen a partir de su nombre.
     *
     * @param nombreImagen Nombre de la imagen a cargar.
     * @return La imagen cargada.
     */
    public BufferedImage configuararImagen(String nombreImagen) {

        GestorImagen gImagen = new GestorImagen();
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(nombreImagen + ".png"));
            imagen = gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
}
