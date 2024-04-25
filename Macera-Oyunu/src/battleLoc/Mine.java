package battleLoc;

import game.Player;

public class Mine extends BattleLoc{
    public Mine(Player player) {
        super(player, "Mine", new Snake(), "Nothing", 5);
    }
}
