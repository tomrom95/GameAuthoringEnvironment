package testing;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import engine.modules.IMovementModule;
import engine.modules.UserMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.ControlKeys;
import util.Coordinate;
import util.Key;
import util.TimeDuration;


/**
 *
 * @author Dhrumil and Jeremy
 *
 *         jes85 and drp18
 *
 */

public class TestSprite {

    private ISprite sprite = new Sprite(new SpriteType("Test"));

    @Before
    public void setUp () {

        ControlKeys keys =
                new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        ObjectProperty<IMovementModule> mover =
                new SimpleObjectProperty<>(new UserMover(10, keys, sprite));

        try {
            Method goRight = mover.get().getClass().getDeclaredMethod("goRight");
            goRight.setAccessible(true);
            goRight.invoke(mover.get());
        }
        catch (Exception e) {
            assert (false);
            return;
        }

        TimeDuration time = new TimeDuration(100.0);
        List<Coordinate> newPath = getListOfCoordinates();
        sprite.getMovementStrategy().setPath(newPath);
        mover.get().update(time);

    }

    private static List<Coordinate> getListOfCoordinates () {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(100, 100));
        list.add(new Coordinate(20, 20));
        list.add(new Coordinate(200, 2));
        list.add(new Coordinate(500, 30));
        return list;
    }

    @Test
    public void test () {
        Coordinate myCoordinate = new Coordinate(1000.0, 0.0);

        assertEquals("XPos of Sprite", myCoordinate.getX(), sprite.getLocation().getX(), 0);
        assertEquals("YPos of Sprite", myCoordinate.getY(), sprite.getLocation().getY(), 0);
    }

}
