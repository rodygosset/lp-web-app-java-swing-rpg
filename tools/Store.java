package tools;
import java.util.ArrayList;

import players.Player;
import ui.GameMap;

public class Store {


    public static final String STORE_LOGO = """
            
█▀ ▀█▀ █▀█ █▀█ █▀▀
▄█ ░█░ █▄█ █▀▄ ██▄
            """;

    private ArrayList<Tool> tools;

    private static final int NB_ITEMS_PER_TOOL = 5;

    public Store() {
        this.tools = new ArrayList<Tool>();
        for(int i = 0; i < NB_ITEMS_PER_TOOL; i++) {
            tools.add(new Axe());
            tools.add(new HandSaw());
            tools.add(new ChainSaw());
        }
    }

    
    /** 
     * Retrive the first Tool object of the indicated class
     * and charges the user the price for the chosen tool
     * @param  toolType     the class name of the Tool object to retrieve
     * @param  p            the current player
     * @return              the retrieved Tool object or null if not found
     */
    public Tool buyFrom(String toolType, Player p) {
        // find the first tool in the store of the indicated type
        Tool t = this.tools.stream()
                    .filter(
                        tool -> tool.getClass().getSimpleName().equals(toolType)
                    )
                    .findFirst().orElse(null);
        if(t == null) { return t; }
        int index = this.tools.indexOf(t);
        p.pay(t.getPrice());
        return this.tools.remove(index);
    }

    
    /** 
     * Allow players to sell tools to the store.
     * @param t the Tool object to be added to store
     * @param p the Player selling the Tool
     */
    public void sellTo(Tool t, Player p) {
        this.tools.add(t);
        p.getPayed(t.getPrice());
    }

    private String getStoreLogo() {
        return GameMap.colorPurple(STORE_LOGO);
    }

    private String paintStoreInventory() {
        String inventory = "\n" + GameMap.colorPurple("Store Inventory") + "\n\n";
        ArrayList<String> toolTypes = new ArrayList<String>();
        ArrayList<Integer> toolTypesCount = new ArrayList<Integer>();
        // get the amount of items we have for each type of Tool
        this.tools.forEach(tool -> {
            String className = tool.getClass().getSimpleName();
            String toolString = className + " " + tool.paint() + "  " + GameMap.colorCyan(Integer.toString(tool.getPrice()) + "c") + ""; 
            int index = toolTypes.indexOf(toolString);
            if(!toolTypes.contains(toolString)) {
                toolTypes.add(toolString);
                toolTypesCount.add(1);
            } else {
                toolTypesCount.set(index, toolTypesCount.get(index) + 1);   
            }
        });
        for(int i = 0; i < toolTypes.size(); i++) {
            inventory += toolTypes.get(i) + " (" + toolTypesCount.get(i) + "x)";
            if(i != toolTypes.size() - 1) {
                inventory += ", ";
            }
        }
        return inventory + '\n';
        
    }

    public void handleUserPurchase(Player p) {
        String userInput = "";
        // get the different class names
        ArrayList<String> toolTypes = new ArrayList<String>();
        this.tools.forEach(tool -> {
            String className = tool.getClass().getSimpleName();
            if(!toolTypes.contains(className)) {
                toolTypes.add(className);
            }
        });
        Tool t = null;
        while(t == null) {
            while(!toolTypes.contains(userInput)) {
                System.out.println("Enter the name of the tool you wish to buy:");
                System.out.println("-- To cancel, type 'cancel' --");
                userInput = GameMap.prompt();
                if(userInput.equals("cancel")) {
                    return;
                }
            }
            for(Tool tool : this.tools) {
                if(tool.getClass().getSimpleName().equals(userInput)) {
                    t = tool;
                    break;
                }
            }
            if(p.pay(t.getPrice()) == -1) {
                System.out.println("Sorry, looks like you don't have the funds, yet ;-).");
                System.out.println("Keep chopping!\n");
                t = null;
                userInput = "";
            } 
        }
        this.tools.remove(t);
        p.grabTool(t);

    }

    
    /** 
     * Utility function to check for valid integer string
     * @param s string to validate
     * @return  true if s is formatted as a number, false otherwise
     */
    private static boolean isNumber(String s) {
        return s != null && s.matches("[0-9.]+");
    }

    public void handleUserSell(Player p) {
        String userInput = "";
        Tool t = null;
        while(t == null && !isNumber(userInput)) {
            System.out.println("Enter the index of the tool in your inventory that you want to sell.");
            System.out.println("-- For example, to sell the first one, type '1' --");
            System.out.println("-- To cancel, type 'cancel' --");
            userInput = GameMap.prompt();
            if(isNumber(userInput)) {
                t = p.dropTool(Integer.parseInt(userInput) - 1);
                if(t == null) {
                    System.out.println("\nInvalid input. Please try again.\n");
                }
            } else if(userInput.equals("cancel")) {
                return;
            }
        }
        p.getPayed(t.getPrice());
        this.tools.add(t);
        return;
    }


    public void shop(Player p) {
        GameMap.clearScreen();
        System.out.println(GameMap.getLogo());
        System.out.println(this.getStoreLogo());
        System.out.println(this.paintStoreInventory());
        System.out.println(GameMap.colorCyan("Player"));
        System.out.println(p);
        String userInput = "";
        ArrayList<String> validChoices = new ArrayList<String>(3);
        for(int i = 0; i < 3; i++, validChoices.add(Integer.toString(i)));
        while(!validChoices.contains(userInput)) {
            System.out.println("Options:");
            System.out.println("\t1 --> Buy a tool from the store.");
            System.out.println("\t2 --> Sell a tool to the store.");
            System.out.println("\t3 --> Leave the store.");
            userInput = GameMap.prompt();
            System.out.println();
        }
        switch(userInput) {
            case "1":
                handleUserPurchase(p);
                break;
            case "2":
                handleUserSell(p);
                break;
            case "3":
                return;
            default:
                break;
        }
    }

}



