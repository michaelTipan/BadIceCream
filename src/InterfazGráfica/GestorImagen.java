package InterfazGráfica;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * La clase GestorImagen se encarga de gestionar las operaciones de imagen.
 */
public class GestorImagen {

    /**
     * Escala una imagen a un nuevo tamaño.
     * Crea una nueva imagen con el tamaño especificado y dibuja la imagen original en ella.
     * @param original La imagen original que se va a escalar.
     * @param ancho El ancho de la imagen escalada.
     * @param alto El alto de la imagen escalada.
     * @return La imagen escalada.
     */
    public BufferedImage escalarImagen(BufferedImage original, int ancho, int alto) {

        // Crear una nueva imagen con el tamaño especificado.
        BufferedImage imagenAEscala = new BufferedImage(ancho, alto, original.getType());

        // Crear un objeto Graphics2D para dibujar en la imagen.
        Graphics2D g2 = imagenAEscala.createGraphics();

        // Dibujar la imagen original en la nueva imagen.
        g2.drawImage(original, 0, 0, ancho, alto, null);

        // Liberar los recursos del objeto Graphics2D.
        g2.dispose();

        // Devolver la imagen escalada.
        return imagenAEscala;
    }
}
