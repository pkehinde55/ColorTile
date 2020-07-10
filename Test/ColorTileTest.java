import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import javax.swing.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
class ColorTileTest {
    private Player p ;
    private Board b;
    private Painter art;
    
    @BeforeEach
    public void constructPrivates() {
        p = new Player();
        b = new Board();
        b.fillBoard();
        art = new Painter();
    }

    @Test
    public void testTileConstructor() {
        Tile red = Tile.createRedTile();
        assertEquals(red.getPointChange(), -15);
    }
    
    @Test
    public void testTileConstructor2() {
        Tile blue = Tile.createBlueTile();
        assertEquals(blue.getColor(), Color.BLUE);
        
    }
    @Test
    public void testTileConstructorIsOn() {
        Tile yellow = Tile.createYellowTile();
        assertTrue(yellow.getIsOn());
    }
    
    @Test
    public void testTileConstructorLocX() {
        Tile red = Tile.createRedTile();
        assertEquals(0,red.getLocX());
    }
    
    @Test
    public void testTileConstructorLocY() {
        Tile red = Tile.createRedTile();
        assertEquals(0,red.getLocY());
    }
    @Test
    public void testTurnBlankOnColored() {
        Tile yellow = Tile.createYellowTile();
        yellow.turnBlank(p);
        Tile blank = Tile.createBlankTile();
        assertTrue(yellow.equality(blank));
    }
    
    @Test
    public void testTurnBlankNoEffectOnBlank() {
        Tile blank = Tile.createBlankTile();
        Tile blank2 = Tile.createBlankTile();
        blank.turnBlank(p);
        assertTrue(blank.equality(blank2));
        
    }
    
    @Test
    public void testTurnBlankPointChange() {
        Tile yellow = Tile.createYellowTile();
        yellow.turnBlank(p);
        assertEquals(30, p.getScore()); 
    }
    
    @Test
    public void testTurnBlankColorsGrabbedAdd() {
        Tile yellow = Tile.createYellowTile();
        yellow.turnBlank(p);
        assertTrue(p.getColorsGrabbed().contains(Color.YELLOW));
    }
    @Test
    public void testTurnBlankRedMarker() {
        Tile red = Tile.createRedTile();
        red.turnBlank(p);
        assertEquals(1, p.getRedMarker());
        
    }
    
    @Test
    public void testTileConstructorSeperateObjects() {
        Tile red = Tile.createRedTile();
        Tile red2 = Tile.createRedTile();
        assertFalse(red == red2);
    }
    @Test
    public void testSetLocX() {
        Tile blank = Tile.createBlankTile();
        blank.setLocX(50);
        assertEquals(50, blank.getLocX());
    }
    @Test
    public void testSetLocY() {
        Tile blank = Tile.createBlankTile();
        blank.setLocY(50);
        assertEquals(50, blank.getLocY());
    }
    
    @Test
    public void testInsertList() {
        p.insertList(Color.RED);
        assertTrue(p.getColorsGrabbed().contains(Color.RED));
    }
    
    @Test
    public void testClipOverMaxX() {
        p.setVx(500);
        p.move();
        assertEquals(350, p.getPosX());
    }
    
    @Test
    public void testMoveNowhere() {
        p.move();
        boolean constant = p.getPosX() == 0 && p.getPosY() == 0;
        assertTrue(constant);
    }
    
    @Test
    public void testMove50() {
        p.setVx(50);
        p.setVy(50);
        p.move();
        boolean moved = p.getPosX() == 50 && p.getPosY() == 50;
        assertTrue(moved);
    }
    
    @Test
    public void testChangeScore() {
        p.changeScore(40);
        assertEquals(40, p.getScore());
    }
    
    @Test
    public void testIncreaseRedMarker() {
        p.increaseRedMarker();
        assertEquals(1, p.getRedMarker());
    }
    
    //---------------Board Test------------------
    
