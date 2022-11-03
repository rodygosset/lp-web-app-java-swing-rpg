package choppable;
import java.util.concurrent.TimeUnit;

import ui.Paintable;

public abstract class Choppable implements Paintable {
    
    private int creditValue;

    public Choppable(int currencyValue) {
        this.creditValue = currencyValue;
    }

    
    /** 
     * Cut up a Choppable object.
     * @param toolEfficiency the price of the Tool object divided by 100 --> < 10
     * @return               the amount of credits this Choppable object sells for
     */
    public int getChopped(int toolEfficiency) {
        // sleep while the wood is getting chopped up
        // the lower the tool efficiency
        // the longer the player waits
        try {
            TimeUnit.MILLISECONDS.sleep(1500 / toolEfficiency);
            // when we're done, return the amount of credits earned 
            return creditValue;
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
            return 0;
        }
    }

}
