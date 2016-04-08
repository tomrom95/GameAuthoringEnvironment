package engine;

import static org.junit.Assert.*;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modules.IMovementModule;
import modules.UserControlledMover;
import util.ControlKeys;
import util.Coordinate;
import util.Key;
import util.TimeDuration;

/**
 * 
 * @author Dhrumil and Jeremy
 * 
 * jes85 and drp18
 *
 */

public class TestSprite {
    
    private ISprite sprite = new Sprite();
    

    @Before
    public void setUp () {
        
        ControlKeys keys = new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        ObjectProperty<IMovementModule> mover = new SimpleObjectProperty<>(new UserControlledMover(10, keys, sprite));
        
        try {
            Method goRight = mover.get().getClass().getDeclaredMethod("goRight");
            goRight.setAccessible(true);
            goRight.invoke(mover.get());
        }
        catch (Exception e) {
            assert(false);
            return;
        }     
        
        TimeDuration time = new TimeDuration(100.0);
        sprite.getMovementStrategyProperty().set(mover.get());
        mover.get().update(time);
        
    }

    @Test
    public void test () {
        Coordinate myCoordinate = new Coordinate(1000.0, 0.0);
        
        assertEquals("XPos of Sprite", myCoordinate.getX(), sprite.getLocation().get().getX(), 0);
        assertEquals("YPos of Sprite", myCoordinate.getY(), sprite.getLocation().get().getY(), 0);
    }

}
