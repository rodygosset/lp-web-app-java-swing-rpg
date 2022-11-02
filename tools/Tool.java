package tools;

import choppable.Choppable;
import ui.Paintable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Tool implements Paintable {

    public static final int PRICE = 100;

    public Tool() {}

    
    /** 
     * Used to read the ASCII file visually representing the Tool
     * @param path path to the ASCII art file
     * @return     the file's content
     */
    public String readASCIIArtFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
            return "Art...";
        }
    }

    /**
     * Implemented by child classes,
     * which call readASCIIArtFile to get the ASCIIS reprensentation
     * of the current Tool
     * @return ASCII art content
     */
    public abstract String ASCIIArt();
    /**
     * Use to current Tool object to chop up a Choppable object,
     * calling the Choppable.getChopped(int toolEfficiency) method.
     * @param thing the Choppable object
     */
    public abstract void use(Choppable thing);
    /**
     * Get the price of the current Tool.
     * @return the price as an integer
     */
    public int getPrice() { return Tool.PRICE; }
}