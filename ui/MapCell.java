package ui;

import choppable.Choppable;
import choppable.Tree;
import tools.Tool;

public class MapCell implements Paintable {

    private int x;
    private int y;
    private Paintable content;
    
    public MapCell(int x, int y, Paintable c) {
        this.x = x;
        this.y = y;
        this.content = c;
    }

    /**
     * Check if the content of the cell is choppable.
     * @return true if that is the case, false otherwise.
     */
    public boolean isContentChoppable() {
        return this.content instanceof Choppable;
    }

    /**
     * Check whether the cell is empty.
     * @return true if that is the case, false otherwise.
     */
    public boolean isEmpty() {
        return this.content == null;
    }

    
    /** 
     * If the content of the cell is choppable, proceed to chopping it
     * @param t     the tool used to chop
     * @return int  the amount of credits earned
     */
    public int chopContent(Tool t) {
        if(!this.isContentChoppable()) { return 0; }
        System.out.println("Chop chop " + t.paint() + " ...");
        int credits = t.use((Choppable) content);
        if(this.content instanceof Tree && ((Tree) this.content).chopsLeft() == 0 ||
            !(this.content instanceof Tree)) {
            this.content = null;
        } 
        return credits;
    }
    
    /** 
     * Used to check the position of a MapCell
     * @param posX the cell's position on the X axis
     * @param posY the cell's position on the Y axis
     * @return     whether the provided coordinates correspond to the current cell
     */
    public boolean isAt(int posX, int posY) {
        return this.x == posX && this.y == posY;
    }
    
    /** 
     * Gets the content of the cell, 
     * whether a Paintable object or null
     * and returns it as a String
     * @return the string representation of the cell's content
     */
    public String paint() {
        if(this.content == null) {
            // return "  ";
            return "&nbsp;";
        }
        return content.paint();
    }

}
