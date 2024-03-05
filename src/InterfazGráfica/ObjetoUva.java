package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoUva extiende a la clase ObjetoJuego y representa un objeto de tipo Uva en el juego.
 * Esta versión del objeto Uva también tiene una referencia a un objeto PanelJuego.
 */
public class ObjetoUva extends ObjetoJuego{
    // Referencia a un objeto PanelJuego
    PanelJuego pj;

    /**
     * Constructor de la clase ObjetoUva.
     * Inicializa el nombre del objeto, carga su imagen correspondiente, escala la imagen al tamaño del tile del panel de juego,
     * y establece la visibilidad del objeto en falso.
     * @param pj PanelJuego que se asocia con este objeto Uva.
     */
    public ObjetoUva(PanelJuego pj){

        // Asignar el PanelJuego proporcionado a la variable de instancia pj
        this.pj = pj;

        // Nombre del objeto
        nombre = "Uva";

        try {
            // Cargar la imagen del objeto desde el archivo correspondiente
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Grape.png"));

            // Escalar la imagen al tamaño del tile del panel de juego
            gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar o escalar la imagen
            e.printStackTrace();
        }

        // Establecer la visibilidad del objeto en falso
        visible = false;
    }
}
