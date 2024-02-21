package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase Entidad representa una entidad en el juego.
 * Una entidad puede ser un personaje, un NPC, un objeto, etc.
 */
public class Entidad {

    public int mundoX, mundoY; // Posición de la entidad en el mundo del juego.
    public int velocidad; // Velocidad de movimiento de la entidad.
    public BufferedImage arriba1, arriba2, abajo1, abajo2, izquierda1, izquierda2, derecha1, derecha2; // Imágenes de la entidad en diferentes direcciones.
    public String direccion; // Dirección actual de la entidad.
    public int contadorSprite = 0; // Contador para controlar el cambio de sprites.
    public int numSprite = 1; // Número del sprite actual.
    public Rectangle areaSolida; // Área sólida de la entidad para las colisiones.
    public int areaSolidaPredetermindaX, areaSolidaPredetermindaY; // Posición predeterminada del área sólida.
    public boolean colisionActivada = false; // Indicador de si la entidad está colisionando con algo.
}
