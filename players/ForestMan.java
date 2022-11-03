package players;

import java.util.ArrayList;
import java.util.Arrays;

import tools.Axe;
import tools.Tool;

public class ForestMan extends Player {

    public static final int INIT_CREDITS = 200;
    public static final String FORESTMAN_ICON = "\uD83E\uDDD4";
    
    public ForestMan(String n) {
        super(
            n, 
            INIT_CREDITS, 
            new ArrayList<Tool>(Arrays.asList(new Axe()))
        );
    }

    public String paint() {
        return FORESTMAN_ICON;
    }


}
