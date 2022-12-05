package players;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import tools.ChainSaw;
import tools.HandSaw;
import tools.Tool;

public class Lumberjack extends Player {
    
    public static final int INIT_CREDITS = 75;
    public static final String LUMBERJACK_ICON = "\uD83E\uDDD3";
    

    public Lumberjack(String n, JFrame w) {
        super(
            n,
            INIT_CREDITS,
            new ArrayList<Tool>(Arrays.asList(new ChainSaw(), new HandSaw())),
            w
        );
    }

    @Override
    public String paint() {
        return LUMBERJACK_ICON;
    }
}
