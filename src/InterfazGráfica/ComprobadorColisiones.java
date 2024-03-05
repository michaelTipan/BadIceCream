package InterfazGráfica;

/**
 * La clase ComprobadorColisiones se encarga de comprobar las colisiones entre las entidades y los tiles del juego.
 */
public class ComprobadorColisiones {

    // Instancia de PanelJuego para acceder a las propiedades del juego.
    PanelJuego pj;

    /**
     * Constructor de la clase ComprobadorColisiones.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public ComprobadorColisiones(PanelJuego pj) {
        this.pj = pj;
    }

    /**
     * Comprueba si una entidad está colisionando con un tile.
     * Si la entidad está colisionando con un tile, se activa la propiedad de colisión de la entidad.
     * @param entidad La entidad para la que se va a comprobar la colisión.
     */
    public void comprobarTile(Entidad entidad) {

        // Calcular las coordenadas de los bordes de la entidad en el mundo del juego.
        int entidadIzquierdaMundoX = entidad.mundoX + entidad.areaSolida.x;
        int entidadDerechaMundoX = entidad.mundoX + entidad.areaSolida.x + entidad.areaSolida.width;
        int entidadArribaMundoY = entidad.mundoY + entidad.areaSolida.y;
        int entidadAbajoMundoY = entidad.mundoY + entidad.areaSolida.y + entidad.areaSolida.height;

        // Calcular las columnas y filas de los tiles que corresponden a los bordes de la entidad.
        int columnaIzquierdaEntidad = entidadIzquierdaMundoX / pj.tamañoTile;
        int columnaDerechaEntidad = entidadDerechaMundoX / pj.tamañoTile;
        int filaArribaEntidad = entidadArribaMundoY / pj.tamañoTile;
        int filaAbajoEntidad = entidadAbajoMundoY / pj.tamañoTile;

        // Variables para almacenar los números de los tiles que corresponden a los bordes de la entidad.
        int numeroTile1, numeroTile2;

        // Comprobar la colisión en función de la dirección de la entidad.
        switch (entidad.direccion) {
            case "arriba":
                // Calcular la fila del tile que corresponde al borde superior de la entidad.
                filaArribaEntidad = (entidadArribaMundoY - entidad.velocidad) / pj.tamañoTile;
                // Obtener los números de los tiles que corresponden a los bordes superior izquierdo y superior derecho de la entidad.
                numeroTile1 = pj.gestorTiles.numTileMapa[columnaIzquierdaEntidad][filaArribaEntidad];
                numeroTile2 = pj.gestorTiles.numTileMapa[columnaDerechaEntidad][filaArribaEntidad];
                // Si alguno de los tiles es colisionable, activar la propiedad de colisión de la entidad.
                if (pj.gestorTiles.tile[numeroTile1].colision == true || pj.gestorTiles.tile[numeroTile2].colision == true) {
                    entidad.colisionActivada = true;
                }
                break;
            // Los casos "abajo", "izquierda" y "derecha" son similares al caso "arriba", pero para los otros bordes de la entidad.
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
     * Si la entidad está colisionando con un objeto, se activa la propiedad de colisión de la entidad.
     * Si el parámetro jugador es verdadero, devuelve el índice del objeto con el que la entidad está colisionando.
     * @param entidad La entidad para la que se va a comprobar la colisión.
     * @param jugador Si la entidad es el jugador.
     * @return El índice del objeto con el que la entidad está colisionando, o 999 si la entidad no está colisionando con ningún objeto.
     */
    public int comprobarObjeto(Entidad entidad, boolean jugador) {

        // Inicializar el índice a un valor que indica que no hay colisión.
        int index = 999;

        // Recorrer cada objeto en la lista de objetos.
        for (int i = 0; i < pj.objetos.size(); i++) {

            // Si el objeto actual no es nulo, comprobar la colisión.
            if (pj.objetos.get(i) != null) {

                // Actualizar las coordenadas del área sólida de la entidad y del objeto.
                entidad.areaSolida.x = entidad.mundoX + entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.mundoY + entidad.areaSolidaPredetermindaY;
                pj.objetos.get(i).areaSolida.x = pj.objetos.get(i).mundoX;
                pj.objetos.get(i).areaSolida.y = pj.objetos.get(i).mundoY;

                // Comprobar la colisión en función de la dirección de la entidad.
                switch (entidad.direccion) {
                    case "arriba":
                        // Calcular la nueva coordenada y del área sólida de la entidad.
                        entidad.areaSolida.y -= entidad.velocidad;
                        // Si el área sólida de la entidad intersecta con el área sólida del objeto, comprobar la colisión.
                        if (entidad.areaSolida.intersects(pj.objetos.get(i).areaSolida)) {
                            // Si el objeto es colisionable, activar la propiedad de colisión de la entidad.
                            if (pj.objetos.get(i).colision == true) {
                                entidad.colisionActivada = true;
                            }
                            // Si la entidad es el jugador, actualizar el índice al índice del objeto.
                            if (jugador == true) {
                                index = i;
                            }
                        }
                        break;
                    // Los casos "abajo", "izquierda" y "derecha" son similares al caso "arriba", pero para las otras direcciones.
                    case "abajo":
                        entidad.areaSolida.y += entidad.velocidad;
                        if (entidad.areaSolida.intersects(pj.objetos.get(i).areaSolida)) {
                            if (pj.objetos.get(i).colision == true) {
                                entidad.colisionActivada = true;
                            }
                            if (jugador == true) {
                                index = i;
                            }
                        }
                        break;
                    case "izquierda":
                        entidad.areaSolida.x -= entidad.velocidad;
                        if (entidad.areaSolida.intersects(pj.objetos.get(i).areaSolida)) {
                            if (pj.objetos.get(i).colision == true) {
                                entidad.colisionActivada = true;
                            }
                            if (jugador == true) {
                                index = i;
                            }
                        }
                        break;
                    case "derecha":
                        entidad.areaSolida.x += entidad.velocidad;
                        if (entidad.areaSolida.intersects(pj.objetos.get(i).areaSolida)) {
                            if (pj.objetos.get(i).colision == true) {
                                entidad.colisionActivada = true;
                            }
                            if (jugador == true) {
                                index = i;
                            }
                        }
                        break;
                }
                // Restaurar las coordenadas del área sólida de la entidad y del objeto a sus valores predeterminados.
                entidad.areaSolida.x = entidad.areaSolidaPredetermindaX;
                entidad.areaSolida.y = entidad.areaSolidaPredetermindaY;
                pj.objetos.get(i).areaSolida.x = pj.objetos.get(i).areaSolidaPredeterminadaX;
                pj.objetos.get(i).areaSolida.y = pj.objetos.get(i).getAreaSolidaPredeterminadaY;
            }
        }
        // Devolver el índice del objeto con el que la entidad está colisionando, o 999 si la entidad no está colisionando con ningún objeto.
        return index;
    }
}