package tools;
import java.util.ArrayList;

import players.Player;

public class Store {

    private ArrayList<Tool> tools;

    public Store() {
        this.tools = new ArrayList<Tool>();
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
}
