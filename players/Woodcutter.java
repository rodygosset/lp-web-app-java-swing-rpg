package players;

import java.util.ArrayList;
import java.util.Arrays;

import tools.Axe;
import tools.HandSaw;
import tools.Tool;

public class Woodcutter extends Player {
    
    public static final int INIT_CREDITS = 150;
    public static final String WOODCUTTER_ICON = "\uD83D\uDC77";

    public Woodcutter(String n) {
        super(
            n,
            INIT_CREDITS,
            new ArrayList<Tool>(Arrays.asList(new Axe(), new HandSaw()))
        );
    }

    @Override
    public String paint() {
        return WOODCUTTER_ICON;
    }
}
