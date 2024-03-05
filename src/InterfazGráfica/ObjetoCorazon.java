package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoCorazon representa un objeto de corazón en el juego.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class ObjetoCorazon extends ObjetoJuego{
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoCorazon.
     * @param pj Referencia al panel de juego.
     */
    public ObjetoCorazon(PanelJuego pj){

        this.pj = pj;
        nombre = "Corazon";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            imagen = gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);
            imagen2 = gImagen.escalarImagen(imagen2, pj.tamañoTile, pj.tamañoTile);
            imagen3 = gImagen.escalarImagen(imagen3, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
