import java.util.*;
/**
 * This class designs a the player of the game. A player is shown as a magenta circle. 
 * */

import java.awt.*;

public class Player {
    
    private int posX;//X axis position of player
    private int posY;//Y axis position of player
    private int width;//Width of player Icon
    private int height;//Height of player Icon
    private int score = 0;//Score of player
    private LinkedList<Color> colorsGrabbed;//List of colors in the order the player grabbed them
    private int velocityX;//How fast the player moves in x direction
    private int velocityY;//How fast the player moves in y direction
    private int maxX;//Maximum x position player allowed to be in
    private int maxY;//maximum y position the player is allowed to be in
    private int redMarker;//Number of times a redTile was passed
    
    public Player() {
        this.posX = 0;//Position of the player on the x axis
        this.posY = 0;//Position of the player on the x axis
        this.width = 50;//Width of the circle representing the player
        this.height = 50;//height of the circle representing the player
        this.score = 0;//Player's current score
      //List of the colors grabbed by the player in the order they passed the tiles
      //not including the blank and red tiles 
        this.colorsGrabbed = new LinkedList<Color>();
        this.velocityX = 0;//Velocity of the player circle in horizontal direction
        this.velocityY = 0;//Velocity of the player circle in vertical direction
      //The max value of posX for circle is the right end of the tile board
        this.maxX = 400 - width;
      //The max value of posY for circle is the bottom end of the tile board
        this.maxY = 400 - height;
    }
    
    /**
     * This function adds the given color to the player's colorsGrabbed
     * @param Color
     * @return None 
     * */
    public void insertList(Color passed) {
        colorsGrabbed.add(passed);
    }
    
    /**
     * Draws the player as a magenta circle at (posX, posY) with radius width
     * @param Graphics context
     * @return None
     * */
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(posX, posY, width, height);
    }
    
    /**
     * Prevents the player from going outside of the bounds of the board.
     * @param None
     * @return None
     */ 
    private void clip() {
        this.posX = Math.min(Math.max(this.posX, 0), this.maxX);
        this.posY = Math.min(Math.max(this.posY, 0), this.maxY);
    }
    
    /**
     * Updates the player's position by the current velocities 
     * @param None
     * @return None
     * */
    public void move() {
        posX += velocityX;
        posY += velocityY;
        clip();
    }
    
    /**
     * Changes the player's score by the value points given
     * @param int points to change score by
     * @return None
     * */
    public void changeScore(int points) {
        score += points;
    }
    /**
     * Sets velocityX of the player to given value 
     * @param int new wanted velocity in the x direction
     * @return None
     * */
    public void setVx(int vx) {
        this.velocityX = vx;
    }
    /**
     * Sets velocityY of the player to given value 
     * @param int new wanted velocity in the y direction
     * @return None
     * */
    public void setVy(int vy) {
        this.velocityY = vy;
    }
    /**
     * Increases the reddMarker of the player by 1
     * @param None 
     * @return None
     * */
    public void increaseRedMarker() {
        redMarker++;
    }
    
    /**Getter function for the colorsGrabbed field 
     * @param None
     * @return LinkedList of colors colorsGrabbedd
     * */
    public LinkedList<Color> getColorsGrabbed() {
        return colorsGrabbed;
    }
    /**Getter function for the redMarker field 
     * @param None
     * @return int redMarker
     * */
    public int getRedMarker() {
        return redMarker;
    }
    
    /**Getter function for the redMarker field 
     * @param None
     * @return int score
     * */
    public int getScore() {
        return score;
    }
    
    /**Getter function for the posX field 
     * @param None
     * @return int posX
     * */
    public int getPosX() {
        return posX;
    }
    
    /**Getter function for the posY field 
     * @param None
     * @return int posY
     * */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Setter function for the colorsGrabbed field. Simply for testing
     * @param LinkedList <Color> rainbow
     * @return None
     * */
    public void setColorsGrabbed(LinkedList <Color> rainbow) {
        colorsGrabbed = rainbow; 
    }
    /**
     * Getter functions for velocity components for testing
     * @param None
     * @return int of velocity components
     * */
    public int getVx() {
        return velocityX;
    }
    public int getVy() {
        return velocityY;
    }

}
 

