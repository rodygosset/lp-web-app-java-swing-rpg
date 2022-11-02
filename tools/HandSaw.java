package tools;

import choppable.Choppable;

public class HandSaw extends Tool {
 
    public static final int PRICE = 230;

    private static final String ASCII_ART_PATH = "res/ascii-art/HandSaw.text";

    public static final String SAW_ICON = "\uD83E\uDE9A";

    public HandSaw() {
        super();
    }

    /**
     * Overriding the parent method.
     * Call readASCIIArtFile to get the ASCIIS reprensentation
     * of the current Tool
     * @return ASCII art content
     */
    public String ASCIIArt() {
        return this.readASCIIArtFile(HandSaw.ASCII_ART_PATH);
    }

    /**
     * Overriding the parent method.
     * @param thing the Choppable to chop up
     */
    @Override
    public void use(Choppable thing) {
        thing.getChopped(HandSaw.PRICE / 100);
    }

    /**
     * Overriding the parent method.
     * @return the price, in credits, of a chainsaw
     */
    @Override
    public int getPrice() { return HandSaw.PRICE; }


    public String paint() {
        return SAW_ICON;
    }

}
