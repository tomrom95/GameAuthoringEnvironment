package engine.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This class creates a firer that handles and manages actions involving a creating an object to
 * fire
 *
 */
public abstract class Firer extends DefaultAffectable implements IFireModule {

    private IAttribute myAmmo;
    private boolean ranged;
    private IGame myGame;
    private IAttribute myRange;
    private Positionable myParent;
    private List<ISprite> myFiredSprites;
    private EnemyTracker myTracker;

    private Predicate<ISprite> isOutOfRange () {
        return p -> myTracker.calculateDistance(myParent.getLocation(), p.getLocation()) >= myRange
                .getValueProperty().get();
    }

    public Firer (IGame game, Positionable parent) {
        myParent = parent;
        myAmmo = new Attribute(AttributeType.AMMO);
        myRange = new Attribute(AttributeType.FIRE_RANGE);
        ranged = false;
        myFiredSprites = new ArrayList<ISprite>();
        myTracker = new EnemyTracker();
        myGame = game;
    }

    private void removeSpritesBeyondRange () {

        getSpritesBeyondRange().stream()
                .forEach(p -> myGame.getLevelManager().getCurrentLevel().remove(p));
        getSpritesBeyondRange().stream().forEach(p -> myFiredSprites.remove(p));
    }

    private List<ISprite> getSpritesBeyondRange () {

        return myFiredSprites.stream().filter(isOutOfRange())
                .collect(Collectors.<ISprite> toList());
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        ObservableList<IAttribute> attributes = FXCollections.observableArrayList();
        attributes.add(myAmmo);
        attributes.add(myRange);
        attributes.addAll(getSpecificAttributes());
        return attributes;

    }

    protected abstract List<IAttribute> getSpecificAttributes ();

    @Override
    public void update (TimeDuration duration) {

        if (ranged) {
            removeSpritesBeyondRange();
        }
    }

    public void setAmmo (double ammo) {
        myAmmo.setValue(ammo);
    }

    public IAttribute getAmmo () {
        return myAmmo;
    }

    public boolean getRanged () {
        return ranged;
    }

    public void setRanged (boolean isRanged) {
        ranged = isRanged;
    }

    public IAttribute getRange () {
        return myRange;
    }

    public void setRange (double range) {
        myRange.setValue(range);
    }

    protected List<ISprite> getFiredSprites () {
        return myFiredSprites;
    }

    protected EnemyTracker getTracker () {
        return myTracker;
    }

    public IGame getGame () {
        return myGame;
    }

    public void setGame (IGame game) {
        myGame = game;
    }

}
