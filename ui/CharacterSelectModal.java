package ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import players.ForestMan;
import players.Lumberjack;
import players.Player;
import players.Woodcutter;

import java.awt.Window;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;


public class CharacterSelectModal extends ModalDialog {

    private Player p;

    public CharacterSelectModal(Window w) {
        super(w, "Character Selection", "Select which character you want to play as", null);
        this.p = null;
    }

    /**
     * Show the modal and let the user choose a character to play the game as
     * @return the instantiated Player object
     */
    public Player askUser() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        CharacterSelectModal thisModal = this;
        // create a button to instatiate each character
        JButton[] characterButtons = new JButton[3];
        characterButtons[0] = new JButton("ForestMan");
        characterButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = new ForestMan("PlayerOne", (JFrame) thisModal.getOwner());
                thisModal.setVisible(false);
            }
        });
        characterButtons[1] = new JButton("Woodcutter");
        characterButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = new Woodcutter("PlayerOne", (JFrame) thisModal.getOwner());
                thisModal.setVisible(false);
            }
        });
        characterButtons[2] = new JButton("Lumberjack");
        characterButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = new Lumberjack("PlayerOne", (JFrame) thisModal.getOwner());
                thisModal.setVisible(false);
            }
        });
        
        // add each button to the panel
        for(JButton b : characterButtons) {
            String description = null;
            switch(b.getText()) {
                case "ForestMan":
                    description = "Start with 200c and 1 axe";
                    break;
                case "Woodcutter":
                    description = "Start with 150c, 1 axe and 1 handsaw";
                    break;
                case "Lumberjack":
                    description = "Start with 75c, 1 handsaw and 1 chainsaw";
                    break;
                default:
                    break;
            }
            panel.add(new JLabel("<html><body>" + description + "<br/></body></html>"));
            panel.add(b);
        }
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        return this.p;
    }
    
}
