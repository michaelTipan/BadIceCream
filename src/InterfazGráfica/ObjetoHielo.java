package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoHielo representa un objeto de hielo en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class ObjetoHielo extends ObjetoJuego {
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoHielo.
     * @param pj Referencia al panel de juego.
     */
    public ObjetoHielo(PanelJuego pj){

        this.pj = pj;
        nombre = "Hielo";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Ice.png"));
            gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        colision = true;
    }
}
