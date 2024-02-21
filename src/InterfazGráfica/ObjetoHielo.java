package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoHielo extiende a la clase ObjetoJuego y representa un objeto de tipo Hielo en el juego.
 */
public class ObjetoHielo extends ObjetoJuego{

    /**
     * Constructor de la clase ObjetoHielo.
     * Inicializa el nombre del objeto y carga su imagen correspondiente.
     */
    public ObjetoHielo(){
        nombre = "Hielo"; // Nombre del objeto
        try {
            // Cargar la imagen del objeto desde el archivo correspondiente
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Ice.png"));

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar la imagen
            e.printStackTrace();
        }
    }
}
