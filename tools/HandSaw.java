package tools;

import choppable.Choppable;

public class HandSaw extends Tool {
 
    public static final int PRICE = 190;

    public static final String SAW_ICON = "\uD83E\uDE9A";

    public HandSaw() {
        super();
    }

    /**
     * Overriding the parent method.
     * @param thing the Choppable to chop up
     * @return      the amount of credits earned
     */
    @Override
    public int use(Choppable thing) {
        return thing.getChopped(HandSaw.PRICE / 100);
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
