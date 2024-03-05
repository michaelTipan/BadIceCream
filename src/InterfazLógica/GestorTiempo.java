package InterfazLógica;

import java.text.DecimalFormat;

/**
 * La clase GestorTiempo gestiona el tiempo de juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GestorTiempo {
    private double tiempoDeJuego;
    private boolean enPausa;
    private DecimalFormat formatoDecimal;

    /**
     * Crea un nuevo GestorTiempo con el tiempo de juego inicializado a 0 y sin pausa.
     */
    public GestorTiempo() {
        tiempoDeJuego = 0;
        enPausa = false;
        formatoDecimal = new DecimalFormat("#0.00");
    }

    /**
     * Actualiza el tiempo de juego si el juego no está en pausa.
     */
    public void actualizarTiempo() {
        if (!enPausa) {
            tiempoDeJuego += (double) 1 / 60;
        }
    }

    /**
     * Obtiene el tiempo de juego formateado como una cadena de caracteres.
     * @return Tiempo de juego formateado.
     */
    public String getTiempoFormateado() {
        return formatoDecimal.format(tiempoDeJuego);
    }

    /**
     * Obtiene el tiempo de juego en segundos.
     * @return Tiempo de juego en segundos.
     */
    public double getTiempo() {
        return tiempoDeJuego;
    }

    /**
     * Establece el tiempo de juego a un valor específico.
     * @param tiempo El tiempo de juego a establecer.
     */
    public void setTiempo(double tiempo) {
        this.tiempoDeJuego = tiempo;
    }

    /**
     * Comprueba si el juego está en pausa.
     * @return true si el juego está en pausa, false de lo contrario.
     */
    public boolean enPausa() {
        return enPausa;
    }

    /**
     * Establece el estado de pausa del juego.
     * @param enPausa true para poner el juego en pausa, false para reanudarlo.
     */
    public void setEnPausa(boolean enPausa) {
        this.enPausa = enPausa;
    }

    /**
     * Reinicia el tiempo de juego, estableciéndolo a 0.
     */
    public void reiniciarTiempo() {
        tiempoDeJuego = 0;
    }

}



