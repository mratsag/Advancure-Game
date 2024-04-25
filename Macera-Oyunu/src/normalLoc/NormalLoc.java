package normalLoc;

import game.Location;
import game.Player;

public abstract class NormalLoc extends Location {
    public NormalLoc(Player player, String name) {
        super(player, name);
    }
}
