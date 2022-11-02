package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import choppable.Bush;
import choppable.Choppable;
import choppable.Tree;

public class GameMap implements Paintable {

    private ArrayList<MapCell> cells;

    public static final int NB_COLUMNS = 10;

    private int nbColumns;

    private int nbObstacles;
    public static int MAX_TREE_HEIGHT = 3;

    public static final String HORIZONTAL_LINE_UNICODE = "―";  
    public static final String VERTICAL_LINE_UNICODE = "❘";    
    public static final String TOP_LEFT_CORNER_UNICODE = "⌜";  
    public static final String TOP_RIGHT_CORNER_UNICODE = "⌝";
    public static final String BOTTOM_LEFT_CORNER_UNICODE = "⌞"; 
    public static final String BOTTOM_RIGHT_CORNER_UNICODE = "⌟"; 

    public GameMap() {
        this.nbColumns = NB_COLUMNS;
        this.cells = new ArrayList<MapCell>(this.nbColumns * this.nbColumns);
        this.nbObstacles = this.nbColumns * 7;
        this.genMap();
    }


    /**
     * Generate a map with Choppable objects at random coordinates.
     */
    public void genMap() {
        // start with creating a list of Choppable objects to put on the map
        ArrayList<Choppable> obstacles = new ArrayList<Choppable>(this.nbObstacles);
        // for random int generation
        Random r = new Random();
        for(int i = 0; i < this.nbObstacles; i++) {
            if(i % 2 == 0) {
                obstacles.add(new Tree(r.nextInt(GameMap.MAX_TREE_HEIGHT + 1)));
            } else {
                obstacles.add(new Bush());
            }
        }
        // next, place them at random positions on each column
        ArrayList<Integer> columnCells = new ArrayList<Integer>();
        // get a list of the cells in the current column
        for(int i = 0; i < this.nbColumns; columnCells.add(i), i++);
        // place a few obstacles in each column
        int obstaclePerCol = this.nbObstacles / this.nbColumns;
        int colObstacleCount = 0;
        int index = 0;
        for(int i = 0; i < this.nbColumns; i++) {
            ArrayList<Integer> obstacleIndexes = new ArrayList<Integer>(obstaclePerCol);
            for(int j = 0; j < obstaclePerCol; j++) {
                index = ThreadLocalRandom.current().nextInt(columnCells.size());
                // get a random cell in the current column
                obstacleIndexes.add(columnCells.get(index));
                columnCells.remove(obstacleIndexes.get(j));
            }
            obstacleIndexes.sort(null);
            columnCells.clear();
            // get a list of the cells in the current column
            for(int k = 0; k < this.nbColumns; columnCells.add(k), k++);
            colObstacleCount = 0;
            // fill the rest of the column with empty cells
            for(int j = 0; j < this.nbColumns; j++) {
                if(obstacleIndexes.contains(j)) {
                    this.cells.add(new MapCell(i, obstacleIndexes.get(colObstacleCount), obstacles.remove(0)));
                    colObstacleCount++;
                } else {
                    this.cells.add(new MapCell(i, j, null));
                }
            }
            obstacleIndexes.clear();
        }
        // empty the player's spawn cell
        this.emptyCell(0, 9);
    }
    
    
    /**
     * Clear the screen.
     */
    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }


    // map drawing utils

    private String getHL() { return HORIZONTAL_LINE_UNICODE; }

    private String getVL() { return VERTICAL_LINE_UNICODE; }

    private String getTLC() { return TOP_LEFT_CORNER_UNICODE; }

    private String getTRC() { return TOP_RIGHT_CORNER_UNICODE; }

    private String getBLC() { return BOTTOM_LEFT_CORNER_UNICODE; }

    private String getBRC() { return BOTTOM_RIGHT_CORNER_UNICODE; }

    private String horizontalLine() {
        String l = "";
        String hr = getHL() + getHL() + getHL(); // "―――"
        for(int i = 0; i < this.nbColumns; i++) { l += hr; }
        return l;
    }
    
    /** 
     * Implements Paintable.paint()
     * @return the visual representation of the whole map as a String
     */
    @Override
    public String paint() {
        String map = "";
        int x = 0;
        int y = 0;
        int index = 0;
        // draw the top map line
        map += getTLC() + horizontalLine() + getTRC() + "\n" + getVL();
        for(int i = 0; i < this.nbColumns * this.nbColumns; i++) {
            // calculate the index of the cell in relation to its position on the map
            index = x * this.nbColumns + y;
            map += this.cells.get(index).paint();
            // if we've arrived to the end of the current line
            if((i + 1) % this.nbColumns == 0) {
                map += getVL() + "\n";
            }
            if(i != this.nbColumns * this.nbColumns - 1) {
                map += getVL();
            }
            x++;
            if(x == this.nbColumns) {
                x = 0;
                y++;
            }
        }
        // draw the bottom map line
        map += getBLC() + horizontalLine() + getBRC() + '\n';
        return map;
    }

    /** 
     * Display the visusal representation of the map on the screen.
    */
    public void render() {
        System.out.println(this.paint());
    }

    
    /** 
     * If exists, 
     * replace the cell at position (x, y) with an empty one
     * @param x the cell's position on the X axis
     * @param y the cell's position on the Y axis
     */
    public void emptyCell(int x, int y) {
        // find the cell at the indicated position
        MapCell c = cells.stream().filter(cell -> cell.isAt(x, y)).findFirst().orElse(null);
        if(c == null) { return; }
        // get its index
        int index = this.cells.indexOf(c);
        // replace the cell at this position in the ArrayList
        // with one that's empty
        this.cells.set(index, new MapCell(x, y, null));
    }

    
    /** 
     * Replaces the cell at position (x, y) 
     * with a cell containing a new Paintable object.
     * @param x position on the X axis
     * @param y position on the Y axis
     * @param p a Paintable object
     */
    public void putAt(int x, int y, Paintable p) {
        // find the cell at the indicated position
        MapCell c = cells.stream().filter(cell -> cell.isAt(x, y)).findFirst().orElse(null);
        if(c == null) { return; }
        // get its index
        int index = this.cells.indexOf(c);
        // replace the cell at this position in the ArrayList
        // with one that contains p
        this.cells.set(index, new MapCell(x, y, p));
    }

}
