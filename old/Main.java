package old;

import java.util.ArrayList;
import java.util.Scanner;

import players.ForestMan;
import players.Lumberjack;
import players.Player;
import players.Woodcutter;
import ui.GameMap;

public class Main {

    public static void main(String[] args) {
        String welcomeString = """
█░█░█ █▀▀ █░░ █▀▀ █▀█ █▀▄▀█ █▀▀   ▀█▀ █▀█
▀▄▀▄▀ ██▄ █▄▄ █▄▄ █▄█ █░▀░█ ██▄   ░█░ █▄█
                """;
        System.out.println(welcomeString);
        System.out.println(GameMap.colorGreen(GameMap.LOGO));
        Player p = null;
        // Ask the user which character he wants to play as
        System.out.println("-- What character would you like to play as ? --");
        String userInput = "";
        // build the list of valid answers
        ArrayList<String> validAnswers = new ArrayList<>(3);
        for(int i = 0; i < 3; i++, validAnswers.add(Integer.toString(i)));
        Scanner sc = new Scanner(System.in);
        while(!validAnswers.contains(userInput)) {
            System.out.println("Options:");
            System.out.println("\t1 --> " + GameMap.colorYellow("ForestMan " + new ForestMan("").paint()));
            System.out.println("\t\t-- Start with " + GameMap.colorCyan("200c") + " and 1 axe.");
            System.out.println("\t2 --> " + GameMap.colorYellow("Woodcutter " + new Woodcutter("").paint()));
            System.out.println("\t\t-- Start with " + GameMap.colorCyan("150c") + ", 1 axe and 1 handsaw.");
            System.out.println("\t3 --> " + GameMap.colorYellow("Lumberjack " + new Lumberjack("").paint()));
            System.out.println("\t\t-- Start with " + GameMap.colorCyan("75c") + ", 1 handsaw and 1 chainsaw.");
            System.out.print(GameMap.colorGreen("> "));
            userInput = sc.nextLine().trim();
        }
        System.out.println("-- Pick a name --");
        System.out.print(GameMap.colorGreen("> "));
        String playerName = sc.nextLine();
        switch(userInput) {
            case "1": 
                p = new ForestMan(playerName);
                break;
            case "2": 
                p = new Woodcutter(playerName);
                break;
            case "3": 
                p = new Lumberjack(playerName);
                break;
            default:
                p = new ForestMan(playerName);
                break;
        }
        p.play();
    }

}
