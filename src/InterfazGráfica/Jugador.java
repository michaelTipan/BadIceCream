package InterfazGráfica;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase Jugador representa al personaje controlado por el jugador en el juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class Jugador extends Entidad {

    ManejadorTeclas mt;

    public final int pantallaX;
    public final int pantallaY;
    public int vidaMaxima;
    public int vida;
    public boolean invencible = false;
    public int contadorDeInvencibilidad = 0;
    private int ultimaPosicionX;
    private int ultimaPosicionY;

    /**
     * Constructor de la clase Jugador.
     * @param pj Referencia al panel de juego.
     * @param mt Manejador de teclas.
     */
    public Jugador(PanelJuego pj, ManejadorTeclas mt) {
        super(pj);
        this.mt = mt;

        pantallaX = pj.anchoPantalla / 2 - (pj.tamañoTile / 2); // Centrar jugador en la pantalla (x)
        pantallaY = pj.altoPantalla / 2 - (pj.tamañoTile / 2); // Centrar jugador en la pantalla (y)

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaPredetermindaX = areaSolida.x;
        areaSolidaPredetermindaY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;

        establecerValoresPredeterminados();
        obtenerImagenJugador();
    }

    /**
     * Método para obtener las imágenes del jugador.
     */
    public void obtenerImagenJugador() {

        arriba1 = configuararImagen("/player/boy_up_1");
        arriba2 = configuararImagen("/player/boy_up_2");
        abajo1 = configuararImagen("/player/boy_down_1");
        abajo2 = configuararImagen("/player/boy_down_2");
        izquierda1 = configuararImagen("/player/boy_left_1");
        izquierda2 = configuararImagen("/player/boy_left_2");
        derecha1 = configuararImagen("/player/boy_right_1");
        derecha2 = configuararImagen("/player/boy_right_2");
    }

    /**
     * Método para establecer los valores predeterminados del jugador.
     */
    public void establecerValoresPredeterminados() {
        if (pj.mapaActual == 1 || pj.mapaActual == 2) {
            mundoX = pj.tamañoTile * 8;
            mundoY = pj.tamañoTile * 8;
        } else {
            mundoX = pj.tamañoTile * 8;
            mundoY = pj.tamañoTile * 12;
        }

        velocidad = 4;
        direccion = "abajo"; // Dirección inicial del jugador (arriba)

        // Condiciones iniciales
        vidaMaxima = 2;
        vida = vidaMaxima;
    }


    /**
     * Recoge el objeto en la posición especificada en la lista de objetos del panel de juego.
     * Este método se activa cuando el jugador interactúa con un objeto en el juego.
     * Si el índice de objeto es válido (diferente de 999), se verifica el tipo de objeto y se realiza la acción correspondiente.
     * Si el objeto es una "Banana", se elimina de la lista de objetos y se ajustan las variables relacionadas con las bananas en el juego.
     * Si la cantidad de bananas llega a cero, se activa un evento para que aparezcan las uvas en el juego.
     * Si el objeto es una "Uva" visible, se elimina de la lista de objetos y se ajustan las variables relacionadas con las uvas en el juego.
     * Además, se suman puntos al puntaje del jugador en función del tipo de objeto recogido.
     *
     * @param i Índice del objeto en la lista de objetos del panel de juego.
     */
    public void recogerObjetos(int i) {

        if (i != 999) {

            String nombreObjeto = pj.objetos.get(i).nombre;

            switch (nombreObjeto) {
                case "Banana":
                    pj.objetos.remove(i);
                    pj.gestorActivos.cantidadBananas--; // Decrementar la cantidad de bananas en el juego
                    pj.gestorPuntuacion.sumarPuntos(50);
                    if (pj.gestorActivos.cantidadBananas == 0) {
                        pj.gestorEventos.hacerQueAparezcanLasUvas();
                    }
                    break;

                case "Uva":
                    if (pj.objetos.get(i).visible) {
                        pj.objetos.remove(i);
                        pj.gestorActivos.cantidadUvas--; // Decrementar la cantidad de uvas en el juego
                        pj.gestorPuntuacion.sumarPuntos(100);
                    }
                    break;
            }
        }
    }

    /**
     * Método para actualizar el estado del jugador.
     */
    public void actualizar() {

        if (mt.arribaPresionado || mt.abajoPresionado || mt.izquierdaPresionado || mt.derechaPresionado) {

            if (mt.arribaPresionado) {
                direccion = "arriba";
            } else if (mt.abajoPresionado) {
                direccion = "abajo";
            } else if (mt.izquierdaPresionado) {
                direccion = "izquierda";
            } else if (mt.derechaPresionado) {
                direccion = "derecha";
            }

            // comprobar colision con tile
            colisionActivada = false;
            pj.comprobadorColisiones.comprobarTile(this);

            // comprobar colision con objetos
            int objetoIndex = pj.comprobadorColisiones.comprobarObjeto(this, true);
            recogerObjetos(objetoIndex);

            int enemigoIndex = pj.comprobadorColisiones.comprobarEntidad(this, pj.enemigos);
            contactoConEnemigo(enemigoIndex);

            if (pj.gestorActivos.cantidadUvas == 0) {
                pj.gestorEventos.comprobarEvento();
            }

            pj.gestorEventos.comprobarVictoria();


            if (colisionActivada == false) {
                switch (direccion) {
                    case "arriba":
                        mundoY -= velocidad;
                        break;
                    case "abajo":
                        mundoY += velocidad;
                        break;
                    case "izquierda":
                        mundoX -= velocidad;
                        break;
                    case "derecha":
                        mundoX += velocidad;
                        break;
                }

                contadorSprite++;
                if (contadorSprite > 12) {
                    if (numSprite == 1) {
                        numSprite = 2;
                    } else if (numSprite == 2) {
                        numSprite = 1;
                    }
                    contadorSprite = 0;
                }
            }
        }

        if (invencible) {
            contadorDeInvencibilidad++;
            if (contadorDeInvencibilidad > 120) {
                invencible = false;
                contadorDeInvencibilidad = 0;
            }
        }
        ultimaPosicionX = mundoX;
        ultimaPosicionY = mundoY;

    }

    /**
     * Método para gestionar el contacto del jugador con enemigos.
     * @param enemigoIndex Índice del enemigo en la lista de enemigos.
     */
    private void contactoConEnemigo(int enemigoIndex) {
        if (enemigoIndex != 999) {
            if (invencible == false) {
                vida--;
                invencible = true;
            }
        }
        if (vida <= 0) {
            pj.estadoJuego = pj.estadoGameOver;
        }
    }

    /**
     * Método para dibujar al jugador en pantalla.
     * @param g2 Objeto Graphics2D para dibujar.
     */
    public void dibujar(Graphics2D g2) {

        BufferedImage imagen = null;
        switch (direccion) {
            case "arriba":
                if (numSprite == 1) imagen = arriba1;
                if (numSprite == 2) imagen = arriba2;
                break;
            case "abajo":
                if (numSprite == 1) imagen = abajo1;
                if (numSprite == 2) imagen = abajo2;
                break;
            case "izquierda":
                if (numSprite == 1) imagen = izquierda1;
                if (numSprite == 2) imagen = izquierda2;
                break;
            case "derecha":
                if (numSprite == 1) imagen = derecha1;
                if (numSprite == 2) imagen = derecha2;
                break;
        }
        if (invencible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    /**
     * Construye un bloque de hielo en la dirección del jugador si no hay otro bloque de hielo en esa posición.
     * El método calcula las coordenadas del tile en función de la dirección del jugador y verifica si hay un bloque de hielo en esas coordenadas.
     * Si no hay un bloque de hielo, crea uno nuevo y lo agrega a la lista de objetos del panel de juego.
     */
    public void construirHielo() {
        int tileX = 0;
        int tileY = 0;

        switch (direccion) {
            case "arriba":
                tileX = mundoX / pj.tamañoTile;
                tileY = (mundoY - pj.tamañoTile) / pj.tamañoTile;
                break;
            case "abajo":
                tileX = mundoX / pj.tamañoTile;
                tileY = (mundoY + pj.tamañoTile) / pj.tamañoTile;
                break;
            case "izquierda":
                tileX = (mundoX - pj.tamañoTile) / pj.tamañoTile;
                tileY = mundoY / pj.tamañoTile;
                break;
            case "derecha":
                tileX = (mundoX + pj.tamañoTile) / pj.tamañoTile;
                tileY = mundoY / pj.tamañoTile;
                break;
        }

        // Verificar si ya hay un hielo en las coordenadas especificadas
        if (!pj.gestorActivos.hayHielo(tileX, tileY)) {
            ObjetoHielo hielo = new ObjetoHielo(pj);
            hielo.mundoX = tileX * pj.tamañoTile;
            hielo.mundoY = tileY * pj.tamañoTile;
            pj.objetos.add(hielo);
        }
    }

    /**
     * Destruye un bloque de hielo en la dirección del jugador si existe uno en esa posición.
     * El método calcula las coordenadas del tile en función de la dirección del jugador y verifica si hay un bloque de hielo en esas coordenadas.
     * Si hay un bloque de hielo, lo elimina de la lista de objetos del panel de juego.
     */
    public void destruirHielo() {
        int tileX = 0;
        int tileY = 0;

        switch (direccion) {
            case "arriba":
                tileX = mundoX / pj.tamañoTile;
                tileY = (mundoY - pj.tamañoTile) / pj.tamañoTile;
                break;
            case "abajo":
                tileX = mundoX / pj.tamañoTile;
                tileY = (mundoY + pj.tamañoTile) / pj.tamañoTile;
                break;
            case "izquierda":
                tileX = (mundoX - pj.tamañoTile) / pj.tamañoTile;
                tileY = mundoY / pj.tamañoTile;
                break;
            case "derecha":
                tileX = (mundoX + pj.tamañoTile) / pj.tamañoTile;
                tileY = mundoY / pj.tamañoTile;
                break;
        }
        for (ObjetoJuego objeto : pj.objetos) {
            if (objeto instanceof ObjetoHielo) {
                if (objeto.mundoX == tileX * pj.tamañoTile && objeto.mundoY == tileY * pj.tamañoTile) {
                    pj.objetos.remove(objeto); // Eliminar el hielo si coincide con las coordenadas
                    break; // Salir del bucle una vez que se ha eliminado el hielo
                }
            }
        }
    }
}
