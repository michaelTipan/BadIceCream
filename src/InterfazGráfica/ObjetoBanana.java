package InterfazGráfica;

import javax.imageio.ImageIO;

public class ObjetoBanana extends ObjetoJuego{

    public ObjetoBanana(){
        nombre = "Banana";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objects/Banana.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
