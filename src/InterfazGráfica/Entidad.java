package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase Entidad representa una entidad en el juego.
 * Una entidad puede ser cualquier objeto en el juego que tenga una posición y una velocidad,
 * y puede tener una imagen asociada para representarlo visualmente.
 */
public class Entidad {

    // Coordenadas de la entidad en el mundo del juego.
    public int mundoX, mundoY;
    // Velocidad de la entidad.
    public int velocidad;
    // Imágenes de la entidad en diferentes direcciones.
    public BufferedImage arriba1, arriba2, abajo1, abajo2, izquierda1, izquierda2, derecha1, derecha2;
    // Dirección actual de la entidad.
    public String direccion;
    // Contador para controlar el cambio de sprites en la animación.
    public int contadorSprite = 0;
    // Número de sprite actual en la animación.
    public int numSprite = 1;
    // Área sólida de la entidad para la detección de colisiones.
    public Rectangle areaSolida;
    // Coordenadas predeterminadas del área sólida.
    public int areaSolidaPredetermindaX, areaSolidaPredetermindaY;
    // Indica si la detección de colisiones está activada para esta entidad.
    public boolean colisionActivada = false;
}
