package engine;

import engine.sprite.ISprite;
import util.BitMap;
import util.Bounds;
import util.ISampledBitMap;
import util.SampledBitMap;
import util.TimeDuration;


/**
 * This class will loop through the sprites each in the current running level each
 * update cycle in the game and will use their bounds to create a {@link BitMap} of
 * the current places on the board that are 'obstructed'
 *
 * @author jonathanim
 *
 */
public class ObstructionManager implements IObstructionManager {

    public static final int SAMPLE_RESOLUTION = 60;
    public static final double SAMPLE_DOUBLE = SAMPLE_RESOLUTION;

    private static final boolean POSITION_OBSTRUCTED = true;
    private IGame myGame;

    ObstructionManager (IGame game) {
        myGame = game;
    }

    @Override
    public void update (TimeDuration duration) {
        // Do nothing
    }

    @Override
    public ISampledBitMap getObstructionMap () {
        return parseCurrentGameForObstructions(getGame());
    }

    private ISampledBitMap parseCurrentGameForObstructions (IGame game) {
        ISampledBitMap obstructionMap = getBitMapSizedForCurrentGame(game);
        game.getLevelManager().getCurrentLevel().getSprites()
                .stream()
                .forEach(sprite -> ifObstructsMarkSprite(obstructionMap, sprite));
        return obstructionMap;
    }

    private void ifObstructsMarkSprite (ISampledBitMap map, ISprite sprite) {
        if (sprite.doesObstruct()) {
            markSpriteOnMap(map, sprite);
        }
    }

    private void markSpriteOnMap (ISampledBitMap map, ISprite sprite) {
        Bounds bound = sprite.getBounds();

        int leftX = (int) (sprite.getLocation().getX() / SAMPLE_RESOLUTION);
        int rightX = (int) ((sprite.getLocation().getX() + bound.getMyWidth()) / SAMPLE_RESOLUTION);
        int topY = (int) (sprite.getLocation().getY() / SAMPLE_RESOLUTION);
        int botY = (int) ((sprite.getLocation().getY() + bound.getMyHeight()) / SAMPLE_RESOLUTION);
        for (int i = leftX; i <= rightX; i++) {
            for (int j = topY; j <= botY; j++) {
                map.set(i, j, POSITION_OBSTRUCTED);
            }
        }
    }

    private IGame getGame () {
        return myGame;
    }

    private ISampledBitMap getBitMapSizedForCurrentGame (IGame game) {
        double realWidth = game.getLevelBounds().getWidth();
        double realHeight = game.getLevelBounds().getHeight();
        int xOffset = Math.floor(realWidth / SAMPLE_DOUBLE) == realWidth / SAMPLE_DOUBLE ? 0 : 1;
        int yOffset = Math.floor(realHeight / SAMPLE_DOUBLE) == realHeight / SAMPLE_DOUBLE ? 0 : 1;

        int gameWidth = (int) game.getLevelBounds().getWidth();
        // game.getLevelManager().getCurrentLevel().getBackgroundImageWidth();
        int gameHeight = (int) game.getLevelBounds().getHeight();
        // game.getLevelManager().getCurrentLevel().getBackgroundImageHeight();
        return new SampledBitMap(gameHeight / SAMPLE_RESOLUTION + yOffset,
                                 gameWidth / SAMPLE_RESOLUTION + xOffset, gameHeight, gameWidth);
    }

}
