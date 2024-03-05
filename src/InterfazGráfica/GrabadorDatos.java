package InterfazGráfica;

import java.io.Serializable;

/**
 * La clase GrabadorDatos es una clase serializable que almacena los datos del jugador para guardar el progreso del juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GrabadorDatos implements Serializable {
    //Jugador Estadisticas
    int vida;
    int mapaActual;
    int puntuacion;
    double tiempo;
}
