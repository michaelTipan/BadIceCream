package InterfazGráfica;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * La clase PanelJuego extiende a JPanel e implementa Runnable.
 * Representa el panel principal del juego donde se dibuja y actualiza todo el contenido del juego.
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

    int FPS = 60; // Fotogramas por segundo

    // Sistema
    GestorTiles gestorTiles = new GestorTiles(this);
    ManejadorTeclas manejadorTeclas = new ManejadorTeclas(this);
    Thread hiloJuego; // Hilo del juego que llama al método run
    public ComprobadorColisiones comprobadorColisiones = new ComprobadorColisiones(this);
    public GestorActivos gestorActivos = new GestorActivos(this);
    public InterfazUsuario interfazUsuario = new InterfazUsuario(this);

    //Jugador y Objetos
    public Jugador jugador = new Jugador(this, manejadorTeclas); // Instanciar jugador
    public ArrayList<ObjetoJuego> objetos = new ArrayList<>(); // ArrayList para objetos de juego

    // EstadoJuego
    public int estadoJuego; // Estado inicial: 0 = Menu, 1 = Juego, 2 = Game Over, 3 = Victory
    public final int estadoMenu = 0;
    public final int estadoJugar = 1;
    public final int estadoPausa = 2;

    /**
     * Constructor del panel del juego.
     * Configura el tamaño de la ventana, el color de fondo, la optimización para dibujar rápido,
     * agrega el teclado a la ventana y permite que la ventana tenga el foco de la aplicación.
     */
    public PanelJuego() {
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla)); // Tamaño de la ventana
        this.setBackground(Color.BLACK); // Color de fondo
        this.setDoubleBuffered(true); // Optimización para dibujar rápido
        this.addKeyListener(manejadorTeclas); // Agregar teclado a la ventana
        this.setFocusable(true); // Permitir que la ventana tenga el foco de la aplicación
    }

    /**
     * Configura el juego estableciendo el estado inicial del juego y cargando los objetos del juego.
     */
    public void configurarJuego() {
        estadoJuego = estadoMenu;
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
     * Actualiza y repinta el juego a una velocidad constante (FPS).
     */
    @Override
    public void run() {
        // Intervalo de tiempo entre cada fotograma en nanosegundos
        double intervaloDibujo = 1000000000 / FPS; // 1 segundo

        // Variable para acumular el tiempo transcurrido
        double delta = 0;

        // Tiempo en nanosegundos al inicio del ciclo de juego
        long ultimoTiempo = System.nanoTime();

        // Variable para almacenar el tiempo actual en cada iteración del ciclo
        long tiempoActual;

        // Ciclo principal del juego
        while (hiloJuego != null) {
            // Obtener el tiempo actual en nanosegundos
            tiempoActual = System.nanoTime();

            // Acumular en delta la fracción del intervalo de dibujo que ha pasado desde la última iteración
            delta += (tiempoActual - ultimoTiempo) / intervaloDibujo;

            // Actualizar el tiempo de la última iteración
            ultimoTiempo = tiempoActual;

            // Si ha pasado el tiempo del intervalo de dibujo
            if (delta >= 1) {
                // Actualizar el estado del juego
                actualizar();

                // Repintar el panel del juego
                repaint();

                // Restar 1 a delta
                delta--;
            }
        }
    }

    /**
     * Actualiza el estado del juego y del jugador dependiendo del estado del juego.
     */
    public void actualizar() {

        if (estadoJuego == estadoJugar) {
            jugador.actualizar();
        }
        if (estadoJuego == estadoPausa) {
            //nada
        }

    }

    /**
     * Método para pintar componentes en el panel del juego.
     * @param g Objeto Graphics para dibujar los componentes.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la clase padre JPanel
        Graphics2D g2 = (Graphics2D) g; // Crear un objeto Graphics2D para dibujar con más funcionalidades

        // Si el estado del juego es el menú, dibujar la interfaz de usuario
        if (estadoJuego == estadoMenu) {
            interfazUsuario.dibujar(g2);
        } else {
            // Si el estado del juego no es el menú, dibujar los tiles, los objetos y el jugador
            gestorTiles.dibujar(g2);
            for (ObjetoJuego obj : objetos) {
                obj.dibujar(g2, this);
            }
            jugador.dibujar(g2);
            interfazUsuario.dibujar(g2);
        }

        g2.dispose(); // Liberar los recursos del objeto Graphics2D para optimizar la memoria
    }

}
