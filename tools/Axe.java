package tools;

import choppable.Choppable;

public class Axe extends Tool {
    
    public static final int PRICE = 150;

    public static final String AXE_ICON = "\uD83E\uDE93";

    public Axe() {
        super();
    }

    /**
     * Overriding the parent method.
     * @param thing the Choppable to chop up
     * @retun       the amount of credits earned
     */
    @Override
    public int use(Choppable thing) {
        return thing.getChopped(Axe.PRICE / 100);
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
