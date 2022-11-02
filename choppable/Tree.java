package choppable;

public class Tree extends Choppable {

    public static final int TREE_CHOP_CREDIT_VAL = 25; 
    public static final String TREE_ICON = "\uD83C\uDF32";

    private int height;
    private int chopsNb;

    public Tree(int height) {
        super(TREE_CHOP_CREDIT_VAL);
        this.height = height;
    }


    /**
     * Overriding the parent method
     * to account for the fact that a Tree an get chopped up
     * several times (into several pieces) depending on its height
     * @param toolEfficiency the price of the Tool object divided by 100 --> < 10
     * @return               the amount of credits this Choppable object sells for
     */
    @Override
    public int getChopped(int toolEfficiency) {
        // don't keep chopping up the tree
        // if it's all chopped up already
        if(chopsNb == height) { 
            return 0; 
        }
        int credits = super.getChopped(toolEfficiency);
        this.chopsNb++;
        return credits;
    }

    
    /** 
     * Calculate how many times the current Tree can still get chopped.
     * @return the tree's height (max chops count) - the number of times it's gotten chopped.
     */
    public int chopsLeft() {
        return height - chopsNb;
    }


    @Override
    public String paint() {
        return TREE_ICON;
    }
    
}
