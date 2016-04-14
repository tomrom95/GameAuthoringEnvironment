package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.sprite.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Key;


/**
 * This class provides the behavior necessary to implement a user controlled fire module in the
 * game.
 *
 * @author Dhrumil
 *
 */
public class UserFirer extends Firer {

    private Key myFireKey;
    private List<SpriteDefinition> myProjectileList;
    private SpriteDefinition myProjectile;
    private IAttribute myAmmo;
    private IGame myGame;

    public UserFirer (SpriteDefinition fireSprite, Key fireKey, IGame game, double ammo) {

        myFireKey = fireKey;
        myProjectile = fireSprite;
        myGame = game;
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
       /*
        * this is fucked
        */

    }
    
    
    /*
     * needs to be methods to create the X and Y velocity
     * (non-Javadoc)
     * @see engine.modules.Firer#getAttributes()
     */
    @Override
    public ObservableList<IAttribute> getAttributes () {
        ObservableList<IAttribute> attributeList = FXCollections.observableArrayList();
        attributeList.add(myAmmo);
        return attributeList;
    }

}
