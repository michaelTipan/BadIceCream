package InterfazGráfica;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * La clase GestorImagen proporciona métodos para el manejo y escalado de imágenes.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GestorImagen {

    /**
     * Escala una imagen al tamaño especificado.
     * @param original La imagen original que se va a escalar.
     * @param ancho El ancho deseado de la imagen escalada.
     * @param alto El alto deseado de la imagen escalada.
     * @return La imagen escalada al tamaño especificado.
     */
    public BufferedImage escalarImagen(BufferedImage original, int ancho, int alto) {

        BufferedImage imagenAEscala = new BufferedImage(ancho, alto, original.getType());
        Graphics2D g2 = imagenAEscala.createGraphics();
        g2.drawImage(original, 0, 0, ancho, alto, null);
        g2.dispose();

        return imagenAEscala;
    }
}
