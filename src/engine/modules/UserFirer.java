package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAdder;
import engine.IAttribute;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.sprite.ISprite;
import util.Key;

/**
 * This class provides the behavior necessary to implement a user controlled fire module in the
 * game.
 *
 * @author Dhrumil
 * 
 *
 */
public class UserFirer extends Firer {

    private Key myFireKey;
    private IAdder myAdder;
    private List<SpriteDefinition> myProjectileList;
    private SpriteDefinition myProjectile;
    private IAttribute myAmmo;

    public UserFirer (SpriteDefinition fireSprite, Key fireKey, IAdder adder, double ammo) {
        
        myFireKey = fireKey;
        myProjectile = fireSprite;
        myAdder = adder;
        myAmmo = new Attribute(ammo, AttributeType.AMMO);

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
        
        ISprite bullet = myProjectile.create();
        myAdder.add(bullet, bullet.getLocation());

    }


    @Override
    public List<IAttribute> getAttributes () {
        List<IAttribute> attributeList = new ArrayList<>();
        attributeList.add(myAmmo);
        return attributeList;
    }

}
