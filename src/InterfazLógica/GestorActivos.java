package InterfazLógica;

import InterfazGráfica.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * La clase GestorActivos se encarga de gestionar la carga de objetos del juego
 * en el mundo del juego a partir de un archivo de texto.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class GestorActivos {
    PanelJuego pj;
    ArrayList<ObjetoJuego> objetos;

    public int cantidadBananas = 0; // Inicializar la cantidad de bananas en 0
    public int cantidadUvas = 0; // Inicializar la cantidad de uvas en 0

    /**
     * Constructor de la clase GestorActivos.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public GestorActivos(PanelJuego pj) {
        this.pj = pj;
        objetos = new ArrayList<>();
    }

    /**
     * Carga los objetos del juego en el mundo del juego a partir de un archivo de texto.
     *
     * @param filePath Ruta del archivo de texto que contiene la disposición de los objetos del juego.
     */
    public void cargarObjetos(String filePath) {
        try {
            // Abrir el archivo de texto para lectura
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;
            int fila = 0;

            // Leer cada línea del archivo
            while ((linea = br.readLine()) != null && fila < pj.maxFilaMundo) {
                // Recorrer cada carácter de la línea
                for (int col = 0; col < linea.length() && col < pj.maxColMundo; col++) {
                    char c = linea.charAt(col);
                    // Determinar qué tipo de objeto crear según el carácter en el archivo
                    switch (c) {
                        case '1':
                            // Hielo
                            ObjetoHielo hielo = new ObjetoHielo(pj);
                            hielo.mundoX = col * pj.tamañoTile;
                            hielo.mundoY = fila * pj.tamañoTile;
                            pj.objetos.add(hielo);
                            break;
                        case '2':
                            // Banana
                            ObjetoBanana banana = new ObjetoBanana(pj);
                            banana.mundoX = col * pj.tamañoTile;
                            banana.mundoY = fila * pj.tamañoTile;
                            pj.objetos.add(banana);
                            cantidadBananas++; // Incrementar la cantidad de bananas en el juego
                            break;
                        case '3':
                            // Uva
                            ObjetoUva uva = new ObjetoUva(pj);
                            uva.mundoX = col * pj.tamañoTile;
                            uva.mundoY = fila * pj.tamañoTile;
                            pj.objetos.add(uva);
                            cantidadUvas++; // Incrementar la cantidad de uvas en el juego
                            break;
                        default:
                            // No hacer nada si el carácter no corresponde a ningún objeto
                            break;
                    }
                }
                fila++;
            }

            // Cerrar el archivo de texto
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece la cantidad de bananas en el juego.
     *
     * @param cantidadBananas La cantidad de bananas a establecer.
     */
    public void setCantidadBananas(int cantidadBananas) {
        this.cantidadBananas = cantidadBananas;
    }

    /**
     * Establece la cantidad de uvas en el juego.
     *
     * @param cantidadUvas La cantidad de uvas a establecer.
     */
    public void setCantidadUvas(int cantidadUvas) {
        this.cantidadUvas = cantidadUvas;
    }

    /**
     * Carga los enemigos en el juego.
     */
    public void cargarEnemigos() {
        int numeroMapa = 0;
        int i = 0;

        pj.enemigos[numeroMapa][i] = new EnemigoBasico(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 3;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 4;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoBasico(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 3;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 12;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoBasico(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 15;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 4;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoBasico(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 15;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 15;
        i++;

        numeroMapa = 1;
        pj.enemigos[numeroMapa][i] = new EnemigoBasico(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 10;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 3;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoBasico(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 14;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 14;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoMediano(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 3;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 3;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoMediano(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 3;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 14;
        i++;

        numeroMapa = 2;

        pj.enemigos[numeroMapa][i] = new EnemigoMediano(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 12;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 7;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoMediano(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 5;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 10;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoFinal(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 5;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 4;
        i++;

        pj.enemigos[numeroMapa][i] = new EnemigoFinal(pj);
        pj.enemigos[numeroMapa][i].mundoX = pj.tamañoTile * 14;
        pj.enemigos[numeroMapa][i].mundoY = pj.tamañoTile * 14;

    }

    /**
     * Verifica si hay hielo en una posición determinada del mapa.
     *
     * @param tileX Coordenada X del tile en el mapa.
     * @param tileY Coordenada Y del tile en el mapa.
     * @return true si hay hielo en la posición especificada, de lo contrario false.
     */
    public boolean hayHielo(int tileX, int tileY) {
        for (ObjetoJuego objeto : objetos) {
            if (objeto instanceof ObjetoHielo) {
                if (objeto.mundoX == tileX * pj.tamañoTile && objeto.mundoY == tileY * pj.tamañoTile) {
                    return true;
                }
            }
        }
        return false;
    }
}