package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoBanana extiende a la clase ObjetoJuego y representa un objeto de tipo Banana en el juego.
 * Esta versión del objeto Banana también tiene una referencia a un objeto PanelJuego.
 */
public class ObjetoBanana extends ObjetoJuego{

    // Referencia a un objeto PanelJuego
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoBanana.
     * Inicializa el nombre del objeto, carga su imagen correspondiente y escala la imagen al tamaño del tile del panel de juego.
     * @param pj PanelJuego que se asocia con este objeto Banana.
     */
    public ObjetoBanana(PanelJuego pj){
        // Asignar el PanelJuego proporcionado a la variable de instancia pj
        this.pj = pj;

        // Nombre del objeto
        nombre = "Banana";

        try {
            // Cargar la imagen del objeto desde el archivo correspondiente
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Banana.png"));

            // Escalar la imagen al tamaño del tile del panel de juego
            gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar o escalar la imagen
            e.printStackTrace();
        }
    }

}
