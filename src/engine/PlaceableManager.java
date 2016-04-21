package engine;

import java.util.List;
import engine.sprite.ISprite;
import util.BitMap;
import util.TimeDuration;


public class PlaceableManager implements IPlaceableManager {

    private IGame myGame;
    private BitMap myCurrentPlaceableMap;

    public PlaceableManager (IGame game) {
        myGame = game;
        myCurrentPlaceableMap = getBitMapForCurrentGame(getGame());
    }

    private BitMap getBitMapForCurrentGame (IGame game) {
        int gameWidth = game.getGameGridConfig().getGridWidth();
        int gameHeight = game.getGameGridConfig().getGridHeight();
        return new BitMap(gameWidth, gameHeight);
    }

    private IGame getGame () {
        return myGame;
    }

    @Override
    public void update (TimeDuration duration) {
        myCurrentPlaceableMap = parseCurrentGameForPlaceable(getGame());
    }

    private BitMap parseCurrentGameForPlaceable(IGame game){
        BitMap placeableMap = getBitMapForCurrentGame(game);
        
        //Check all the sprites and update the bitmap
        List<ISprite> listOfSprites = game.getLevelManager().getCurrentLevel().getSprites();
        for(ISprite sprite: listOfSprites){
            for(int row = (int)sprite.getBounds().getTop(); row < (int)sprite.getBounds().getBottom(); row++) {
                for(int col = (int)sprite.getBounds().getLeft(); col < (int)sprite.getBounds().getRight(); col++){
                    placeableMap.set(row, col, true);
                }
            }
        }
        return placeableMap;
    }

    @Override
    public BitMap getPlaceableArea () {
        return myCurrentPlaceableMap;
    }

}
