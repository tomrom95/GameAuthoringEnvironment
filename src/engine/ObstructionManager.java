package engine;

import util.BitMap;
import util.TimeDuration;


public class ObstructionManager implements IObstructionManager {
    private IGame myGame;
    private BitMap myCurrentObstructionMap;

    ObstructionManager (IGame game) {
        myGame = game;
        myCurrentObstructionMap = getBitMapSizedForCurrentGame(getGame());
    }

    @Override
    public void update (TimeDuration duration) {
        myCurrentObstructionMap = parseCurrentGameForObstructions(getGame());
    }

    private BitMap parseCurrentGameForObstructions (IGame game) {
        //TODO implement parse step for sprites and bounds boxes into internal bitmap
        return getBitMapSizedForCurrentGame(game);
    }

    @Override
    public BitMap getObstructionMap () {
        return myCurrentObstructionMap;
    }

    private IGame getGame () {
        return myGame;
    }

    private BitMap getBitMapSizedForCurrentGame (IGame game) {
        int gameWidth = game.getGameGridConfig().getGridWidth();
        int gameHeight = game.getGameGridConfig().getGridHeight();
        return new BitMap(gameWidth, gameHeight);
    }

}
