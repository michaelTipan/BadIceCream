package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoCorazon extiende a la clase ObjetoJuego y representa un objeto de tipo Corazon en el juego.
 * Esta versión del objeto Corazon también tiene una referencia a un objeto PanelJuego.
 */
public class ObjetoCorazon extends ObjetoJuego{

    // Referencia a un objeto PanelJuego
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoCorazon.
     * Inicializa el nombre del objeto, carga sus imágenes correspondientes y escala las imágenes al tamaño del tile del panel de juego.
     * @param pj PanelJuego que se asocia con este objeto Corazon.
     */
    public ObjetoCorazon(PanelJuego pj){

        // Asignar el PanelJuego proporcionado a la variable de instancia pj
        this.pj = pj;

        // Nombre del objeto
        nombre = "Corazon";

        try {
            // Cargar las imágenes del objeto desde los archivos correspondientes
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));

            // Escalar las imágenes al tamaño del tile del panel de juego
            imagen = gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);
            imagen2 = gImagen.escalarImagen(imagen2, pj.tamañoTile, pj.tamañoTile);
            imagen3 = gImagen.escalarImagen(imagen3, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar o escalar las imágenes
            e.printStackTrace();
        }
    }
}
