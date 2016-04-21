package engine;

import util.BitMap;
import util.TimeDuration;


public class PlaceableManager implements IPlaceableManager {

    IGame myGame;

    public PlaceableManager (IGame game) {
        myGame = game;
    }

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public BitMap getPlaceableArea () {
        // TODO Auto-generated method stub
        return null;
    }

}
