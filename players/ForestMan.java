package players;

import java.util.ArrayList;
import java.util.Arrays;

import tools.Axe;
import tools.Tool;

public class ForestMan extends Player {

    public static final int INIT_CREDITS = 200;
    
    public ForestMan(String n) {
        super(
            n, 
            INIT_CREDITS, 
            new ArrayList<Tool>(Arrays.asList(new Axe()))
        );
    }


}
