package engine.modules;

import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAdder;
import engine.IAttribute;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Key;
import util.TimeDuration;


/**
 * This class provides the behavior necessary to implement a user controlled fire module in the
 * game.
 * 
 * @author Dhrumil
 *
 */
public class UserFirer extends Shooter {

    private Key myFireKey;
    private IAdder myAdder;
    private List<SpriteDefinition> myProjectileList;
    private SpriteDefinition myProjectile;
    private ObjectProperty<IAttribute> myAmmo;

    public UserFirer (SpriteDefinition fireSprite, Key fireKey, IAdder adder, double ammo) {

        myFireKey = fireKey;
        myProjectile = fireSprite;
        myAdder = adder;
        myAmmo = new SimpleObjectProperty<>(new Attribute(ammo, AttributeType.AMMO));

    }

    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applyEffect (IEffect effect) {
        // TODO Auto-generated method stub

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
        myAdder.add(bullet, bullet.getLocation().get());

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        ObservableList<ObjectProperty<IAttribute>> attributeList =
                FXCollections.observableArrayList();
        attributeList.add(myAmmo);
        return attributeList;
    }
}
