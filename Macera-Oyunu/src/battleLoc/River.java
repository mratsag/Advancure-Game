package battleLoc;

import game.Player;

public class River extends BattleLoc {
    public River(Player player) {
        super(player, "River", new Bear(), "Water", 2);
    }
}
