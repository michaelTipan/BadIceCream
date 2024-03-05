package InterfazGráfica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * La clase CargarGuardar se encarga de guardar y cargar el estado del juego.
 * @author     Grupo 6
 * @author	   Escuela Politécnica Nacional
 * @version     1.0
 */
public class CargarGuardar {

    PanelJuego pj;

    /**
     * Constructor de la clase CargarGuardar.
     *
     * @param pj Instancia de PanelJuego para acceder a las propiedades del juego.
     */
    public CargarGuardar(PanelJuego pj) {
        this.pj = pj;
    }

    /**
     * Guarda el estado actual del juego en un archivo.
     */
    public void guardar(){

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("guardado.dat")));
            GrabadorDatos gd = new GrabadorDatos();

            gd.vida = pj.jugador.vida;
            gd.mapaActual = pj.mapaActual;
            gd.puntuacion =  pj.gestorPuntuacion.getPuntos();
            gd.tiempo =  pj.interfazUsuario.gestorTiempo.getTiempo();

            oos.writeObject(gd);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carga el estado del juego desde un archivo.
     */
    public void cargar(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("guardado.dat")));
            GrabadorDatos gd = (GrabadorDatos) ois.readObject();

            // Restaurar el estado del juego
            pj.objetos.clear();
            pj.jugador.vida = gd.vida;
            pj.mapaActual = gd.mapaActual;
            pj.gestorActivos.setCantidadBananas(0);
            pj.gestorActivos.setCantidadUvas(0);
            pj.jugador.establecerValoresPredeterminados();
            pj.gestorActivos.cargarObjetos("/maps/objects"+ gd.mapaActual +".txt");
            pj.gestorPuntuacion.setPuntos(gd.puntuacion);
            pj.interfazUsuario.gestorTiempo.setTiempo(gd.tiempo);
            pj.estadoJuego = pj.estadoJugar;

            ois.close();

            System.out.println("Datos cargados");

        } catch (Exception e){
            System.out.println("Falla al cargar datos");
        }

    }
}
