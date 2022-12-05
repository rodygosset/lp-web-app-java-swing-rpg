import javax.swing.JFrame;

import players.Player;
import ui.CharacterSelectModal;

public class Main {
    

    public static void main(String[] args) {
        JFrame window = new JFrame("Woodchop");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);

        // first, let the user choose which user to play as
        // ask them through a modal

        CharacterSelectModal characterSelect = new CharacterSelectModal(window);
        Player p = characterSelect.askUser();
        // when the user has chosen a character,
        // start the game
        if(p != null) p.play();
    }

}
