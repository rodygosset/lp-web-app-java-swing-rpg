package tools;

import choppable.Choppable;

public class ChainSaw extends Tool {
 
    public static final int PRICE = 230;

    public static final String SAW_ICON = "\uD83E\uDE9A";

    public ChainSaw() {
        super();
    }

    /**
     * Overriding the parent method.
     * @param thing the Choppable to chop up
     * @return      the amount of credits earned
     */
    @Override
    public int use(Choppable thing) {
        return thing.getChopped(ChainSaw.PRICE / 100);
    }

    /**
     * Overriding the parent method.
     * @return the price, in credits, of a chainsaw
     */
    @Override
    public int getPrice() { return ChainSaw.PRICE; }


    public String paint() {
        return SAW_ICON;
    }

}
