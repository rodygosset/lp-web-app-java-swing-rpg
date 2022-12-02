package players;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import tools.Store;
import tools.Tool;
import ui.EmojiFont;
import ui.GameMap;
import ui.Paintable;

public abstract class Player implements Paintable {

    private int xPos;
    private int yPos;

    public static final int INIT_CREDITS = 200;
    
    private String name;
    private int credits;
    private ArrayList<Tool> tools;

    public Player(String n) {
        this.name = n;
        this.credits = Player.INIT_CREDITS;
        this.tools = new ArrayList<Tool>();
    }

    public Player(String n, int c, ArrayList<Tool> t) {
        this.name = n;
        this.credits = c;
        this.tools = t;
    }

    
    /** 
     * Give money after a transaction.
     * @param amount the amount of credits to substract from the Player's wallet
     * @return       the amount of credits paid by the user
     */
    public int pay(int amount) {
        if(amount > this.credits) { return -1; }
        this.credits -= amount;
        return amount;
    }

    
    /** 
     * Recieve money after a transactionxs
     * @param amount the amount of money to receive
     */
    public void getPayed(int amount) {
        this.credits += amount;
    }

    /**
     * Make the player get rid of a tool.
     * Used during transactions at the store
     * @param index the index of the tool to let go of
     * @return      the retrieved tool
     */
    public Tool dropTool(int index) {
        if(index < 0 || index >= this.tools.size()) {
            return null;
        }
        return this.tools.remove(index);
    }

    /**
     * Add a tool to the player's inventory
     * @param t the tool to add to the inventory
     */
    public void grabTool(Tool t) {
        this.tools.add(t);
    }

    /**
     * Translate the player on the x and y axis of the map.
     * @param map the game map.
     * @param x   the number of cells to translate the user by on the X axis
     * @param y   the number of cells to translate the user by on the Y axis
     */
    public void move(GameMap map, int x, int y) {
        // calculate new position
        int newXPos = this.xPos + x;
        int newYPos = this.yPos + y;
        // in case this translation would move the user outside of the grid
        if(newXPos < 0 || newYPos < 0 
        || newXPos >= GameMap.NB_COLUMNS || newYPos >= GameMap.NB_COLUMNS) { 
            // don't apply the translation
            return; 
        }
        // if the new coordinates are within the bounds of the map
        if(map.isCellEmpty(newXPos, newYPos)) {
            // update the user's position
            map.emptyCell(this.xPos, this.yPos);
            map.putAt(newXPos, newYPos, this);
            // store the new coordinates
            this.xPos = newXPos;
            this.yPos = newYPos;   
        } else if(!map.isCellContentChoppable(newXPos, newYPos)) {
            return;
        } else  {
            while(!map.isCellEmpty(newXPos, newYPos)) {
                this.getPayed(map.chopCellContent(newXPos, newYPos, this.tools.get(0)));
            }
            // update the user's position
            map.emptyCell(this.xPos, this.yPos);
            map.putAt(newXPos, newYPos, this);
            // store the new coordinates
            this.xPos = newXPos;
            this.yPos = newYPos;   
        } 
    }

    
    /**
     * Check whether the player has won by getting to the top right corner.
     * @return true if that's the case, false otherwise
     */
    private boolean playerWon() {
        return this.xPos == GameMap.NB_COLUMNS - 1 && this.yPos == 0;
    }

    
    /** 
     * @return colored text indicating relevant player information.
     */
    private String getPlayerInfoMenu() {
        String menuString = GameMap.colorGreen(this.name) + " - " + GameMap.colorYellow(this.getClass().getSimpleName()) + " " + this.paint();
        menuString += " - " + GameMap.colorCyan(Integer.toString(this.credits) + "c");
        menuString += " - " + GameMap.colorPurple("Tools: ");
        for(int i = 0; i < this.tools.size(); i++) {
            menuString += this.tools.get(i).getClass().getSimpleName() + " " + this.tools.get(i).paint();
            if(i != this.tools.size() - 1) {
                menuString += "  - ";
            }
        }
        return menuString;
    }

    private boolean handleUserChoice(String choice, GameMap map, Store store) {
        switch(choice) {
            case "u": 
                this.move(map, 0, -1);
                break;
            case "d": 
                this.move(map, 0, 1);
                break;
            case "l": 
                this.move(map, -1, 0);
                break;
            case "r": 
                this.move(map, 1, 0);
                break;
            case "s": 
                store.shop(this);
                break;
            case "q": 
                return true;
            default:
                break;
        }
        // if we've arrived at the top right corner
        if(this.playerWon()) {
            return true;
        }  
        return false;
    }

    private class MyKeyAdapter extends KeyAdapter {

        private Player player;
        private GameMap map;

        public MyKeyAdapter(Player p, GameMap m) {
            this.player = p;
            this.map = m;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode) {
                case KeyEvent.VK_UP:
                    player.move(map, 0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    player.move(map, 0, 1);
                    break;
                case KeyEvent.VK_LEFT:
                    player.move(map, -1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.move(map, 1, 0);
                    break;
                default:
                    break;
            }
            map.render();
        }

    }

    public void play(JFrame window) {
        JLabel mapUIComponent = new JLabel();
        // config the JLabel so it can display the unicode emojis
        mapUIComponent.setFont(new EmojiFont().getEmojiFont(20f));
        GameMap map = new GameMap(mapUIComponent);
        JLabel gameTitle = new JLabel("WOODCHOP");
        gameTitle.setForeground(Color.GREEN);
        gameTitle.setFont(new Font("Montserrat", Font.BOLD, 48));
        window.getContentPane().add(gameTitle, BorderLayout.NORTH);
        window.getContentPane().add(mapUIComponent, BorderLayout.CENTER);
        // put the player in the bottom left corner
        this.xPos = 0;
        this.yPos = 9;
        map.putAt(this.xPos, this.yPos, this);
        // set up the key listeners used to move the player around the map
        window.addKeyListener(new MyKeyAdapter(this, map));
        map.render();
    }

    public void oldPlay() {
        // GameMap map = new GameMap();
        Store store = new Store();
        // put the player in the bottom left corner
        this.xPos = 0;
        this.yPos = 9;
        // map.putAt(this.xPos, this.yPos, this);
        System.out.println();
        // show the map
        // map.render();
        System.out.println();
        String choice = "";
        boolean over = false;
        while(!over) {
            // let the user chose an action
            System.out.println(this.getPlayerInfoMenu());
            System.out.println("Move controls:");
            System.out.println("\tu --> move " + GameMap.colorCyan("up"));
            System.out.println("\td --> move " + GameMap.colorCyan("down"));
            System.out.println("\tl --> move " + GameMap.colorCyan("left"));
            System.out.println("\tr --> move " + GameMap.colorCyan("right"));
            System.out.println("Other options:");
            System.out.println("\ts --> go to the " + GameMap.colorPurple("tool store"));
            System.out.println("\tq --> quit game");
            choice = GameMap.prompt();
            // over = handleUserChoice(choice, map, store);
            GameMap.clearScreen();
            System.out.println(GameMap.colorGreen(GameMap.LOGO));
            // map.render();
        }
        // todo --> display youWin or goodbye message in a modal dialog (js alert style)
        // System.out.println(GameMap.colorGreen(this.playerWon() ? youWinString : goodbyeString));
    }

    @Override
    public String toString() {
        return this.getPlayerInfoMenu();
    }

}
