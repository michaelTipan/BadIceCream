package InterfazGráfica;

/**
 * La clase ComprobadorColisiones se encarga de verificar las colisiones en el juego.
 */
public class ComprobadorColisiones {

    PanelJuego pj; // Instancia de PanelJuego para acceder a las propiedades del juego.

    /**
     * Constructor de la clase ComprobadorColisiones.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public ComprobadorColisiones(PanelJuego pj) {
        this.pj = pj;
    }

    /**
     * Comprueba si una entidad está colisionando con un tile.
     * @param entidad La entidad que se va a comprobar.
     */
    public void comprobarTile(Entidad entidad) {

        int entidadIzquierdaMundoX = entidad.mundoX + entidad.areaSolida.x;
        int entidadDerechaMundoX = entidad.mundoX + entidad.areaSolida.x + entidad.areaSolida.width;
        int entidadArribaMundoY = entidad.mundoY + entidad.areaSolida.y;
        int entidadAbajoMundoY = entidad.mundoY + entidad.areaSolida.y + entidad.areaSolida.height;

        int columnaIzquierdaEntidad = entidadIzquierdaMundoX / pj.tamañoTile;
        int columnaDerechaEntidad = entidadDerechaMundoX / pj.tamañoTile;
        int filaArribaEntidad = entidadArribaMundoY / pj.tamañoTile;
        int filaAbajoEntidad = entidadAbajoMundoY / pj.tamañoTile;

        int numeroTile1, numeroTile2;

        // Comprobar la colisión en función de la dirección de la entidad
        switch (entidad.direccion) {
            case "arriba":
                filaArribaEntidad = (entidadArribaMundoY - entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[columnaIzquierdaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[columnaDerechaEntidad][filaArribaEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            case "abajo":
                filaAbajoEntidad = (entidadAbajoMundoY + entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[columnaIzquierdaEntidad][filaAbajoEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[columnaDerechaEntidad][filaAbajoEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            case "izquierda":
                columnaIzquierdaEntidad = (entidadIzquierdaMundoX - entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[columnaIzquierdaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[columnaIzquierdaEntidad][filaAbajoEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            case "derecha":
                columnaDerechaEntidad = (entidadDerechaMundoX + entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[columnaDerechaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[columnaDerechaEntidad][filaAbajoEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
        }
    }

    /**
     * Comprueba si una entidad está colisionando con un objeto.
     * @param entidad La entidad que se va a comprobar.
     * @param jugador Indica si la entidad es el jugador.
     * @return El índice del objeto con el que la entidad está colisionando, o 999 si no hay colisión.
     */
    public int checkObjeto(Entidad entidad, boolean jugador) {

        int index = 999;
        return index;
    }
}