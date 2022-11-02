
import choppable.Bush;
import players.ForestMan;
import players.Player;
import ui.GameMap;

public class Main {

    public static void main(String[] args) {
        GameMap map = new GameMap();
        Player forestMan = new ForestMan("Rody");
        forestMan.play(map);
    }

}
