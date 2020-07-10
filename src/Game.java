import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
/**
 * This class allows the game to actually run
 * */
public class Game extends JApplet implements Runnable {// Step  1 extend JAapplet
    Graphics g;
    
    public void run() {
       
        //Creates frame
        final JFrame frame = new JFrame("COLOR TILE");
        frame.setLocation(300, 300);

        // Status panel
        
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        
        //Add  game to frame
        final GamePlay gameTime = new GamePlay(status);
        frame.add(gameTime, BorderLayout.WEST);
        
        //Add instructions to frame
        final Instructions imager = new Instructions();
        frame.add(imager, BorderLayout.CENTER);
        
        //Adds control panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameTime.reset();
                
            }
            
        });
        control_panel.add(reset);

    
       
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        gameTime.reset();

    }    
    public static void main(String[] args) { //Step 2 remove main method
        SwingUtilities.invokeLater(new Game());
    }
}