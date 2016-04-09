package testing;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import engine.ISpriteManager;
import engine.SpriteManager;
import engine.modules.PathMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import util.Coordinate;
import util.TimeDuration;


public class TestSpriteManager {

    private ISpriteManager mySpriteManager;

    @Before
    public void setUp () {
        mySpriteManager = new SpriteManager();
    }

    @Test
    public void updateAllSprites () {
        for (int i = 0; i < 10; i++) {
            mySpriteManager.add(createMovementSprite(), new Coordinate(10, 10));
        }
        mySpriteManager.update(new TimeDuration(100));
        mySpriteManager.getSprites().forEach(sprite -> testSprite(sprite.get()));
    }

    private void testSprite (ISprite sprite) {
        assert (sprite.getLocation().get().getX() != 10 && sprite.getLocation().get().getY() != 10);
    }

    private ISprite createMovementSprite () {
        ISprite mySprite = new Sprite();
        mySprite.getLocation().set(new Coordinate(10, 10));
        List<Coordinate> path = new ArrayList<>();
        PathMover mover = new PathMover(1, path, mySprite);
        mySprite.getMovementStrategyProperty().set(mover);
        return mySprite;

    }

}
