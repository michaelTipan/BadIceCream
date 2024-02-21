package InterfazGráfica;

import javax.imageio.ImageIO;

/**
 * La clase ObjetoUva extiende a la clase ObjetoJuego y representa un objeto de tipo Uva en el juego.
 */
public class ObjetoUva extends ObjetoJuego{

    /**
     * Constructor de la clase ObjetoUva.
     * Inicializa el nombre del objeto y carga su imagen correspondiente.
     */
    public ObjetoUva(){
        nombre = "Uva"; // Nombre del objeto
        try {
            // Cargar la imagen del objeto desde el archivo correspondiente
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Grape.png"));

        } catch (Exception e) {
            // Imprimir la traza de la pila en caso de que ocurra una excepción al cargar la imagen
            e.printStackTrace();
        }
    }
}
