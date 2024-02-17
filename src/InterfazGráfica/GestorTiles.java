package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GestorTiles {

    PanelJuego pj;
    Tile[] tile;
    int numTileMapa[][];

    public GestorTiles(PanelJuego pj) {
        this.pj = pj;

        tile = new Tile[10];
        numTileMapa = new int[pj.maxColMundo][pj.maxFilaMundo];

        obtenerImagenesTile();
        cargarMapa("/maps/world01.txt");
    }

    public void obtenerImagenesTile() {
        try {
            tile[0] = new Tile();
            tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/snow.png"));

            tile[1] = new Tile();
            tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/block.png"));
            tile[1].colision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarMapa(String filePath) {
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
                    numTileMapa[col][fila] = num;
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

    public void dibujar(Graphics2D g2) {
        int colMundo = 0;
        int filaMundo = 0;

        while (colMundo < pj.maxColMundo && filaMundo < pj.maxFilaMundo) {
            int numTile = numTileMapa[colMundo][filaMundo];
            int mundoX = colMundo * pj.tamañoTile;
            int mundoY = filaMundo * pj.tamañoTile;
            int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
            int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY;

            if (mundoX + pj.tamañoTile > pj.jugador.mundoX - pj.jugador.pantallaX &&
                    mundoX - pj.tamañoTile < pj.jugador.mundoX + pj.jugador.pantallaX &&
                    mundoY + pj.tamañoTile > pj.jugador.mundoY - pj.jugador.pantallaY &&
                    mundoY - pj.tamañoTile < pj.jugador.mundoY + pj.jugador.pantallaY) {
                g2.drawImage(tile[numTile].imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
            }
            colMundo++;
            if (colMundo == pj.maxColMundo) {
                colMundo = 0;
                filaMundo++;
            }
        }
    }
}