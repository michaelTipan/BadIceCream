package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entidad {

    public int mundoX, mundoY;
    public int velocidad;
    public BufferedImage arriba1, arriba2, abajo1, abajo2, izquierda1, izquierda2, derecha1, derecha2;
    public String direccion;
    public int contadorSprite = 0;
    public int numSprite = 1;
    public Rectangle areaSolida;
    public int areaSolidaPredetermindaX, areaSolidaPredetermindaY;
    public boolean colisionActivada = false;
}
