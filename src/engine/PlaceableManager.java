package engine;

import java.util.List;
import engine.sprite.ISprite;
import util.BitMap;
import util.TimeDuration;


public class PlaceableManager implements IPlaceableManager {

    private ILevel myLevel;
    private BitMap myCurrentPlaceableMap;

    public PlaceableManager (ILevel level) {
        myLevel = level;
        myCurrentPlaceableMap = getBitMapForCurrentLevel(getLevel());
    }

    private BitMap getBitMapForCurrentLevel (ILevel level) {
        // TODO: Return the bitmap saved from authoring environment
        // TODO: DEFAULT width and height
        return new BitMap(800, 1000);
    }

    private ILevel getLevel () {
        return myLevel;
    }

    @Override
    public void update (TimeDuration duration) {
        myCurrentPlaceableMap = parseCurrentLevelForPlaceable(getLevel());
    }

    private BitMap parseCurrentLevelForPlaceable (ILevel level) {
        BitMap placeableMap = getBitMapForCurrentLevel(level);

        // Check all the sprites and update the bitmap
        List<ISprite> listOfSprites = level.getSprites();
        for (ISprite sprite : listOfSprites) {
            for (int row = (int) sprite.getBounds().getTop(); row <= (int) sprite.getBounds()
                    .getBottom(); row++) {
                for (int col = (int) sprite.getBounds().getLeft(); col <= (int) sprite.getBounds()
                        .getRight(); col++) {
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
