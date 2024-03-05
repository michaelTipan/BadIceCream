package InterfazGráfica;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * La clase InterfazUsuario se encarga de gestionar la interfaz de usuario del juego.
 * La interfaz de usuario incluye elementos como el menú, los mensajes, la vida del jugador y el tiempo de juego.
 */
public class InterfazUsuario {

    // Instancia de PanelJuego para acceder a las propiedades del juego.
    PanelJuego pj;
    // Objeto Graphics2D para dibujar en la pantalla.
    Graphics2D g2;
    // Fuentes para dibujar texto en la pantalla.
    Font arial_40, arial_80B;
    // Imágenes para representar la vida del jugador y los objetos recogidos.
    BufferedImage imagenBanana, imagenUva, corazon_Completo, corazon_Lastimado, corazon_Vacio;
    // Indica si se está mostrando un mensaje en la pantalla.
    public boolean mensajeActivado = false;
    // El mensaje que se está mostrando en la pantalla.
    public String mensaje = "";
    // Indica si el juego ha finalizado.
    public boolean juegoFinalizado = false;
    // Número de comando actual en el menú.
    public int numeroComando = 0;
    // Tiempo de juego en segundos.
    double tiempoDeJuego = 0;
    // Indica si el juego está en pausa.
    public boolean enPausa = false;
    // Formato para mostrar el tiempo de juego con dos decimales.
    DecimalFormat formatoDecimal = new DecimalFormat("#0.00");

    /**
     * Constructor de la clase InterfazUsuario.
     * Inicializa las variables de instancia y carga las imágenes de los objetos y la vida del jugador.
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public InterfazUsuario(PanelJuego pj) {
        this.pj = pj;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        ObjetoBanana banana = new ObjetoBanana(pj);
        ObjetoUva uva = new ObjetoUva(pj);
        imagenBanana = banana.imagen; // Obtener la imagen de la banana
        imagenUva = uva.imagen; // Obtener la imagen de la uva

        ObjetoCorazon corazon = new ObjetoCorazon(pj);
        corazon_Completo = corazon.imagen;
        corazon_Lastimado = corazon.imagen2;
        corazon_Vacio = corazon.imagen3;
    }

    /**
     * Muestra un mensaje en la pantalla.
     * @param texto El texto del mensaje.
     */
    public void mostrarMensaje(String texto) {
        mensaje = texto;
        mensajeActivado = true;
    }

    /**
     * Dibuja la interfaz de usuario en la pantalla.
     * La interfaz de usuario incluye el menú, los mensajes, la vida del jugador y el tiempo de juego.
     * @param g2 Objeto Graphics2D para dibujar en la pantalla.
     */
    public void dibujar(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.red);

        if (pj.estadoJuego == pj.estadoMenu) {
            dibujarPantallaMenu();
        }

        if (pj.estadoJuego == pj.estadoJugar) {

            mostrarVidaJugador();

            if (!enPausa) {
                tiempoDeJuego += (double) 1 / 60;
                g2.drawString("Tiempo: " + formatoDecimal.format(tiempoDeJuego) + " s", pj.tamañoTile * 9, 86);
            }

            g2.setFont(arial_40);
            g2.setColor(Color.pink);
            g2.drawImage(imagenBanana, pj.tamañoTile / 2, pj.tamañoTile, pj.tamañoTile, pj.tamañoTile, null); // Dibujar la imagen de la banana en la interfaz usuario
            g2.drawString("x " + pj.gestorActivos.cantidadBananas, 74, 86);

            if (pj.gestorActivos.cantidadBananas != 0) {
                g2.drawImage(imagenBanana, pj.tamañoTile / 2, pj.tamañoTile, pj.tamañoTile, pj.tamañoTile, null);
                g2.drawString("x " + pj.gestorActivos.cantidadBananas, 74, 86);
            } else {

                // Dibuja la imagen de la uva y muestra la cantidad de uvas en su lugar
                g2.drawImage(imagenUva, pj.tamañoTile * 4, pj.tamañoTile, pj.tamañoTile, pj.tamañoTile, null);
                g2.drawString("x " + pj.gestorActivos.cantidadUvas, 250, 86);
            }
            enPausa = false;
        }

        if (pj.estadoJuego == pj.estadoPausa) {
            dibujarPantallaPausa();
            mostrarVidaJugador();
            enPausa = true;
        }

