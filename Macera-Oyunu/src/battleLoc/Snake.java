package battleLoc;

import java.util.Random;

import static game.Location.random;

public class Snake extends Obstacle{

    public Snake() {
        super("Snake", 4, random.nextInt(4) + 3, 12, 0);
    }
}
