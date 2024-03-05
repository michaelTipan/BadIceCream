package InterfazLógica;

import InterfazGráfica.Entidad;
import InterfazGráfica.PanelJuego;

/**
 * La clase ComprobadorColisiones se encarga de verificar las colisiones entre entidades y objetos en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class ComprobadorColisiones {

    PanelJuego pj;

    /**
     * Constructor de la clase ComprobadorColisiones.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public ComprobadorColisiones(PanelJuego pj) {
        this.pj = pj;
    }

    /**
     * Comprueba si una entidad colisiona con los tiles del mapa.
     *
     * @param entidad La entidad cuya colisión se va a comprobar.
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

        switch (entidad.direccion) {
            case "arriba":
                filaArribaEntidad = (entidadArribaMundoY - entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaIzquierdaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaDerechaEntidad][filaArribaEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            case "abajo":
                filaAbajoEntidad = (entidadAbajoMundoY + entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaIzquierdaEntidad][filaAbajoEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaDerechaEntidad][filaAbajoEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            case "izquierda":
                columnaIzquierdaEntidad = (entidadIzquierdaMundoX - entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaIzquierdaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaIzquierdaEntidad][filaAbajoEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            case "derecha":
                columnaDerechaEntidad = (entidadDerechaMundoX + entidad.velocidad) / pj.tamañoTile;
                numeroTile1 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaDerechaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[pj.mapaActual][columnaDerechaEntidad][filaAbajoEntidad];
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
        }
    }

    /**
     * Comprueba si una entidad colisiona con objetos en el juego.
     *
     * @param entidad  La entidad cuya colisión se va a comprobar.
     * @param jugador  true si la entidad es el jugador, false si es otro tipo de entidad.
     * @return El índice del objeto con el que colisiona, o 999 si no hay colisión con objetos.
     */
    public int comprobarObjeto(Entidad entidad, boolean jugador) {

        int index = 999;

        for (int i = 0; i < pj.objetos.size(); i++) {

            if (pj.objetos.get(i) != null) {

                entidad.areaSolida.x = entidad.mundoX + entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.mundoY + entidad.areaSolidaPredetermindaY;

                pj.objetos.get(i).areaSolida.x = pj.objetos.get(i).mundoX;
                pj.objetos.get(i).areaSolida.y = pj.objetos.get(i).mundoY;

                switch (entidad.direccion) {
                    case "arriba":
                        entidad.areaSolida.y -= entidad.velocidad;
                        break;
                    case "abajo":
                        entidad.areaSolida.y += entidad.velocidad;
                        break;
                    case "izquierda":
                        entidad.areaSolida.x -= entidad.velocidad;
                        break;
                    case "derecha":
                        entidad.areaSolida.x += entidad.velocidad;
                        break;
                }
                if (entidad.areaSolida.intersects(pj.objetos.get(i).areaSolida)) {
                    if (pj.objetos.get(i).colision == true) {
                        entidad.colisionActivada = true;
                    }
                    if (jugador == true) {
                        index = i;
                    }
                }
                entidad.areaSolida.x = entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.areaSolidaPredetermindaY;
                pj.objetos.get(i).areaSolida.x = pj.objetos.get(i).areaSolidaPredeterminadaX;
                pj.objetos.get(i).areaSolida.y = pj.objetos.get(i).getAreaSolidaPredeterminadaY;
            }
        }
        return index;
    }

    /**
     * Comprueba si una entidad colisiona con monstruos en el juego.
     *
     * @param entidad La entidad cuya colisión se va a comprobar.
     */
    public void comprobarObjetoMounstro(Entidad entidad) {

        for (int i = 0; i < pj.objetos.size(); i++) {

            if (pj.objetos.get(i) != null) {

                entidad.areaSolida.x = entidad.mundoX + entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.mundoY + entidad.areaSolidaPredetermindaY;

                pj.objetos.get(i).areaSolida.x = pj.objetos.get(i).mundoX;
                pj.objetos.get(i).areaSolida.y = pj.objetos.get(i).mundoY;

                switch (entidad.direccion) {
                    case "arriba":
                        entidad.areaSolida.y -= entidad.velocidad;
                        break;
                    case "abajo":
                        entidad.areaSolida.y += entidad.velocidad;
                        break;
                    case "izquierda":
                        entidad.areaSolida.x -= entidad.velocidad;
                        break;
                    case "derecha":
                        entidad.areaSolida.x += entidad.velocidad;
                        break;
                }
                if (entidad.areaSolida.intersects(pj.objetos.get(i).areaSolida)) {
                    if (pj.objetos.get(i).colision == true) {
                        entidad.colisionActivada = true;
                    }
                }
            }
            entidad.areaSolida.x = entidad.areaSolidaPredetermindaX;
            entidad.areaSolida.y = entidad.areaSolidaPredetermindaY;
            pj.objetos.get(i).areaSolida.x = pj.objetos.get(i).areaSolidaPredeterminadaX;
            pj.objetos.get(i).areaSolida.y = pj.objetos.get(i).getAreaSolidaPredeterminadaY;
        }
    }

    /**
     * Comprueba si una entidad colisiona con otras entidades en el juego.
     *
     * @param entidad   La entidad cuya colisión se va a comprobar.
     * @param objetivo  La matriz de entidades objetivo con las que se va a comprobar la colisión.
     * @return El índice de la entidad con la que colisiona, o 999 si no hay colisión con otras entidades.
     */
    public int comprobarEntidad(Entidad entidad, Entidad[][] objetivo) {
        int index = 999;

        for (int i = 0; i < objetivo[1].length; i++) {

            if (objetivo[pj.mapaActual][i] != null) {

                entidad.areaSolida.x = entidad.mundoX + entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.mundoY + entidad.areaSolidaPredetermindaY;

                objetivo[pj.mapaActual][i].areaSolida.x = objetivo[pj.mapaActual][i].mundoX + objetivo[pj.mapaActual][i].areaSolida.x;
                objetivo[pj.mapaActual][i].areaSolida.y = objetivo[pj.mapaActual][i].mundoY + objetivo[pj.mapaActual][i].areaSolida.y;

                switch (entidad.direccion) {
                    case "arriba":
                        entidad.areaSolida.y -= entidad.velocidad;
                        break;
                    case "abajo":
                        entidad.areaSolida.y += entidad.velocidad;
                        break;
                    case "izquierda":
                        entidad.areaSolida.x -= entidad.velocidad;
                        break;
                    case "derecha":
                        entidad.areaSolida.x += entidad.velocidad;
                        break;
                }

                if (entidad.areaSolida.intersects(objetivo[pj.mapaActual][i].areaSolida)) {
                    if (objetivo[pj.mapaActual][i] != entidad) {
                        entidad.colisionActivada = true;
                        index = i;
                    }
                }
                entidad.areaSolida.x = entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.areaSolidaPredetermindaY;
                objetivo[pj.mapaActual][i].areaSolida.x = objetivo[pj.mapaActual][i].areaSolidaPredetermindaX;
                objetivo[pj.mapaActual][i].areaSolida.y = objetivo[pj.mapaActual][i].areaSolidaPredetermindaY;
            }
        }
        return index;
    }

}