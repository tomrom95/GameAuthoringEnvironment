package testing.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import engine.modules.PathMover;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import util.Coordinate;
import util.TimeDuration;


public class TestPathFollow {

    private Sprite mySprite;

    /**
     * These tests will not work because they need to use definitions or
     * a call to the {@link Sprite#initialize(engine.modules.IMovementModule, engine.modules.IGraphicModule, List, List, Coordinate) initialize}
     * method in order to properly set the movement submodule
     * 
     *  Not fixing this to prioritize core feature development
     */
    @Before
    public void setUp () {
        mySprite = new Sprite(new SpriteType("UserSprite"));
        //mySprite.initialize(new pathMover, graphicModule, otherModules, attributes, coord);
        
    }

    @Test
    public void testDontMove () {
        mySprite.setLocation(new Coordinate(10, 10));
        List<Coordinate> path = new ArrayList<>();
        PathMover mover = new PathMover(1, path, mySprite);
        Coordinate beforeMove = mySprite.getLocation();
        mySprite.update(new TimeDuration(100));
        Coordinate afterMove = mySprite.getLocation();
        assertEquals("Should not has moved", beforeMove.getX(), afterMove.getX(), .001);
        assertEquals("Should not has moved", beforeMove.getY(), afterMove.getY(), .001);

    }

    @Test
    public void testInitialMovement () {

        mySprite.setLocation(new Coordinate(10, 10));
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(11, 11));
        PathMover mover = new PathMover(1, path, mySprite);
        //mySprite.getMovementStrategyProperty().set(mover);
        Coordinate beforeMove = mySprite.getLocation();
        mySprite.update(new TimeDuration(100));
        Coordinate afterMove = mySprite.getLocation();
        assert(afterMove.getX() > beforeMove.getX());
        assert(afterMove.getY() > beforeMove.getY());

    }

}
