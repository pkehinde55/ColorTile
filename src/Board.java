
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**This class defines the board object of the game. The board is made up of many tiles 
 * and is where the game actually takes place.
 * */
public class Board { 
    private Tile [][] court; //A Tile double array that will model the grid of tiles
    private int goodColorCounter; //How many non-red and non-blank tiles are on the board
    
    public Board() {
        this.court = new Tile[8][8];//The board will be 8 by 8  size
        this.goodColorCounter = 0;
    }
    
    /**
     * Helper function for the fillBoard method. It sets the location
     * of each tile based off the the row and column it is in on the court
     * 2 d array
     * @param None
     * @return None 
     * */
    private void setArrayPosition() {
        for (int i = 0; i < court.length; i++) {
            for (int j = 0; j < court.length; j++) {
                court[i][j].setLocX(j * 50);
                court[i][j].setLocY(i * 50);
            }
        }
    }
    /**
     * Fills the board with tiles so that no index of the court array contains no tile
     * @param None
     * @return None
     * */
    public void fillBoard() {
       
        //Creates a diagonal line of red tiles from upper - right
        //Creates a row of red Tiles across row 4 
        //Creates a column of red Tiles across column 4
        for (int row = 1; row < court.length; row ++) {
            court[row][7 - row] = Tile.createRedTile();
            court[row][4] = Tile.createRedTile();
            court[4][row] = Tile.createRedTile();
        }
        //fills specific indexes with specific color of tiles
        court[2][2] = Tile.createRedTile();
        court[6][6] = Tile.createRedTile();
        
        court[5][5] = Tile.createYellowTile();
        court[3][5] =  Tile.createYellowTile();
        court[7][7] = Tile.createYellowTile();
        court[1][7] = Tile.createYellowTile();
        court[7][1] = Tile.createYellowTile();
        court[6][7] =  Tile.createBlueTile();
        court[2][0] = Tile.createBlueTile();
        court[0][0] = Tile.createBlankTile();
        
        //fills remaining indexes with randomly colored tiles to a degree
        for (int i = 0; i < court.length; i++) {
            for (int j = 0; j < court.length; j++) {
                double random = Math.random();
                
                if (court[i][j] == null) {
                    if (random <= .45) {
                        court[i][j] = Tile.createBlankTile();
                    } else if (random > .45 && random <= .80) {
                        court[i][j] = Tile.createGreenTile();
                        
                    } else if (random > .80 && random < .95) {
                        court[i][j] = Tile.createBlueTile();
                        
                    } else { 
                        court[i][j] = Tile.createYellowTile();
                    }
                        
                }
            }
            
        }
        setArrayPosition();
    }
    
    /**
     * Draws the board of tiles by drawing each tile in the bard at their correct location
     * @param Graphics context g
     * @return None
     * */
    public void draw(Graphics g) {
        for (int i = 0; i < court.length; i++) {
            for (int j = 0; j < court.length; j++) {
                Tile slab = court[i][j]; 
                slab.drawTile(g, slab.getLocX(), slab.getLocY());
            }
        }
    }
    
    /**
     * Returns the current tile that the player is on by comparing the location of a tile to the
     * location of the player 
     * @param a Player p
     * @return Tile that player is currently on*/
    public Tile currentTile(Player p) {
        Tile current = Tile.createBlankTile();
        for (int i = 0; i < court.length; i++) {
            for (int j = 0; j < court.length; j++) {
                if (p.getPosX() == court[i][j].getLocX() && p.getPosY() == court[i][j].getLocY()) {
                    current = court[i][j];
                    break;
                }
               
            }
        }
        return current;
    }
    
   /**
    * Sets and returns the current value of the goodColorCounter
    * by computing the amount of non-red and non-blank tiles currently on the
    * board
    * @param None
    * @return int current goodColorCounter
    * */
    public int getGoodColorCounter() {
        int count = 0;
        for (int i = 0; i < court.length; i++) {
            for (int j = 0; j < court.length; j++) {
                if (!(court[i][j].getColor() == Color.WHITE ||
                        court[i][j].getColor() == Color.RED)) {
                    count ++;
                }
                        
            }
        }
        goodColorCounter = count;
        return goodColorCounter;
    }
    
    /**
     * Getter function for the court field
     * @param None
     * @return Tile 2D array*/
    public Tile [][] getCourt() {
        return court;
    }

    
}