package tools;

import choppable.Choppable;
import ui.Paintable;

public abstract class Tool implements Paintable {

    public static final int PRICE = 100;

    public Tool() {}

    /**
     * Use to current Tool object to chop up a Choppable object,
     * calling the Choppable.getChopped(int toolEfficiency) method.
     * @param thing the Choppable object
     * @return      the amount of credits earned
     */
    public abstract int use(Choppable thing);
    /**
     * Get the price of the current Tool.
     * @return the price as an integer
     */
    public abstract int getPrice();


    @Override
    public String toString() {
        return this.paint();
    }
}