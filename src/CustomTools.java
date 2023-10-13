import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CustomTools {
    public static JLabel loadimage (String zrodlo){
        BufferedImage image;
        try{
            InputStream inputStream = CustomTools.class.getResourceAsStream(zrodlo);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));

        } catch (IOException e) {
            System.out.println("Error loading image: " + e);
        }
        return null;
    }
}
