package InterfazGráfica;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * La clase PanelJuego se encarga de la configuración y la lógica del juego.
 * Hereda de JPanel e implementa Runnable para manejar el ciclo de juego.
 */
public class PanelJuego extends JPanel implements Runnable {

    // Configuración de pantalla
    final int tamañoTileOriginal = 16; // Tamaño predeterminado de 16x16 para personajes y NPC
    final int escala = 3; // Escala de 3x3 para mapas

    public final int tamañoTile = tamañoTileOriginal * escala; // Tamaño final de 48x48 para personajes y NPC
    public final int maxColPantalla = 16; // 16 columnas
    public final int maxFilaPantalla = 12; // 12 filas
    public final int anchoPantalla = tamañoTile * maxColPantalla; // 768 píxeles
    public final int altoPantalla = tamañoTile * maxFilaPantalla; // 576 píxeles

    // Configuración del juego
    public final int maxColMundo = 18;
    public final int maxFilaMundo = 18;
    public final int anchoMundo = tamañoTile * maxColMundo;
    public final int altoMundo = tamañoTile * maxFilaMundo;

    int FPS = 60; // Fotogramas por segundo

    // Instancias
    GestorTiles gestorTiles = new GestorTiles(this);
    ManejadorTeclas manejadorTeclas = new ManejadorTeclas();
    Thread hiloJuego; // Hilo del juego que llama al método run
    public ComprobadorColisiones comprobadorColisiones = new ComprobadorColisiones(this);
    public GestorActivos gestorActivos = new GestorActivos(this);
    public Jugador jugador = new Jugador(this, manejadorTeclas); // Instanciar jugador
    public ArrayList<ObjetoJuego> objetos = new ArrayList<>(); // ArrayList para objetos de juego

    /**
     * Constructor de la clase PanelJuego.
     * Inicializa la configuración de la pantalla y del juego.
     */
    public PanelJuego() {
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla)); // Tamaño de la ventana
        this.setBackground(Color.BLACK); // Color de fondo
        this.setDoubleBuffered(true); // Optimización para dibujar rápido
        this.addKeyListener(manejadorTeclas); // Agregar teclado a la ventana
        this.setFocusable(true); // Permitir que la ventana tenga el foco de la aplicación
    }

    /**
     * Configura el juego cargando los objetos desde un archivo de texto.
     */
    public void configurarJuego() {
        gestorActivos.cargarObjetos("/maps/objects01.txt");
    }

    /**
     * Inicia el hilo del juego.
     */
    public void iniciarHiloJuego() {
        hiloJuego = new Thread(this); // 'this' pasa PanelJuego al hiloJuego
        hiloJuego.start(); // Iniciar hilo del juego llamando al método run
    }

    /**
     * Método run del hilo del juego.
     * Controla el ciclo de juego y actualiza la pantalla.
     */
    @Override
    public void run() {
        double intervaloDibujo = 1000000000 / FPS; // 1 segundo
        double delta = 0;
        long ultimoTiempo = System.nanoTime();
        long tiempoActual;

        while (hiloJuego != null) {
            tiempoActual = System.nanoTime();

            delta += (tiempoActual - ultimoTiempo) / intervaloDibujo;

            ultimoTiempo = tiempoActual;

            if (delta >= 1) {
                actualizar();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Actualiza el estado del jugador.
     */
    public void actualizar() {
        jugador.actualizar();
    }

    /**
     * Dibuja los componentes del juego en la pantalla.
     * @param g Objeto Graphics para dibujar en la pantalla.
     */
    public void paintComponent(Graphics g) {
        // Dibujar pantalla
        super.paintComponent(g); // Llamar al método paintComponent de JPanel
        Graphics2D g2 = (Graphics2D) g; // Crear un objeto de tipo Graphics2D para dibujar

        // Tile
        gestorTiles.dibujar(g2);

        // Objetos
        for (ObjetoJuego obj : objetos) {
            obj.dibujar(g2, this);
        }

        // Jugador
        jugador.dibujar(g2);
        g2.dispose(); // Cerrar el objeto de tipo Graphics2D para ahorrar memoria
    }
}
