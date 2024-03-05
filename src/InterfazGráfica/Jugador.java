package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * La clase Jugador extiende de la clase Entidad y representa al jugador en el juego.
 * El jugador tiene una posición en la pantalla, una vida, una velocidad y una dirección.
 * El jugador también tiene una imagen que se muestra en la pantalla.
 */
public class Jugador extends Entidad {

    // Instancia de PanelJuego para acceder a las propiedades del juego.
    PanelJuego pj;
    // Instancia de ManejadorTeclas para manejar las entradas del teclado.
    ManejadorTeclas mt;

    // Posición del jugador en la pantalla.
    public final int pantallaX;
    public final int pantallaY;
    // Vida máxima y actual del jugador.
    public int vidaMaxima;
    public int vida;

    /**
     * Constructor de la clase Jugador.
     * Inicializa las variables de instancia y carga las imágenes del jugador.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     * @param mt Instancia de ManejadorTeclas para manejar las entradas del teclado.
     */
    public Jugador(PanelJuego pj, ManejadorTeclas mt) {
        this.pj = pj;
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
     * Establece los valores predeterminados para el jugador.
     * Los valores predeterminados incluyen la posición inicial, la velocidad y la dirección del jugador, y la vida máxima y actual.
     */
    public void establecerValoresPredeterminados() {

        mundoX = pj.tamañoTile * 8;
        mundoY = pj.tamañoTile * 12;
        velocidad = 4;
        direccion = "abajo"; // Dirección inicial del jugador (arriba)

        // Condiciones iniciales
        vidaMaxima = 2;
        vida = vidaMaxima;
    }

    /**
     * Obtiene las imágenes del jugador.
     * Las imágenes incluyen las imágenes del jugador mirando hacia arriba, hacia abajo, hacia la izquierda y hacia la derecha.
     */
    public void obtenerImagenJugador() {

        arriba1 = configuararImagen("boy_up_1");
        arriba2 = configuararImagen("boy_up_2");
        abajo1 = configuararImagen("boy_down_1");
        abajo2 = configuararImagen("boy_down_2");
        izquierda1 = configuararImagen("boy_left_1");
        izquierda2 = configuararImagen("boy_left_2");
        derecha1 = configuararImagen("boy_right_1");
        derecha2 = configuararImagen("boy_right_2");
    }

    /**
     * Configura una imagen del jugador.
     * La imagen se escala al tamaño de un tile del juego.
     * @param nombreImagen El nombre de la imagen.
     * @return La imagen configurada.
     */
    public BufferedImage configuararImagen(String nombreImagen) {

        GestorImagen gImagen = new GestorImagen();
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/player/" + nombreImagen + ".png"));
            imagen = gImagen.escalarImagen(imagen, pj.tamañoTile, pj.tamañoTile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }

    /**
     * Actualiza el estado del jugador en cada frame del juego.
     * Este método se encarga de manejar las entradas del teclado, actualizar la dirección del jugador,
     * comprobar las colisiones con los tiles y los objetos, recoger los objetos, y actualizar la posición y el sprite del jugador.
     */
    public void actualizar() {

        // Comprobar si alguna tecla de movimiento está presionada.
        if (mt.arribaPresionado == true || mt.abajoPresionado == true || mt.izquierdaPresionado == true || mt.derechaPresionado == true) {

            // Actualizar la dirección del jugador en función de la tecla de movimiento que está presionada.
            if (mt.arribaPresionado == true) {
                direccion = "arriba";
            } else if (mt.abajoPresionado == true) {
                direccion = "abajo";
            } else if (mt.izquierdaPresionado == true) {
                direccion = "izquierda";
            } else if (mt.derechaPresionado == true) {
                direccion = "derecha";
            }

            // Comprobar la colisión con los tiles.
            colisionActivada = false;
            pj.comprobadorColisiones.comprobarTile(this);

            // Comprobar la colisión con los objetos.
            int objetoIndex = pj.comprobadorColisiones.comprobarObjeto(this, true);
            recogerObjetos(objetoIndex);

            // Si no hay colisión, actualizar la posición del jugador y el sprite.
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

                // Actualizar el sprite del jugador.
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
    }

    /**
     * Recoge los objetos en el juego.
     * Si el jugador colisiona con un objeto, el objeto se elimina y se actualiza la cantidad de objetos en el juego.
     * @param i El índice del objeto con el que el jugador está colisionando.
     */
    public void recogerObjetos(int i) {

        if (i != 999) {

            String nombreObjeto = pj.objetos.get(i).nombre;

            switch (nombreObjeto) {
                case "Banana":
                    pj.objetos.remove(i);
                    pj.gestorActivos.cantidadBananas--; // Decrementar la cantidad de bananas en el juego
                    if (pj.gestorActivos.cantidadBananas == 0) {
                        // Hacer que aparezcan las uvas
                        hacerQueAparezcanLasUvas();
                    }
                    break;

                case "Uva":
                    if (pj.objetos.get(i).visible) {
                        pj.objetos.remove(i);
                        pj.gestorActivos.cantidadUvas--; // Decrementar la cantidad de uvas en el juego
                        if(pj.gestorActivos.cantidadUvas == 0){
                            pj.interfazUsuario.juegoFinalizado = true;
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Hace que aparezcan las uvas en el juego.
     * Recorre la lista de objetos y hace visibles las uvas.
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
     * Dibuja al jugador en la pantalla.
     * El jugador se dibuja en la posición del jugador y con la imagen correspondiente a la dirección y el sprite del jugador.
     * @param g2 Instancia de Graphics2D para dibujar en la pantalla.
     */
    public void dibujar(Graphics2D g2) {

        BufferedImage imagen = null;
        // Seleccionar la imagen del jugador en función de la dirección y el sprite actual
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
        // Dibujar al jugador en la pantalla
        g2.drawImage(imagen, pantallaX, pantallaY, pj.tamañoTile, pj.tamañoTile, null);
    }

}
