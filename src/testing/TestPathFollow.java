package testing;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import engine.modules.PathFollowMover;
import engine.sprite.Sprite;
import util.Coordinate;
import util.TimeDuration;


public class TestPathFollow {

    private Sprite mySprite;

    @Before
    public void setUp () {
        mySprite = new Sprite();
    }

    @Test
    public void testDontMove () {

        mySprite.getLocation().set(new Coordinate(10, 10));
        List<Coordinate> path = new ArrayList<>();
        PathFollowMover mover = new PathFollowMover(1, path, mySprite);
        mySprite.getMovementStrategyProperty().set(mover);
        Coordinate beforeMove = mySprite.getLocation().get();
        mySprite.update(new TimeDuration(100));
        Coordinate afterMove = mySprite.getLocation().get();
        assertEquals("Should not has moved", beforeMove.getX(), afterMove.getX(), .001);
        assertEquals("Should not has moved", beforeMove.getY(), afterMove.getY(), .001);

    }

    @Test
    public void testInitialMovement () {

        mySprite.getLocation().set(new Coordinate(10, 10));
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(11, 11));
        PathFollowMover mover = new PathFollowMover(1, path, mySprite);
        mySprite.getMovementStrategyProperty().set(mover);
        Coordinate beforeMove = mySprite.getLocation().get();
        mySprite.update(new TimeDuration(100));
        Coordinate afterMove = mySprite.getLocation().get();
        assert(afterMove.getX() > beforeMove.getX());
        assert(afterMove.getY() > beforeMove.getY());

    }

}
