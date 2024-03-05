package InterfazGráfica;

import java.awt.Rectangle;

/**
 * La clase Evento representa un área en el juego donde ocurre un evento.
 *  * Extiende la clase Rectangle para aprovechar sus propiedades de manejo de áreas rectangulares.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class Evento extends Rectangle{

    int eventoPredeterminadoX, eventoPredeterminadoY;
    boolean eventoRealizado = false;
}
