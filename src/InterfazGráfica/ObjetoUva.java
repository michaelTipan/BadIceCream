package InterfazGráfica;

import javax.imageio.ImageIO;

public class ObjetoUva extends ObjetoJuego{

    public ObjetoUva(){
        nombre = "Uva";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Grape.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
