package players;

import java.util.ArrayList;
import java.util.Arrays;

import tools.ChainSaw;
import tools.HandSaw;
import tools.Tool;

public class Lumberjack extends Player{
    
    public static final int INIT_CREDITS = 75;

    public Lumberjack(String n) {
        super(
            n,
            INIT_CREDITS,
            new ArrayList<Tool>(Arrays.asList(new ChainSaw(), new HandSaw()))
        );
    }
}
