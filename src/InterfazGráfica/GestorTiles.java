package InterfazGráfica;

import InterfazGráfica.GestorImagen;
import InterfazGráfica.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * La clase GestorTiles gestiona los tiles del mapa del juego.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GestorTiles {

    PanelJuego pj;
    public Tile[] tile;
    public int[][][] numTileMapa;

    /**
     * Crea un nuevo GestorTiles para el panel de juego dado.
     * @param pj El panel de juego al que pertenece el gestor de tiles.
     */
    public GestorTiles(PanelJuego pj) {
        this.pj = pj;

        tile = new Tile[10];
        numTileMapa = new int[pj.maxMapa][pj.maxColMundo][pj.maxFilaMundo];

        obtenerImagenesTile();
        cargarMapa("/maps/world01.txt", 0);
        cargarMapa("/maps/world02.txt", 1);
        cargarMapa("/maps/world03.txt", 2);
    }

    /**
     * Obtiene las imágenes de los diferentes tipos de tiles.
     */
    public void obtenerImagenesTile() {

        configurar(0, "snow", false);
        configurar(1, "block", true);
        configurar(2, "igloo", false);
    }

    /**
     * Configura un tipo de tile con su respectiva imagen y si tiene colisión.
     * @param indice El índice del tile.
     * @param nombreImagen El nombre del archivo de imagen del tile.
     * @param colision Indica si el tile tiene colisión.
     */
    public void configurar(int indice, String nombreImagen, boolean colision) {

        GestorImagen gImagen = new GestorImagen();

        try {
            tile[indice] = new Tile();
            tile[indice].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/" + nombreImagen + ".png"));
            tile[indice].imagen = gImagen.escalarImagen(tile[indice].imagen, pj.tamañoTile, pj.tamañoTile);
            tile[indice].colision = colision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el mapa de tiles desde un archivo de texto.
     * @param filePath La ruta del archivo de texto que contiene el mapa de tiles.
     * @param mapa El índice del mapa en el que se cargará el archivo.
     */
    public void cargarMapa(String filePath, int mapa) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int fila = 0;

            while (col < pj.maxColMundo && fila < pj.maxFilaMundo) {
                String linea = br.readLine();
                while (col < pj.maxColMundo) {
                    String[] numeros = linea.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    numTileMapa[mapa][col][fila] = num;
                    col++;
                }
                if (col == pj.maxColMundo) {
                    col = 0;
                    fila++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dibuja los tiles en el panel de juego.
     * @param g2 El contexto gráfico en el que se dibujarán los tiles.
     */
    public void dibujar(Graphics2D g2) {
        int colMundo = 0;
        int filaMundo = 0;

        while (colMundo < pj.maxColMundo && filaMundo < pj.maxFilaMundo) {
            int numTile = numTileMapa[pj.mapaActual][colMundo][filaMundo];
            int mundoX = colMundo * pj.tamañoTile;
            int mundoY = filaMundo * pj.tamañoTile;
            int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
            int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

            if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                    mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                    mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                    mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
                g2.drawImage(tile[numTile].imagen, pantallaX, pantallaY, null);
            }
            colMundo++;
            if (colMundo == pj.maxColMundo) {
                colMundo = 0;
                filaMundo++;
            }
        }
    }
}