import javax.swing.JFrame;

import players.Lumberjack;
import players.Player;

public class Main {
    

    public static void main(String[] args) {
        JFrame window = new JFrame("Woodchop");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Player p = new Lumberjack("Rody");
        p.play(window);
    }

}
