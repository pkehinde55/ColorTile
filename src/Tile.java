import java.awt.*;
/**
 * This class designs a single tile in the game. Each tile will be part 
 * of  a board of tiles. */


public class Tile {
    private int pointChange; //The change of the player's score if they cross this tile
    private boolean isOn; //Does this tile change the palyer's core or is it a blank tile
    private Color color; //Color of the tile
    private int locX;//Location of the tile in the x axis
    private  int locY;//Location of the tile in the y-axis
    
    public Tile(int pointdifference, boolean working, Color color) {
        this.pointChange = pointdifference;
        this.isOn = working;
        this.color = color;
        locX = 0;
        locY = 0;
        
    }
    
    /**
     * Creates a red tile that decreases the player's points by 20 when passed
     * @param None
     * @return Tile
     * */
    public static Tile createRedTile() {
        return new Tile(-15, true, Color.RED);
    }
    /**
     * Creates a yellow tile that increases the player's points by 30 when passed
     * @param None
     * @return Tile
     * */
    public static Tile createYellowTile() {
        return new Tile(30, true, Color.YELLOW);
    }
    /**
     * Creates a blue tile that increases the player's points by 10 when passed
     * @param None
     * @return Tile
     * */
    public static Tile createBlueTile() {
        return new Tile(10, true, Color.BLUE);
    }
    /**
     * Creates a green tile that increases the player's points by 5 when passed
     * @param None
     * @return Tile
     * */
    public static Tile createGreenTile() {
        return new Tile(5, true, Color.GREEN);
    }
    /**
     * Creates a white tile that does not change the player's points  when passed
     * @param None
     * @return Tile
     * */
    public static Tile createBlankTile() {
        return new Tile(0, false, Color.WHITE);
    }
    
    /**
     * Draws a tile as a 50 by 50 square with the appropriate color
     * @param a graphics context, and the x and y position of Tile
     * @return Tile
     * */
    public void drawTile(Graphics g, int xPosition, int yPosition) {
        g.setColor(color);
        g.fillRect(xPosition, yPosition, 50, 50);
    }
    
    /**
     * Turns a colored Tile into an equivalent of a blank tile.
     * Also strips the color from the tile and inserts the color into the
     * colorsGrabbed list of the player if the tile was not previously red.
     * If the tile was previously red, then the redMarker of the player is increased by 1. 
     * @param Player in the game 
     * @return None
     * */
    public void turnBlank(Player pat) {
        if (isOn) {
            pat.changeScore(pointChange);
            if (color != Color.RED) {
                pat.insertList(color);
            } else {
                pat.increaseRedMarker();    
            }
            isOn = false;
            pointChange = 0;
            color = Color.WHITE;
        }
    }
    
    /**
     * Sets the x axis location of the tile as the given parameter
     * @param int new x position
     * @return None
     * */
    public void  setLocX(int newX) {
        locX = newX;
    }
    /**
     * Sets the y axis location of the tile as the given parameter
     * @param int new y position
     * @return None
     * */    
    public void  setLocY(int newY) {
        locY = newY;
    }
    /**
     * Returns the x axis location of the tile
     * @param None
     * @return int locX
     * */    
    public int getLocX() {
        return locX;
    }
    /**
     * Returns the y axis location of the tile
     * @param None
     * @return int locY
     * */       
    public int getLocY() {
        return locY;
    }
    /**
     * Returns the isOn field of the tile
     * @param None
     * @return boolean isOn
     * */       
    public boolean getIsOn() {
        return isOn;
    }
    /**
     * Returns the PointChange field of the tile
     * @param None
     * @return int PointChange
     * */       
    public int getPointChange() {
        return pointChange;
    }
    /**
     * Returns the color field of the tile
     * @param None
     * @return Color color
     * */       
    public Color getColor() {
        return color;
    }
    /**
     * Checks if the given Tile is equal to the this in terms of structure. For testing 
     * @param other Tile
     * @return boolean showing if equal
     * */
    public boolean equality(Tile other) {
        boolean pointsEqual = this.getPointChange() == other.getPointChange();
        boolean colorSame = this.getColor() == other.getColor();
        boolean lightSwitch = this.getIsOn() == other.getIsOn();
        boolean sameX = this.locX == other.locX;
        boolean sameY = this.locY == other.locY;
        return pointsEqual && colorSame && lightSwitch && sameX && sameY;
        
    }
    
    
    
    

    


}
