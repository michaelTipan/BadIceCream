package InterfazGráfica;

import javax.imageio.ImageIO;

public class ObjetoHielo extends ObjetoJuego{

    public ObjetoHielo(){
        nombre = "Hielo";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Ice.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
