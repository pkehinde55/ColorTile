import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GamePlay
 * 
 * This class holds the primary game logic for how the player, board, and painter interact. 
 */

public class GamePlay extends JPanel {
    
    private Board board;//Tile board
    private Player pat;//Player icon
    private Painter picasso;//Painter with ordered color list
  
    
    private boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text
    
 // Game constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;
    public static final int PLAYER_VELOCITY = 50;
    
 // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;
    
    public GamePlay(JLabel status) {
     // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 
        
        setFocusable(true);//Enable keyboard focus on GamePlay area
        // This key listener allows the player to move 
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    pat.setVx(-PLAYER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
                    pat.setVx(PLAYER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    pat.setVy(PLAYER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    pat.setVy(-PLAYER_VELOCITY);
                }
            }
            //Once key is released velocity of player set to 0
            public void keyReleased(KeyEvent e) {
                pat.setVx(0);
                pat.setVy(0);
            }
        });

        this.status = status;
    }
    
    /**
     * (Reset the game to its initial state.
     */
    public void reset() {
        board = new Board();
        board.fillBoard();
        pat = new Player();
        picasso = new Painter();
        picasso.fillHueList();
        playing = true;
        status.setText("Current Score: " + Integer.toString(pat.getScore()));
       
        requestFocusInWindow();
    }
    
    void tick() {
        if (playing) {
            pat.move();
            Tile current = board.currentTile(pat);
            current.turnBlank(pat);//Current tile player is on forever becomes blank
            //If no more non-blank and non-red tiles on board Game is over
            if (board.getGoodColorCounter() == 0) {
             
                playing = false;
                //Display player regular score and bonus points
                status.setText("Game Over:" + "Score = " + Integer.toString(pat.getScore()) + 
                        " Bonus Points =  " + Integer.toString(picasso.compareLists(pat)));
                //If player passes 6 or more red tiles game is over 
            } else if (pat.getRedMarker() >= 6) {
               
                playing = false;
                status.setText("Game Over:" + "Score = " + Integer.toString(pat.getScore()) + 
                        " Bonus Points =  " + Integer.toString(picasso.compareLists(pat)));
            } else {
                //int bonusPoints = picasso.compareLists(pat); 
                status.setText("Current Score: " + Integer.toString(pat.getScore()) + 
                        " Bonus Points =  " + Integer.toString(picasso.compareLists(pat)));
            }
            
        } 

        repaint();
    }
    //Calls all necessary draw elements for the board, player, and painter
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
        pat.draw(g);
        picasso.draw(g);
        
    }
    //Sets dimensions for board
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
   //-------------Getter Functions For Testing Only ----------
    public boolean getPlaying() {
        return playing;
    }
    public Player getPat() {
        return pat;
    }
    

}
