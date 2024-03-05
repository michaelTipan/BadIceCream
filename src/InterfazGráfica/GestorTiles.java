package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * La clase GestorTiles se encarga de gestionar los tiles del juego.
 * Cada tile es una imagen que se utiliza para dibujar el mundo del juego.
 */
public class GestorTiles {

    // Instancia de PanelJuego para acceder a las propiedades del juego.
    PanelJuego pj;
    // Array de tiles.
    Tile[] tile;
    // Mapa de tiles representado como un array bidimensional de números enteros.
    int numTileMapa[][];

    /**
     * Constructor de la clase GestorTiles.
     * Inicializa las variables de instancia y carga los tiles y el mapa del juego.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public GestorTiles(PanelJuego pj) {
        this.pj = pj;

        // Inicializar el array de tiles y el mapa de tiles.
        tile = new Tile[10];
        numTileMapa = new int[pj.maxColMundo][pj.maxFilaMundo];

        // Obtener las imágenes de los tiles y cargar el mapa del juego.
        obtenerImagenesTile();
        cargarMapa("/maps/world01.txt");
    }

    /**
     * Obtiene las imágenes de los tiles a partir de archivos.
     * Configura los tiles con las imágenes y las propiedades de colisión correspondientes.
     */
    public void obtenerImagenesTile() {

        // Configurar los tiles con las imágenes y las propiedades de colisión.
        configurar(0, "snow", false);
        configurar(1, "block", true);
    }

    /**
     * Configura un tile con una imagen y una propiedad de colisión.
     * @param indice El índice del tile en el array.
     * @param nombreImagen El nombre del archivo de la imagen del tile.
     * @param colision Si el tile es colisionable o no.
     */
    public void configurar(int indice, String nombreImagen, boolean colision) {

        // Crear una nueva instancia de GestorImagen para escalar la imagen del tile.
        GestorImagen gImagen = new GestorImagen();

        try {
            // Crear un nuevo tile y configurarlo con la imagen y la propiedad de colisión.
            tile[indice] = new Tile();
            tile[indice].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/" + nombreImagen + ".png"));
            tile[indice].imagen = gImagen.escalarImagen(tile[indice].imagen, pj.tamañoTile, pj.tamañoTile);
            tile[indice].colision = colision;

        } catch (IOException e) {
            // Imprimir la traza de la pila si ocurre una excepción al leer la imagen del tile.
            e.printStackTrace();
        }
    }

    /**
     * Carga el mapa del juego a partir de un archivo de texto.
     * El archivo de texto contiene la disposición de los tiles en el mundo del juego.
     * @param filePath Ruta del archivo de texto que contiene la disposición de los tiles.
     */
    public void cargarMapa(String filePath) {
        try {
            // Abrir el archivo de texto para lectura.
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int fila = 0;

            // Leer cada línea del archivo y cargar los tiles en el mapa de tiles.
            while (col < pj.maxColMundo && fila < pj.maxFilaMundo) {
                String linea = br.readLine();
                while (col < pj.maxColMundo) {
                    String[] numeros = linea.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    numTileMapa[col][fila] = num;
                    col++;
                }
                if (col == pj.maxColMundo) {
                    col = 0;
                    fila++;
                }
            }
            // Cerrar el archivo de texto.
            br.close();
        } catch (Exception e) {
            // Imprimir la traza de la pila si ocurre una excepción al cargar el mapa del juego.
            e.printStackTrace();
        }
    }

    /**
     * Dibuja los tiles en el panel del juego.
     * Solo dibuja los tiles que están en la vista del jugador.
     * @param g2 Instancia de Graphics2D para dibujar los tiles.
     */
    public void dibujar(Graphics2D g2) {
        int colMundo = 0;
        int filaMundo = 0;

        // Recorrer cada tile en el mapa de tiles y dibujarlo en el panel del juego.
        while (colMundo < pj.maxColMundo && filaMundo < pj.maxFilaMundo) {
            int numTile = numTileMapa[colMundo][filaMundo];
            int mundoX = colMundo * pj.tamañoTile;
            int mundoY = filaMundo * pj.tamañoTile;
            int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
            int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

            // Solo dibujar los tiles que están en la vista del jugador.
            if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                    mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                    mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                    mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
                g2.drawImage(tile[numTile].imagen, pantallaX, pantallaY,null);
            }
            colMundo++;
            if (colMundo == pj.maxColMundo) {
                colMundo = 0;
                filaMundo++;
            }
        }
    }
}