        if (juegoFinalizado) {

            g2.setFont(arial_80B);
            g2.setColor(Color.red);

            String texto;
            int x;
            int y;

            texto = "Nivel Finalizado!!!";
            x = obtenerXparaTextoCentrado(texto);
            y = pj.altoPantalla / 2 + (pj.tamañoTile * 2);
            g2.drawString(texto, x, y);

            g2.setFont(arial_40);

            texto = "Tu tiempo fue: " + formatoDecimal.format(tiempoDeJuego) + "!";
            x = obtenerXparaTextoCentrado(texto);
            y = pj.altoPantalla / 2 + (pj.tamañoTile * 4);
            g2.drawString(texto, x, y);
            pj.hiloJuego = null;
        }
    }

    /**
     * Muestra la vida del jugador en la interfaz de usuario.
     * Dibuja corazones vacíos para la vida máxima del jugador y corazones llenos para la vida actual del jugador.
     */
    private void mostrarVidaJugador() {
        pj.jugador.vida = 1;
        int x = pj.tamañoTile * 9;
        int y = pj.tamañoTile * 2;
        int i = 0;

        // Dibujar corazones vacíos para la vida máxima del jugador.
        while (i < pj.jugador.vidaMaxima / 2) {
            g2.drawImage(corazon_Vacio, x, y, null);
            i++;
            x += pj.tamañoTile;
        }
        x = pj.tamañoTile * 9;
        y = pj.tamañoTile * 2;
        i = 0;

        // Dibujar corazones llenos para la vida actual del jugador.
        while (i < pj.jugador.vida) {
            g2.drawImage(corazon_Lastimado, x, y, null);
            i++;
            if (i < pj.jugador.vida) g2.drawImage(corazon_Completo, x, y, null);
            i++;
            x += pj.tamañoTile;
        }
    }

    /**
     * Dibuja la pantalla del menú.
     * Muestra el título del juego, una imagen del jugador y las opciones del menú.
     */
    private void dibujarPantallaMenu() {
        // Dibujar el fondo.
        g2.setColor(new Color(64, 229, 223));
        g2.fillRect(0, 0, pj.anchoPantalla, pj.altoPantalla);

        // Dibujar el título del juego.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String texto = "Bad Ice Cream";
        int x = obtenerXparaTextoCentrado(texto);
        int y = pj.tamañoTile * 3;

        // Dibujar la sombra del título.
        g2.setColor(Color.black);
        g2.drawString(texto, x + 5, y + 5);
        // Dibujar el título.
        g2.setColor(Color.white);
        g2.drawString(texto, x, y);

        // Dibujar una imagen del jugador.
        x = pj.anchoPantalla / 2 - (pj.tamañoTile * 2) / 2;
        y += pj.tamañoTile * 2;
        g2.drawImage(pj.jugador.abajo1, x, y, pj.tamañoTile * 2, pj.tamañoTile * 2, null);

        // Dibujar las opciones del menú.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        texto = "Nueva Partida";
        x = obtenerXparaTextoCentrado(texto);
        y += pj.tamañoTile * 3.5;
        g2.drawString(texto, x, y);
        if (numeroComando == 0) g2.drawString(">", x - pj.tamañoTile, y);

        texto = "Cargar Partida";
        x = obtenerXparaTextoCentrado(texto);
        y += pj.tamañoTile;
        g2.drawString(texto, x, y);
        if (numeroComando == 1) g2.drawString(">", x - pj.tamañoTile, y);

        texto = "Salir";
        x = obtenerXparaTextoCentrado(texto);
        y += pj.tamañoTile;
        g2.drawString(texto, x, y);
        if (numeroComando == 2) g2.drawString(">", x - pj.tamañoTile, y);
    }

    /**
     * Dibuja la pantalla de pausa.
     * Muestra el texto "PAUSA" en el centro de la pantalla.
     */
    public void dibujarPantallaPausa() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String texto = "PAUSA";
        int x = obtenerXparaTextoCentrado(texto);
        int y = pj.altoPantalla / 2;

        g2.drawString(texto, x, y);
    }

    /**
     * Obtiene la posición x para centrar un texto en la pantalla.
     * @param texto El texto que se va a centrar.
     * @return La posición x para centrar el texto en la pantalla.
     */
    public int obtenerXparaTextoCentrado(String texto) {
        int longitudTexto = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = pj.anchoPantalla / 2 - longitudTexto / 2;
        return x;
    }
}
