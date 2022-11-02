package choppable;

public class Bush extends Choppable {

    public static final int BUSH_CREDIT_VAL = 40;
    public static final String BUSH_ICON = "\uD83C\uDF33";

    public Bush() {
        super(BUSH_CREDIT_VAL);
    }


    @Override
    public String paint() {
        return BUSH_ICON;
    }
    
}
