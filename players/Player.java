package players;

import java.util.ArrayList;
import java.util.Scanner;

import tools.Tool;
import ui.GameMap;
import ui.Paintable;

public class Player implements Paintable {

    private int xPos;
    private int yPos;

    public static final int INIT_CREDITS = 200;
    public static final String PLAYER_ICON = "\uD83E\uDDD4";
    
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


    public String paint() {
        return PLAYER_ICON;
    }

    public void move(GameMap map, int x, int y) {
        int newXPos = this.xPos + x;
        int newYPos = this.yPos + y;
        if(newXPos < 0 || newYPos < 0 
        || newXPos >= GameMap.NB_COLUMNS || newYPos >= GameMap.NB_COLUMNS) { 
            return; 
        }
        map.emptyCell(this.xPos, this.yPos);
        map.putAt(newXPos, newYPos, this);
        this.xPos = newXPos;
        this.yPos = newYPos;
    }

    public void play(GameMap map) {
        this.xPos = 0;
        this.yPos = 9;
        map.putAt(this.xPos, this.yPos, this);
        map.render();
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String choice = "";
        boolean over = false;
        while(!over) {
            System.out.println("Options:");
            System.out.println("\tu --> move up");
            System.out.println("\td --> move down");
            System.out.println("\tl --> move left");
            System.out.println("\tr --> move right");
            System.out.println("\ts --> stop playing");
            choice = sc.nextLine();
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
                    over = true;
                    break;
            }
            map.clearScreen();
            map.render();
        }
    }

}
