package InterfazGráfica;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * InterfazUsuario gestiona la interfaz gráfica del usuario en el juego.
 * Administra la representación de la vida del jugador, los objetos recogidos, mensajes en pantalla, etc.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class InterfazUsuario {

    PanelJuego pj;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage imagenBanana, imagenUva, corazon_Completo, corazon_Lastimado, corazon_Vacio;
    public boolean mensajeActivado = false;
    public String mensaje = "";
    public boolean juegoFinalizado = false;
    public int numeroComando = 0;
    public InterfazLógica.GestorTiempo gestorTiempo= new InterfazLógica.GestorTiempo();

    /**
     * Constructor de la clase InterfazUsuario.
     * Inicializa las fuentes y carga las imágenes de los objetos y los corazones.
     *
     * @param pj El panel de juego asociado a la interfaz.
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

    public void dibujar(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.red);

        if (pj.estadoJuego == pj.estadoMenu) {
            dibujarPantallaMenu();
        }

        if (pj.estadoJuego == pj.estadoJugar) {
            mostrarVidaJugador();
            dibujarPantallaJuego(g2);
            gestorTiempo.setEnPausa(false); // Asegurarse de que el tiempo no esté en pausa
        }

        if (pj.estadoJuego == pj.estadoPausa) {
            dibujarPantallaPausa();
            mostrarVidaJugador();
            gestorTiempo.setEnPausa(true);
        }

        if (pj.estadoJuego == pj.estadoGameOver) {
            dibujarPantallaGameOver();
        }

        if (juegoFinalizado) {
            dibujarPantallaFinal(g2);

            pj.hiloJuego = null;
        }
    }

    /**
     * Dibuja la pantalla del final del juego.
     *
     * @param g2 El contexto gráfico en el que se dibujará la pantalla de juego.
     */
    private void dibujarPantallaFinal(Graphics2D g2) {
        g2.setFont(arial_80B);
        g2.setColor(Color.red);

        String texto;
        int x;
        int y;

        texto = "Juego Finalizado!!!";
        x = obtenerXparaTextoCentrado(texto);
        y = pj.altoPantalla / 2 + (pj.tamañoTile * 2);
        g2.drawString(texto, x, y);

        g2.setFont(arial_40);

        texto = "Tu tiempo fue: " + gestorTiempo.getTiempoFormateado();
        x = obtenerXparaTextoCentrado(texto);
        y = pj.altoPantalla / 2 + (pj.tamañoTile * 4);
        g2.drawString(texto, x, y);

        texto = "Puntos: " + pj.gestorPuntuacion.getPuntos();
        x = obtenerXparaTextoCentrado(texto);
        y = pj.altoPantalla / 2 + (pj.tamañoTile * 5);
        g2.drawString(texto, x, y);
    }

    /**
     * Dibuja la pantalla de juego.
     *
     * @param g2 El contexto gráfico en el que se dibujará la pantalla de juego.
     */
    private void dibujarPantallaJuego(Graphics2D g2) {
        if (!gestorTiempo.enPausa()) {
            gestorTiempo.actualizarTiempo();
            g2.drawString("Tiempo: " + gestorTiempo.getTiempoFormateado() + " s", pj.tamañoTile * 9, 86);
        }

        g2.setFont(arial_40);
        g2.setColor(Color.pink);
        g2.drawImage(imagenBanana, pj.tamañoTile / 2, pj.tamañoTile, pj.tamañoTile, pj.tamañoTile, null); // Dibujar la imagen de la banana en la interfaz usuario
        g2.drawString("x " + pj.gestorActivos.cantidadBananas, 74, 86);
        g2.drawString("Puntos: " + pj.gestorPuntuacion.getPuntos(), pj.tamañoTile, 150);

        if (pj.gestorActivos.cantidadBananas != 0) {
            g2.drawImage(imagenBanana, pj.tamañoTile / 2, pj.tamañoTile, pj.tamañoTile, pj.tamañoTile, null);
            g2.drawString("x " + pj.gestorActivos.cantidadBananas, 74, 86);
        } else {
            // Dibuja la imagen de la uva y muestra la cantidad de uvas en su lugar
            g2.drawImage(imagenUva, pj.tamañoTile * 4, pj.tamañoTile, pj.tamañoTile, pj.tamañoTile, null);
            g2.drawString("x " + pj.gestorActivos.cantidadUvas, 250, 86);
        }
    }

    /**
     * Dibuja la pantalla de Game Over.
     */
    public void dibujarPantallaGameOver() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, pj.anchoPantalla, pj.altoPantalla);

        int x;
        int y;
        String texto;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        texto = "Game Over";
        //sombre
        g2.setColor(Color.black);
        x = obtenerXparaTextoCentrado(texto);
        y = pj.tamañoTile * 4;
        g2.drawString(texto, x, y);
        // Principal
        g2.setColor(Color.white);
        g2.drawString(texto, x - 4, y - 4);
        // Reintentar
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        texto = "Reintentar";
        x = obtenerXparaTextoCentrado(texto);
        y += pj.tamañoTile * 2;
        g2.drawString(texto, x, y);
        if (numeroComando == 0) g2.drawString(">", x - pj.tamañoTile, y);
        // Rendirse
        texto = "Rendirse";
        x = obtenerXparaTextoCentrado(texto);
        y += pj.tamañoTile;
        g2.drawString(texto, x, y);
        if (numeroComando == 1) g2.drawString(">", x - pj.tamañoTile, y);
    }

    /**
     * Muestra la representación gráfica de la vida del jugador en la interfaz.
     * Utiliza imágenes de corazones para indicar la cantidad de vida restante del jugador.
     * Los corazones vacíos representan la vida faltante, los corazones dañados representan la vida restante y los corazones completos representan la vida recuperada.
     */
    private void mostrarVidaJugador() {
        int x = pj.tamañoTile * 9;
        int y = pj.tamañoTile * 2;
        int i = 0;

        while (i < pj.jugador.vidaMaxima / 2) {
            g2.drawImage(corazon_Vacio, x, y, null);
            i++;
            x += pj.tamañoTile;
        }
        x = pj.tamañoTile * 9;
        y = pj.tamañoTile * 2;
        i = 0;

        while (i < pj.jugador.vida) {
            g2.drawImage(corazon_Lastimado, x, y, null);
            i++;
            if (i < pj.jugador.vida) g2.drawImage(corazon_Completo, x, y, null);
            i++;
            x += pj.tamañoTile;
        }
    }

    /**
     * Dibuja la pantalla de menú del juego.
     */
    public void dibujarPantallaMenu() {
        //Fondo
        g2.setColor(new Color(64, 229, 223));
        g2.fillRect(0, 0, pj.anchoPantalla, pj.altoPantalla);

        //Nombre
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String texto = "Bad Ice Cream";
        int x = obtenerXparaTextoCentrado(texto);
        int y = pj.tamañoTile * 3;

        //Sombre
        g2.setColor(Color.black);
        g2.drawString(texto, x + 5, y + 5);
        //Color principal
        g2.setColor(Color.white);
        g2.drawString(texto, x, y);
        //Imagen Helado
        x = pj.anchoPantalla / 2 - (pj.tamañoTile * 2) / 2;
        y += pj.tamañoTile * 2;
        g2.drawImage(pj.jugador.abajo1, x, y, pj.tamañoTile * 2, pj.tamañoTile * 2, null);
        //Menu
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
     * Dibuja la pantalla de pausa del juego.
     */
    public void dibujarPantallaPausa() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String texto = "PAUSA";
        int x = obtenerXparaTextoCentrado(texto);
        int y = pj.altoPantalla / 2;

        g2.drawString(texto, x, y);
    }

    /**
     * Calcula la posición x para centrar un texto en la pantalla.
     *
     * @param texto El texto cuya posición x se calculará para centrarlo.
     * @return La posición x para centrar el texto en la pantalla.
     */
    public int obtenerXparaTextoCentrado(String texto) {
        int longitudTexto = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = pj.anchoPantalla / 2 - longitudTexto / 2;
        return x; // Devuelve la posición x para centrar el texto en la pantalla
    }

}
