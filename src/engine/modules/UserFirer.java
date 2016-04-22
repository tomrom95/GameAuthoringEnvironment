package engine.modules;

import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Key;


/**
 * This class provides the behavior necessary to implement a user controlled fire module in the
 * game.
 *
 * @author Dhrumil
 *          This class looks complete, but it is extremely far from complete, do not even bother
 *         evaluating the code, it is very far from functional
 */
public class UserFirer extends Firer {

 

	private Key myFireKey;
    private List<SpriteDefinition> myProjectileList;
    private SpriteDefinition myProjectile;
    private IAttribute myAmmo;
    private IGame myGame;

    public UserFirer (Positionable parent, SpriteDefinition fireSprite, Key fireKey, IGame game, double ammo) {
    	super(parent);
        myFireKey = fireKey;
        myProjectile = fireSprite;
        myGame = game;
        myAmmo = new Attribute(ammo, AttributeType.AMMO);
    }

    @Override
    public void applyEffect (IEffect effect) {
        getAmmo().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        if (keyEvent.getType() == InputType.KEY_PRESSED &&
            keyEvent.getKey().isEqual(myFireKey)) {
            registerKeyPress(keyEvent.getKey());
        }
    }

    private void registerKeyPress (Key fire) {

        myProjectile.create();
    }

    /*
     * needs to be methods to create the X and Y velocity
     * (non-Javadoc)
     *
     * @see engine.modules.Firer#getAttributes()
     */
    @Override
    public ObservableList<IAttribute> getAttributes () {
        ObservableList<IAttribute> attributeList = FXCollections.observableArrayList();
        attributeList.add(myAmmo);
        return attributeList;
    }

	@Override
	protected List<IAttribute> getSpecificAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

}