    @Test
    public void testBoardConstructor() {
        assertEquals(8, b.getCourt()[1].length);
    }
    
    @Test
    public void testBoardConstructor2() {
        assertEquals(8, b.getCourt().length);
    }
    
    @Test
    public void testFillBoardNoNullIndex() {
        boolean existNull = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getCourt()[i][j] == null) {
                    existNull = true;
                }
            }
        }
        assertFalse(existNull);
    }
    
    @Test
    public void testFillBoard20Red() {
        int redCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getCourt()[i][j].getColor() == Color.RED) {
                    redCount++;
                }
            }
        }
        assertEquals(20, redCount);
    }
    
    @Test
    public void testFillBoardAtLeast5Yellow() {
        int yellowCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getCourt()[i][j].getColor() == Color.YELLOW) {
                    yellowCount++;
                }
            }
        }
        assertTrue(5 <= yellowCount);
    }
    
    @Test
    public void testFillBoardAtLeast2Blue() {
        int blueCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getCourt()[i][j].getColor() == Color.BLUE) {
                    blueCount++;
                }
            }
        }
        assertTrue(2 <= blueCount);
    }
    
    @Test
    public void testFillBoardStartIsBlank() {
        assertEquals(Color.WHITE, b.getCourt()[0][0].getColor());
    }
    
    @Test 
    public void testSetArrayPositionX() {
        assertEquals(100, b.getCourt()[1][2].getLocX());
    }
    
    @Test 
    public void testSetArrayPositionY() {
        assertEquals(50, b.getCourt()[1][2].getLocY());
    }
    
    @Test
    public void testCurrentTileStart() {
        Tile current = b.currentTile(p);
        assertTrue(current.equality(b.getCourt()[0][0]));
    }
    
    @Test
    public void testCurrentTileMoved() {
        p.setVx(50);
        p.setVy(50);
        p.move();
        Tile current = b.currentTile(p);
        assertTrue(current.equality(b.getCourt()[1][1]));
    }
    
    @Test
    public void testGetGoodColorCounterAllBlanks() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b.getCourt()[i][j].turnBlank(p);
            }
        }
        assertEquals(0, b.getGoodColorCounter());
    }
    
    @Test
    public void testGetGoodColorCounterAllBlanksAndReds() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(b.getCourt()[i][j].getColor() == Color.RED)) {
                    b.getCourt()[i][j].turnBlank(p);
                }
            }
        }
        assertEquals(0, b.getGoodColorCounter());
    }

    @Test
    public void testGetGoodColorCounterAtLeast7AfterFilled() {
        assertTrue(7 <= b.getGoodColorCounter());
    }
    //----------------Painter--------------
    
    @Test
    public void testPainterConstructorEmptyList() {
        assertTrue(art.getHueList().isEmpty());
    }
    
    @Test
    public void testFillHueList10Size() {
        art.fillHueList();
        assertEquals(10, art.getHueList().size());
    }
    
    @Test 
    public void testFillHueListAllAcceptableColors() {
        boolean oneOf3 = true;
        art.fillHueList();
        for (int i = 0; i < 10; i++) {
            Color present = art.getHueList().get(i);
            boolean properColor = present == Color.BLUE || present == Color.YELLOW ||
                    present == Color.GREEN;
            oneOf3 = oneOf3 && properColor;
        }
        assertTrue(oneOf3);
        
    }
    
    @Test 
    public void testCompareLists2Correct() {
        LinkedList <Color> rainbow = new LinkedList<Color>();
        rainbow.add(Color.BLUE);
        rainbow.add(Color.GREEN);
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        p.setColorsGrabbed(rainbow);
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        art.setHueList(paintings);
        assertEquals(100, art.compareLists(p));
        
    }
    
    @Test 
    public void testCompareListsEmptyPlayerList() {
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
     
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        art.setHueList(paintings);
        assertEquals(0, art.compareLists(p));
        
    }
    @Test 
    public void testCompareListsAllCorrect() {
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        art.setHueList(paintings);
        p.setColorsGrabbed(paintings);
        assertEquals(500, art.compareLists(p));
        
    }
    
    @Test 
    public void testCompareListsAllCorrect2() {
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.GREEN);
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        art.setHueList(paintings);
        p.setColorsGrabbed(paintings);
        assertEquals(500, art.compareLists(p));
        
    }
    public void testCompareLists7Correct() {
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.GREEN);
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.BLUE);
        p.setColorsGrabbed(paintings);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        paintings.add(Color.GREEN);
        art.setHueList(paintings);
        assertEquals(350, art.compareLists(p));
        
      
    }
    
    
    @Test
    public void compareListsEncapsulation() {
        LinkedList <Color> rainbow = new LinkedList<Color>();
        rainbow.add(Color.BLUE);
        rainbow.add(Color.GREEN);
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        p.setColorsGrabbed(rainbow);
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        art.setHueList(paintings);
        art.compareLists(p);
        assertEquals(paintings, art.getHueList());
        
    }
    @Test
    public void compareListsEncapsulation2() {
        LinkedList <Color> rainbow = new LinkedList<Color>();
        rainbow.add(Color.BLUE);
        rainbow.add(Color.GREEN);
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        p.setColorsGrabbed(rainbow);
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        art.setHueList(paintings);
        art.compareLists(p);
        assertEquals(rainbow, p.getColorsGrabbed());
        
    }
    
    @Test 
    public void testPrintHueListFirstHalf() {
        LinkedList <Color> paintings = new LinkedList<Color>();
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        art.setHueList(paintings);
        assertEquals(" BLUE GREEN YELLOW YELLOW YELLOW" ,art.printHueListFirstHalf());
    }
    
    @Test 
    public void testPrintHueListSecondHalf() {
        LinkedList <Color> paintings = new LinkedList<Color>();
        for (int i = 0; i < 8; i++) {
            paintings.add(Color.YELLOW);
        }
        paintings.add(Color.BLUE);
        paintings.add(Color.GREEN);
        
        art.setHueList(paintings);
        assertEquals(" YELLOW YELLOW YELLOW BLUE GREEN" ,art.printHueListSecondHalf());
    }
    @Test
    public void testReset() {
        JLabel status = new JLabel();
        GamePlay fun = new GamePlay(status);
        fun.reset();
        boolean playing = fun.getPlaying();
        boolean empty = fun.getPat().getColorsGrabbed().isEmpty();
        boolean pos0 = fun.getPat().getPosX() == 0;
        boolean pos02 = fun.getPat().getPosY() == 0;
        int redCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getCourt()[i][j].getColor() == Color.RED) {
                    redCount++;
                }
            }
        }
        boolean suffecient = playing && empty && pos0 && pos02 && redCount == 20;
        assertTrue(suffecient);
        
    }
    
    @Test 
    public void testGetPosX() {
        assertEquals(0, p.getPosX());
    }
    
    @Test 
    public void testGetPosY() {
        assertEquals(0, p.getPosY());
    }
    
    @Test 
    public void testGetScore() {
        assertEquals(0, p.getScore());
    }
    
    @Test 
    public void testGetRedMarker() {
        assertEquals(0, p.getRedMarker());
    }
    
    @Test 
    public void testGetColorsGrabbed() {
        LinkedList <Color> rainbow = new LinkedList<Color>();
        rainbow.add(Color.BLUE);
        rainbow.add(Color.GREEN);
        p.setColorsGrabbed(rainbow);
        
        assertEquals(rainbow, p.getColorsGrabbed());
    }
    
    @Test 
    public void testSetVx() {
        p.setVx(50);
        assertEquals(50, p.getVx());
    }
    
    @Test 
    public void testSetVy() {
        p.setVy(50);
        assertEquals(50, p.getVy());
    }
    
   
    
    
    
    
    
    
    
    
    
    
    

}
