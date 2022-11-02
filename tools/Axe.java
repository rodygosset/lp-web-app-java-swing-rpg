package tools;

import choppable.Choppable;

public class Axe extends Tool {
    
    public static final int PRICE = 150;

    private static final String ASCII_ART_PATH = "res/ascii-art/axe.text";

    public static final String AXE_ICON = "\uD83E\uDE93";

    public Axe() {
        super();
    }

    /**
     * Overriding the parent method.
     * Call readASCIIArtFile to get the ASCIIS reprensentation
     * of the current Tool
     * @return ASCII art content
     */
    public String ASCIIArt() {
        return this.readASCIIArtFile(Axe.ASCII_ART_PATH);
    }

    /**
     * Overriding the parent method.
     * @param thing the Choppable to chop up
     */
    @Override
    public void use(Choppable thing) {
        thing.getChopped(Axe.PRICE / 100);
    }

    /**
     * Overriding the parent method.
     * @return the price, in credits, of a chainsaw
     */
    @Override
    public int getPrice() { return Axe.PRICE; }


    @Override
    public String paint() {
        return AXE_ICON;
    }
}
