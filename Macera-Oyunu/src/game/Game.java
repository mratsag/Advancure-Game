package game;
import battleLoc.*;
import normalLoc.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Location location;
    static Random random = new Random();

    public void start() {
        System.out.println("Welcome to Adventure Game!");
        System.out.print("Please enter your player name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();

        player = new Player(name);
        player.selectChar();

        boolean game = true;
        while (game) {
            game = winCondition();
            if(!game)
                break;

            player.playerInfo();
            player.getInventory().treasureInfo();

            System.out.println("------------------------------------------------");
            System.out.println("*                    REGIONS                   *");
            System.out.println("|1| - Safe House -> Here is your health renewing, there is no obstacle in here!");
            System.out.println("|2| - Tool Store -> You can buy Gun and Armor in here!");
            System.out.println("|3| - River -> Award <WATER>, you may encounter a bear!");
            System.out.println("|4| - Cave -> Award <FOOD>, you may encounter zombies!");
            System.out.println("|5| - Forest -> Award <FIREWOOD>, you might encounter a vampire!");
            System.out.println("|6| - Mine -> Award<ANY ITEM>, you may encounter a snake!");
            System.out.println();
            System.out.println("|0| - Exit -> Exit the game");
            System.out.println("------------------------------------------------");

            int secim;
            do {
                System.out.print("Select the region you want to go to (1-5): ");
                secim = scanner.nextInt();
                System.out.println("------------------------------------------------");

                switch (secim) {
                    case 0 -> location = null;
                    case 1 -> location = new SafeHouse(player);
                    case 2 -> location = new ToolStore(player);
                    case 3 -> location = new River(player);
                    case 4 -> location = new Cave(player);
                    case 5 -> location = new Forest(player);
                    case 6 -> location = new Mine(player);

                    default -> System.out.println("Please select a valid region!");
                }

            } while (secim < 0 || secim > 6);

            if(location == null){
                System.out.println("Game is ending...");
                Game.loading();
                break;
            }

            if (!location.onLocation()) {
                System.out.println("***********************GAME OVER***********************");
                break;
            }

        }
    }

    public boolean winCondition(){
        if(player.getInventory().isWater() && player.getInventory().isFood() && player.getInventory().isFirewood()){
            System.out.println("~~~~~~ CONGRATULATIONS ~~~~~~");
            System.out.println("You have successfully completed the game!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return false;
        }

        return true;
    }
    public static void loading() {
        for (int i = 0; i < 16; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("---");
        }
        System.out.println();
    }
    public static void battle(){
        for (int i = 0; i < 16; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("XXX");
        }
        System.out.println();
    }
    public static int coinToss(){
        Random rnd = new Random();
        return rnd.nextInt(2);
    }
}
