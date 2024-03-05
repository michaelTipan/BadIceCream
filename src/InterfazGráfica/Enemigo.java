package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase Enemigo es una clase abstracta que define el comportamiento básico de los enemigos en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public abstract class Enemigo extends Entidad{

    public String nombre;
    public int contardeAccciones = 0;

    /**
     * Constructor de la clase Enemigo.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public Enemigo(PanelJuego pj) {
        super(pj);
    }

    /**
     * Dibuja el enemigo en la pantalla.
     *
     * @param g2 El contexto gráfico 2D utilizado para dibujar.
     */
    public void dibujar(Graphics2D g2) {

        BufferedImage imagen = null;
        int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
        int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

        if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {

            switch (direccion) {
                case "arriba":
                    if (numSprite == 1) imagen = arriba1;
                    if (numSprite == 2) imagen = arriba2;
                    break;
                case "abajo":
                    if (numSprite == 1) imagen = abajo1;
                    if (numSprite == 2) imagen = abajo2;
                    break;
                case "izquierda":
                    if (numSprite == 1) imagen = izquierda1;
                    if (numSprite == 2) imagen = izquierda2;
                    break;
                case "derecha":
                    if (numSprite == 1) imagen = derecha1;
                    if (numSprite == 2) imagen = derecha2;
                    break;
            }
            g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
        }
    }

    /**
     * Actualiza la posición y las acciones del enemigo.
     */
    public void actualizar(){
        darAccion();

        colisionActivada = false;
        pj.comprobadorColisiones.comprobarTile(this);
        pj.comprobadorColisiones.comprobarObjetoMounstro(this);
        if (colisionActivada == false) {
            switch (direccion) {
                case "arriba":
                    mundoY -= velocidad;
                    break;
                case "abajo":
                    mundoY += velocidad;
                    break;
                case "izquierda":
                    mundoX -= velocidad;
                    break;
                case "derecha":
                    mundoX += velocidad;
                    break;
            }

            contadorSprite++;
            if (contadorSprite > 12) {
                if (numSprite == 1) {
                    numSprite = 2;
                } else if (numSprite == 2) {
                    numSprite = 1;
                }
                contadorSprite = 0;
            }
        }
    }

    /**
     * Método abstracto que define la acción que realizará el enemigo.
     */
    public abstract void darAccion();


}
