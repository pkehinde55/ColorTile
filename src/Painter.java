import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * This class creates the Painter that has assigned you to grab these
 * colors. The more the order you grabbed the first 10 colors in his hueList
 * the more bonus points you get.
 * */

public class Painter extends JPanel {
    LinkedList <Color> hueList;

    public Painter() {
        hueList = new LinkedList<Color>();
    }

/**
 * Fills hueList with colors green, blue, and yellow at random to make
 * an unpredictable hueList
 * @param None
 * @return None
 * */
    public void fillHueList() {
        for (int i = 0; i < 10; i++) {
            double random = Math.random();
            if (random <= .63) {
                hueList.add(Color.GREEN);
            } else if (random > .63 && random <= .91) {
                hueList.add(Color.BLUE);
            } else {
                hueList.add(Color.YELLOW);
            }
        } 
    }
/**
 * Returns the hueList of the painter/for testing
 * @param None
 * @return hueList
 * */
    public LinkedList<Color> getHueList() {
        return hueList;
    }
    
/**
 * Compares the list of the player, to the Hue List of the painter at the end of
 * the game. Bonus points are computed for the player by their colorGrabbed list 
 * comparability to the Painter's Hue List.
 * @param Player with their own color list
 * @return int representing the bonus points the player will recieve from comparing the
 * lists
 *  */
    public int compareLists(Player p) {
        LinkedList<Color> playerColors = p.getColorsGrabbed();
        int size = playerColors.size() - 1;
        int bonusPoints = 0;
        for (int i = 0; i < 10; i++) {
            if (i <= size) {
                if (hueList.get(i).equals(playerColors.get(i))) {
                    bonusPoints += 50;
                }
            }
        }
        return bonusPoints;
    }
    
    /**
     * Setter function for the hueList field. Simply for testing
     * @param LinkedList <Color> rainbow
     * @return None
     * */
    public void setHueList(LinkedList <Color> rainbow) {
        hueList = rainbow;
    }
    
    /**
     * These functions return a string form of the painter's list in halves
     * @param None
     * @return String form of list
     * */
    public String printHueListFirstHalf() {
        String orders = "";
        for (int i = 0; i < 5; i++) {
            if (hueList.get(i) == Color.BLUE) {
                orders = orders + " BLUE";
            } else if (hueList.get(i) == Color.GREEN) {
                orders = orders + " GREEN";
            } else  {
                orders = orders + " YELLOW";
            }
            
        }
        return orders;
    }
    public String printHueListSecondHalf() {
        String orders = "";
        for (int i = 5; i < 10; i++) {
            if (hueList.get(i) == Color.BLUE) {
                orders = orders + " BLUE";
            } else if (hueList.get(i) == Color.GREEN) {
                orders = orders + " GREEN";
            } else  {
                orders = orders + " YELLOW";
            }
            
        }
        return orders;
    }
    /**
     * Draws the painter. This is just displaying the painter's list
     * */
    public void draw(Graphics g) {
        g.drawString(printHueListFirstHalf(), 0, 450);
        g.drawString(printHueListSecondHalf(), 0, 470);
        
    }
   
}