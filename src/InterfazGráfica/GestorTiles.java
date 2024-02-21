package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * La clase GestorTiles se encarga de gestionar los tiles del juego.
 */
public class GestorTiles {

    PanelJuego pj;
    Tile[] tile;
    int numTileMapa[][];

    /**
     * Constructor de la clase GestorTiles.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public GestorTiles(PanelJuego pj) {
        this.pj = pj;

        tile = new Tile[10];
        numTileMapa = new int[pj.maxColMundo][pj.maxFilaMundo];

        obtenerImagenesTile();
        cargarMapa("/maps/world01.txt");
    }

    /**
     * Obtiene las imágenes de los tiles a partir de archivos.
     */
    public void obtenerImagenesTile() {
        try {
            // Crear un nuevo tile y asignarle la imagen correspondiente
            tile[0] = new Tile();
            tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/snow.png"));

            // Crear un nuevo tile, asignarle la imagen correspondiente y marcarlo como colisionable
            tile[1] = new Tile();
            tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/block.png"));
            tile[1].colision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el mapa del juego a partir de un archivo de texto.
     * @param filePath Ruta del archivo de texto que contiene la disposición de los tiles.
     */
    public void cargarMapa(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int fila = 0;

            // Leer el archivo línea por línea
            while (col < pj.maxColMundo && fila < pj.maxFilaMundo) {
                String linea = br.readLine();
                // Dividir la línea en números y asignar cada número a la matriz numTileMapa
                while (col < pj.maxColMundo) {
                    String[] numeros = linea.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    numTileMapa[col][fila] = num;
                    col++;
                }
                // Pasar a la siguiente fila cuando se haya leído toda la línea
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
     * Dibuja los tiles en el panel del juego.
     * @param g2 Instancia de Graphics2D para dibujar los tiles.
     */
    public void dibujar(Graphics2D g2) {
        int colMundo = 0;
        int filaMundo = 0;

        // Recorrer todos los tiles en el mundo del juego
        while (colMundo < pj.maxColMundo && filaMundo < pj.maxFilaMundo) {
            int numTile = numTileMapa[colMundo][filaMundo];
            int mundoX = colMundo * pj.tamañoTile;
            int mundoY = filaMundo * pj.tamañoTile;
            int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
            int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

            // Dibujar el tile solo si está dentro de la vista del jugador
            if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                    mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                    mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                    mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
                g2.drawImage(tile[numTile].imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
            }
            colMundo++;
            // Pasar a la siguiente fila cuando se haya recorrido toda la columna
            if (colMundo == pj.maxColMundo) {
                colMundo = 0;
                filaMundo++;
            }
        }
    }
}
