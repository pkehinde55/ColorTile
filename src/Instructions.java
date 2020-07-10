import java.awt.*;
/**
 * This class allows the instructions to be displayed next to the ongoing game.
 * */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import javax.imageio.ImageIO;
public class Instructions extends JPanel {
    private static BufferedImage img;
    public static final String IMG_FILE = "files/colorTile .png";//file in use
    public static final int INIT_POS_X = 400;
    public static final int INIT_POS_Y = 0;
    
    /***/
    public Instructions() {
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        repaint();
    }
    //Displays the picture on the screen after taking a graphics context a s parameter
    @Override
    public  void paintComponent(Graphics g) {
        g.drawImage(img,0, 0, 400, 400, null);
    }
    
    
}


