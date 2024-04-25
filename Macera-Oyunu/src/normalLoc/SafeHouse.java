package normalLoc;

import game.Game;
import game.Location;
import game.Player;

public class SafeHouse extends NormalLoc {

    public SafeHouse(Player p) {
        super(p, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("*                  SAFE HOUSE                  *");
        System.out.println("Your health is regenerating...");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        Game.loading();
        System.out.println("Done!");

        return true;
    }
}
