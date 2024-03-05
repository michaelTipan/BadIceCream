package InterfazGráfica;

/**
 * La clase GestorEventos gestiona los eventos dentro del juego.
 * Se encarga de controlar la activación de eventos en el mapa, cambiar de nivel y comprobar la victoria del jugador.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GestorEventos {

    PanelJuego pj;
    Evento evento[][][];
    int eventoPrevioX, eventoPrevioY;
    boolean eventoPosible = true;

    /**
     * Constructor de la clase GestorEventos.
     * Inicializa la matriz de eventos y establece los eventos predeterminados en el mapa.
     *
     * @param pj Referencia al panel de juego
     */
    public GestorEventos(PanelJuego pj) {
        this.pj = pj;

        evento = new Evento[pj.maxMapa][pj.maxColMundo][pj.maxFilaMundo];

        int mapa = 0;
        int columna = 0;
        int fila = 0;

        while (mapa < pj.maxMapa && columna < pj.maxColMundo && fila < pj.maxFilaMundo) {

            evento[mapa][columna][fila] = new Evento();
            evento[mapa][columna][fila].x = 23;
            evento[mapa][columna][fila].y = 23;
            evento[mapa][columna][fila].width = 2;
            evento[mapa][columna][fila].height = 2;
            evento[mapa][columna][fila].eventoPredeterminadoX = evento[mapa][columna][fila].x;
            evento[mapa][columna][fila].eventoPredeterminadoY = evento[mapa][columna][fila].y;
            columna++;
            if (columna == pj.maxColMundo) {
                columna = 0;
                fila++;

                if (fila == pj.maxFilaMundo) {
                    fila = 0;
                    mapa++;
                }
            }
        }
    }

    /**
     * Comprueba si se activa un evento en el juego.
     * Se encarga de cambiar de nivel y cargar los objetos correspondientes.
     */
    public void comprobarEvento() {
        int distanciaX = Math.abs(pj.jugador.mundoX - eventoPrevioX);
        int distanciaY = Math.abs(pj.jugador.mundoY - eventoPrevioY);
        int distancia = Math.max(distanciaX, distanciaY);
        if (distancia > pj.tamañoTile) {
            eventoPosible = true;
        }
        if (eventoPosible == true) {
            if (pulsar(0, 9, 9, "cualquiera") == true) {
                cambiarNivel(1, 8, 8);
                pj.gestorActivos.cargarObjetos("/maps/objects1.txt");
            }
            if (pulsar(1, 9, 9, "cualquiera") == true) {
                cambiarNivel(2, 8, 8);
                pj.gestorActivos.cargarObjetos("/maps/objects2.txt");
            }
        }
    }

    /**
     * Comprueba si se ha pulsado un evento en el juego.
     *
     * @param mapa      Índice del mapa
     * @param columna   Columna del evento
     * @param fila      Fila del evento
     * @param direccion Dirección del jugador
     * @return true si se ha pulsado un evento, false en caso contrario
     */
    public boolean pulsar(int mapa, int columna, int fila, String direccion) {
        boolean pulsado = false;

        if (mapa == pj.mapaActual) {
            pj.jugador.areaSolida.x = pj.jugador.mundoX + pj.jugador.areaSolida.x;
            pj.jugador.areaSolida.y = pj.jugador.mundoY + pj.jugador.areaSolida.y;
            evento[mapa][columna][fila].x = evento[mapa][columna][fila].x + columna * pj.tamañoTile;
            evento[mapa][columna][fila].y = evento[mapa][columna][fila].y + fila * pj.tamañoTile;

            if (pj.jugador.areaSolida.intersects(evento[mapa][columna][fila]) && evento[mapa][columna][fila].eventoRealizado == false) {
                if (pj.jugador.direccion.contentEquals(direccion) || direccion.contentEquals("cualquiera")) {
                    pulsado = true;
                    eventoPrevioX = pj.jugador.mundoX;
                    eventoPrevioY = pj.jugador.mundoY;
                }
            }
            pj.jugador.areaSolida.x = pj.jugador.areaSolidaPredetermindaX;
            pj.jugador.areaSolida.y = pj.jugador.areaSolidaPredetermindaY;
            evento[mapa][columna][fila].x = evento[mapa][columna][fila].eventoPredeterminadoX;
            evento[mapa][columna][fila].y = evento[mapa][columna][fila].eventoPredeterminadoY;
        }
        return pulsado;
    }

    /**
     * Hace que aparezcan las uvas en el juego.
     * Recorre la lista de objetos y establece las uvas como visibles.
     */
    public void hacerQueAparezcanLasUvas() {
        // Recorrer la lista de objetos y encontrar las uvas
        for (ObjetoJuego objeto : pj.objetos) {
            if (objeto instanceof ObjetoUva) {
                objeto.visible = true; // Hacer que las uvas sean visibles
            }
        }
    }

    /**
     * Cambia al siguiente nivel del juego.
     * @param mapa Índice del nuevo mapa
     * @param columna Columna donde aparecerá el jugador
     * @param fila Fila donde aparecerá el jugador
     */
    public void cambiarNivel(int mapa, int columna, int fila) {
        pj.objetos.clear();
        pj.mapaActual = mapa;
        pj.jugador.mundoX = columna * pj.tamañoTile;
        pj.jugador.mundoY = fila * pj.tamañoTile;
        eventoPrevioX = pj.jugador.mundoX;
        eventoPrevioY = pj.jugador.mundoY;
        eventoPosible = false;
        pj.cargarGuardar.guardar();
    }

    /**
     * Comprueba si el jugador ha ganado el juego.
     * Si el jugador ha recogido todas las uvas en el último mapa, se marca el juego como finalizado.
     */
    public void comprobarVictoria() {
        if(pj.gestorActivos.cantidadUvas == 0 && pj.mapaActual == 2) {
            pj.interfazUsuario.juegoFinalizado = true;
        }
    }
}
