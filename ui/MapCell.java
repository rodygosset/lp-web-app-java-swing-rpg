package ui;

public class MapCell implements Paintable {

    private int x;
    private int y;
    private Paintable content;
    
    public MapCell(int x, int y, Paintable c) {
        this.x = x;
        this.y = y;
        this.content = c;
    }

    public String position() {
        return "(" + this.x + "," + this.y + ")";
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
            return "  ";
        }
        return content.paint();
    }

}
