package InterfazLógica;;

/**
 * La clase GestorPuntuacion gestiona los puntos en el juego.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GestorPuntuacion {
    private int puntos;

    /**
     * Crea un nuevo GestorPuntuacion con puntos iniciales igual a 0.
     */
    public GestorPuntuacion() {
        puntos = 0;
    }

    /**
     * Suma la cantidad especificada de puntos a la puntuación actual.
     * @param cantidad La cantidad de puntos a sumar.
     */
    public void sumarPuntos(int cantidad) {
        puntos += cantidad;
    }

    /**
     * Reinicia la puntuación, estableciendo los puntos a 0.
     */
    public void reiniciarPuntuacion() {
        puntos = 0;
    }

    /**
     * Obtiene la puntuación actual.
     * @return La puntuación actual.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Establece la puntuación a un valor específico.
     * @param puntos El valor de la puntuación a establecer.
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
