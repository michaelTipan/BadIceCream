package InterfazGráfica;

import java.awt.*;
import java.util.Random;

/**
 * La clase EnemigoFinal representa al enemigo final del juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class EnemigoMediano extends Enemigo{

    /**
     * Constructor de la clase EnemigoMediano.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public EnemigoMediano(PanelJuego pj) {
        super(pj);
        tipo = 2;
        nombre = "EnemigoBasico";
        velocidad = 1;

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaPredetermindaX = areaSolida.x;
        areaSolidaPredetermindaY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;
        obtenerImagenEnemigoBasico();
    }

    /**
     * Configura las imágenes para las diferentes direcciones del enemigo mediano.
     */
    public void obtenerImagenEnemigoBasico(){
        arriba1 = configuararImagen("/monster/enemigo5_up_1");
        arriba2 = configuararImagen("/monster/enemigo5_up_2");
        abajo1 = configuararImagen("/monster/enemigo5_down_1");
        abajo2 = configuararImagen("/monster/enemigo5_down_2");
        izquierda1 = configuararImagen("/monster/enemigo5_left_1");
        izquierda2 = configuararImagen("/monster/enemigo5_left_2");
        derecha1 = configuararImagen("/monster/enemigo5_right_1");
        derecha2 = configuararImagen("/monster/enemigo5_right_2");
    }

    /**
     * Define la acción que realizará el enemigo mediano.
     */
    public void darAccion() {
        contardeAccciones++;

        // Sigue al jugador durante 60 actualizaciones
        if (contardeAccciones <= 80) {
            // Calcular la dirección hacia el jugador
            if (pj.jugador.mundoX < mundoX) {
                direccion = "izquierda";
            } else if (pj.jugador.mundoX > mundoX) {
                direccion = "derecha";
            }
            if (pj.jugador.mundoY < mundoY) {
                direccion = "arriba";
            } else if (pj.jugador.mundoY > mundoY) {
                direccion = "abajo";
            }
        } else {
            // Después de 60 actualizaciones, el enemigo cambia de comportamiento
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick a number between 1 and 100

            if (i <= 25) direccion = "arriba";
            else if (i > 25 && i <= 50) direccion = "abajo";
            else if (i > 50 && i <= 75) direccion = "izquierda";
            else if (i > 75 && i <= 100) direccion = "derecha";

            contardeAccciones = 0; // Reiniciar el contador de acciones
        }
    }
}
