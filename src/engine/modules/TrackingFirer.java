package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Key;
import util.TimeDuration;

/**
 * Locates the nearest enemy and fires 
 * @author RyanStPierre
 *
 */
public class TrackingFirer extends Firer {

    private List<ISprite> myTargets; 
    private Key myFireKey;
    
    public TrackingFirer (List<ISprite> targets, Key fire) {
        myTargets = targets;
        myFireKey = fire;
    }
    
    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applyEffect (IEffect effect) {
        getAmmo().get().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        
        if (keyEvent.getType() == InputType.KEY_PRESSED &&
            keyEvent.getKey().isEqual(myFireKey)) {
            registerKeyPress(keyEvent.getKey());
        }

    }

    private void registerKeyPress (Key fire) {
        System.out.println("FIRE");
        ISprite bullet = myProjectile.create();
        myAdder.add(bullet, bullet.getLocation());

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // do nothing

    }

    @Override
    public List<IAttribute> getAttributes () {
        List<IAttribute> attributeList = new ArrayList<>();
        attributeList.add(myAmmo);
        return attributeList;
    }
}
