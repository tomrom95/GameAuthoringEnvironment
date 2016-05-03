package testing;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import engine.ISpriteManager;
import engine.SpriteManager;
import engine.modules.PathMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import util.Coordinate;
import util.TimeDuration;


public class TestSpriteManager {

    private ISpriteManager mySpriteManager;

    /**
     * Once again this class in order to be useful will need to be refactord to
     * use the
     * {@link Sprite#initialize(engine.modules.IMovementModule, engine.modules.IGraphicModule, List, List, Coordinate)
     * initialize}
     * method in order to actually put the required submodules to test into the sprite
     *
     * Running this code as is should result in some null pointer exceptions as calls go to
     * non-existant objects
     */
    @Before
    public void setUp () {
        mySpriteManager = new SpriteManager();
    }

    @Test
    public void updateAllSprites () {
        for (int i = 0; i < 10; i++) {
            mySpriteManager.bufferedAdd(createMovementSprite(), new Coordinate(10, 10));
        }
        mySpriteManager.update(new TimeDuration(100));
        mySpriteManager.getSprites().forEach(sprite -> testSprite(sprite));
    }

    private void testSprite (ISprite sprite) {
        assert (sprite.getLocation().getX() != 10 && sprite.getLocation().getY() != 10);
    }

    private ISprite createMovementSprite () {
        ISprite mySprite = new Sprite(new SpriteType("UserSprite"));
        mySprite.setLocation(new Coordinate(10, 10));
        List<Coordinate> path = new ArrayList<>();
        new PathMover(1, path, mySprite);
        return mySprite;

    }

}
