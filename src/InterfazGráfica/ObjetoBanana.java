package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase GestorActivos se encarga de gestionar la carga de objetos del juego
 * en el mundo del juego a partir de un archivo de texto.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class ObjetoBanana extends ObjetoJuego{

    PanelJuego pj;
    public ObjetoBanana(PanelJuego pj){
        this.pj = pj;
        nombre = "Banana";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Banana.png"));
            gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
