package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoBanana extiende a la clase ObjetoJuego y representa un objeto de tipo Banana en el juego.
 */
public class ObjetoBanana extends ObjetoJuego{

    /**
     * Constructor de la clase ObjetoBanana.
     * Inicializa el nombre del objeto y carga su imagen correspondiente.
     */
    public ObjetoBanana(){
        nombre = "Banana"; // Nombre del objeto
        try {
            // Cargar la imagen del objeto desde el archivo correspondiente
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Banana.png"));

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar la imagen
            e.printStackTrace();
        }
    }

}
