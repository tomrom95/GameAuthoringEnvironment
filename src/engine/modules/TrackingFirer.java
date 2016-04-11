package engine.modules;

import java.util.List;
import engine.sprite.ISprite;


/**
 * Locates the nearest enemy and fires
 * 
 * @author RyanStPierre
 *
 */
public class TrackingFirer extends Firer {

    private List<ISprite> myTargets;

    public TrackingFirer (List<ISprite> targets) {
        myTargets = targets;
    }
}
