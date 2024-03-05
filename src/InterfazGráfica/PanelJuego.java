package InterfazGráfica;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * La clase GestorActivos se encarga de gestionar la carga de objetos del juego
 * en el mundo del juego a partir de un archivo de texto.
 * @author      Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
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
    public final int maxMapa = 3;
    public int mapaActual = 0;

    int FPS = 60; // Fotogramas por segundo

    // Sistema
    public GestorTiles gestorTiles = new GestorTiles(this);
    ManejadorTeclas manejadorTeclas = new ManejadorTeclas(this);
    Thread hiloJuego; // Hilo del juego que llama al método run
    public InterfazLógica.ComprobadorColisiones comprobadorColisiones = new InterfazLógica.ComprobadorColisiones(this);
    public InterfazLógica.GestorActivos gestorActivos = new InterfazLógica.GestorActivos(this);
    public InterfazUsuario interfazUsuario = new InterfazUsuario(this);
    public GestorEventos gestorEventos = new GestorEventos(this);
    public InterfazLógica.GestorPuntuacion gestorPuntuacion = new InterfazLógica.GestorPuntuacion();
    InterfazGráfica.CargarGuardar cargarGuardar = new InterfazGráfica.CargarGuardar(this);

    //Jugador y Objetos
    public Jugador jugador = new Jugador(this, manejadorTeclas); // Instanciar jugador
    public ArrayList<ObjetoJuego> objetos = new ArrayList<>(); // ArrayList para objetos de juego
    public Enemigo enemigos[][] = new Enemigo[maxMapa][12]; // Instanciar enemigo

    // EstadoJuego
    public int estadoJuego; // Estado inicial: 0 = Menu, 1 = Juego, 2 = Game Over, 3 = Victory
    public final int estadoMenu = 0;
    public final int estadoJugar = 1;
    public final int estadoPausa = 2;
    public final int estadoGameOver = 3;

    // Constructor del panel del juego
    public PanelJuego() {
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla)); // Tamaño de la ventana
        this.setBackground(Color.BLACK); // Color de fondo
        this.setDoubleBuffered(true); // Optimización para dibujar rápido
        this.addKeyListener(manejadorTeclas); // Agregar teclado a la ventana
        this.setFocusable(true); // Permitir que la ventana tenga el foco de la aplicación
    }

    /**
     * Configura el juego inicialmente.
     */
    public void configurarJuego() {
        gestorActivos.cargarObjetos("/maps/objects0.txt");
        gestorActivos.cargarEnemigos();
        estadoJuego = estadoMenu;
    }

    /**
     * Reinicia el estado del juego.
     * @param rendirse Indica si el jugador se rindió.
     */
    public void reiniciarJuego(boolean rendirse){

        //gestorActivos.objetos.clear();
        gestorActivos.cargarEnemigos();
        jugador.establecerValoresPredeterminados();
        //gestorActivos.cargarObjetos("/maps/objects"+ mapaActual +".txt");

        if (rendirse == true) {
            gestorPuntuacion.reiniciarPuntuacion();
            interfazUsuario.gestorTiempo.reiniciarTiempo();
            mapaActual = 0;
            gestorActivos.cargarObjetos("/maps/objects0.txt");
        }
    }

    /**
     * Inicia el hilo del juego.
     */
    public void iniciarHiloJuego() {
        hiloJuego = new Thread(this); // 'this' pasa PanelJuego al hiloJuego
        hiloJuego.start(); // Iniciar hilo del juego llamando al método run
    }

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
     * Actualiza la lógica del juego.
     */
    public void actualizar() {

        if (estadoJuego == estadoJugar) {

            jugador.actualizar();

            for (int i = 0; i < enemigos[1].length; i++) {
                if (enemigos[mapaActual][i] != null) {
                    enemigos[mapaActual][i].actualizar();
                }
            }
        }
        if (estadoJuego == estadoPausa) {
            //nada
        }

    }

    public void paintComponent(Graphics g) {
        // Dibujar pantalla
        super.paintComponent(g); // Llamar al método paintComponent de JPanel
        Graphics2D g2 = (Graphics2D) g; // Crear un objeto de tipo Graphics2D para dibujar

        // Menu
        if (estadoJuego == estadoMenu) {
            interfazUsuario.dibujar(g2);
        } else {

            gestorTiles.dibujar(g2);

            for (ObjetoJuego obj : objetos) {
                obj.dibujar(g2, this);
            }

            // Jugador
            jugador.dibujar(g2);

            for (int i = 0; i < enemigos[1].length; i++) {
                if (enemigos[mapaActual][i] != null) {
                    enemigos[mapaActual][i].dibujar(g2);
                }
            }
            // Interfaz usuario
            interfazUsuario.dibujar(g2);

        }
        g2.dispose(); // Cerrar el objeto de tipo Graphics2D para ahorrar memoria
    }
}
