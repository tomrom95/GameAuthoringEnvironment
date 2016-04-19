package engine;

import engine.effects.DefaultAffectable;
import util.BitMap;
import util.TimeDuration;


public class ObstructionManager  implements IObstructionManager {
    IGame myGame;

    ObstructionManager (IGame game) {
        myGame = game;
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO This method will actually generate the bitmaps to return

    }

    @Override
    public BitMap getObstructionMap () {
        // TODO this method will return the generated bitmaps to avoid duplicating
        // calculations
        return null;
    }

}
