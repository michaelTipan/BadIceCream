package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoHielo extiende a la clase ObjetoJuego y representa un objeto de tipo Hielo en el juego.
 * Esta versión del objeto Hielo también tiene una referencia a un objeto PanelJuego.
 */
public class ObjetoHielo extends ObjetoJuego {

    // Referencia a un objeto PanelJuego
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoHielo.
     * Inicializa el nombre del objeto, carga su imagen correspondiente, escala la imagen al tamaño del tile del panel de juego,
     * y establece la propiedad de colisión en verdadero.
     * @param pj PanelJuego que se asocia con este objeto Hielo.
     */
    public ObjetoHielo(PanelJuego pj){

        // Asignar el PanelJuego proporcionado a la variable de instancia pj
        this.pj = pj;

        // Nombre del objeto
        nombre = "Hielo";

        try {
            // Cargar la imagen del objeto desde el archivo correspondiente
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Ice.png"));

            // Escalar la imagen al tamaño del tile del panel de juego
            gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar o escalar la imagen
            e.printStackTrace();
        }

        // Establecer la propiedad de colisión en verdadero
        colision = true;
    }
}
