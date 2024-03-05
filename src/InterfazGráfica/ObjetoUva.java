package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoUva representa una uva en el juego.
 * Extiende la clase ObjetoJuego.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class ObjetoUva extends ObjetoJuego{
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoUva.
     * @param pj Referencia al panel de juego.
     */
    public ObjetoUva(PanelJuego pj){

        this.pj = pj;
        nombre = "Uva";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Grape.png"));
            gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        visible = false;
    }
}
