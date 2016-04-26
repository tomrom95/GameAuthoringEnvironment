package engine;

import engine.sprite.ISprite;
import util.AutoTrueBitMap;
import util.BitMap;
import util.Bounds;
import util.IBitMap;
import util.TimeDuration;

/**
 * This class will loop through the sprites each in the current running level each
 * update cycle in the game and will use their bounds to create a {@link BitMap} of
 * the current places on the board that are 'obstructed'
 * @author jonathanim
 *
 */
public class ObstructionManager implements IObstructionManager {
    private static final boolean POSITION_OBSTRUCTED = true;
    private IGame myGame;
    private IBitMap myCurrentObstructionMap;

    ObstructionManager (IGame game) {
        myGame = game;
        myCurrentObstructionMap = getBitMapSizedForCurrentGame(getGame());
    }

    @Override
    public void update (TimeDuration duration) {
        myCurrentObstructionMap = parseCurrentGameForObstructions(getGame());
    }
    
    @Override
    public IBitMap getObstructionMap () {
        return myCurrentObstructionMap;
    }

    private IBitMap parseCurrentGameForObstructions (IGame game) {
        IBitMap obstructionMap = getBitMapSizedForCurrentGame(game);
        game.getLevelManager().getCurrentLevel().getSprites().stream()
                .forEach(sprite -> ifObstructsMarkSprite(obstructionMap, sprite));
        return obstructionMap;
    }

    private void ifObstructsMarkSprite (IBitMap map, ISprite sprite) {
        if (sprite.doesObstruct()) {
            markSpriteOnMap(map, sprite);
        }
    }

    private void markSpriteOnMap (IBitMap map, ISprite sprite) {
        Bounds bound = sprite.getBounds();
        int leftX = (int) Math.round(bound.getLeft());
        int rightX = (int) Math.round(bound.getRight());
        int topY = (int) Math.round(bound.getTop());
        int botY = (int) Math.round(bound.getBottom());
        for (int i = leftX; i <= rightX; i++) {
            for (int j = topY; j <= botY; j++) {
                map.set(i, j, POSITION_OBSTRUCTED);
            }
        }
    }

    private IGame getGame () {
        return myGame;
    }

    private IBitMap getBitMapSizedForCurrentGame (IGame game) {
        int gameWidth = game.getGameGridConfig().getGridWidth();
        int gameHeight = game.getGameGridConfig().getGridHeight();
        return new AutoTrueBitMap(gameWidth, gameHeight);
    }

}