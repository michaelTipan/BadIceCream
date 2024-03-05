package InterfazGráfica;

import java.awt.*;
import java.util.Random;

/**
 * La clase EnemigoBasico representa a un enemigo básico en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class EnemigoBasico extends Enemigo{

    /**
     * Constructor de la clase EnemigoBasico.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public EnemigoBasico(PanelJuego pj) {
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
     * Configura las imágenes para las diferentes direcciones del enemigo.
     */
    public void obtenerImagenEnemigoBasico(){
        arriba1 = configuararImagen("/monster/enemigo1_up_1");
        arriba2 = configuararImagen("/monster/enemigo1_up_2");
        abajo1 = configuararImagen("/monster/enemigo1_down_1");
        abajo2 = configuararImagen("/monster/enemigo1_down_2");
        izquierda1 = configuararImagen("/monster/enemigo1_left_1");
        izquierda2 = configuararImagen("/monster/enemigo1_left_2");
        derecha1 = configuararImagen("/monster/enemigo1_right_1");
        derecha2 = configuararImagen("/monster/enemigo1_right_2");
    }

    /**
     * Define la acción que realizará el enemigo básico.
     */
    public void darAccion() {
        contardeAccciones++;

        if(contardeAccciones == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick a number between 1 and 100

            if (i <= 25) direccion = "arriba";
            if (i > 25 && i <= 50) direccion = "abajo";
            if (i > 50 && i <= 75) direccion = "izquierda";
            if (i > 75 && i <= 100) direccion = "derecha";

            contardeAccciones = 0;
        }
    }
}
