package engine;

import util.BitMap;
import util.TimeDuration;


public class PlaceableManager implements IPlaceableManager {

    private IGame myGame;
    private BitMap myCurrentPlaceableMap;
    
    public PlaceableManager (IGame game) {
        myGame = game;
        myCurrentPlaceableMap = createBitMapForCurrentGame(getGame());
    }
    
    private BitMap createBitMapForCurrentGame(IGame game){
        int gameWidth = game.getGameGridConfig().getGridWidth();
        int gameHeight = game.getGameGridConfig().getGridHeight();
        return new BitMap(gameWidth, gameHeight);
    }
    
    private IGame getGame() {
        return myGame;
    }

    @Override
    public void update (TimeDuration duration) {
        myCurrentPlaceableMap = parseCurrentGameForPlaceable(getGame());
    }
    
    private BitMap parseCurrentGameForPlaceable(IGame game){
        
        return myCurrentPlaceableMap;
    }

    @Override
    public BitMap getPlaceableArea () {
        return myCurrentPlaceableMap;
    }

}
