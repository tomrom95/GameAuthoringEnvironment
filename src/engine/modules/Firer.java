// This entire file is part of my masterpiece.
// Joseph Timko

/*
 *The purpose of this class is to function as a superclass for all the specific firing modules that can be made in the game.  The reason 
 *I chose to include this class in my masterpiece is because I believe it demonstrates a knowledge of good design specifically with respect to
 *inheritance, and functional programming.  
 *
 *This class prevents the developer from needs to repeatedly define old functionality when they wish
 *to add another type of firing module to the code.  This class enables extensibility, as it quite easy to add another firing module with new functionality, 
 *as the developer gets much of the firing module functionality such as wait time, ranged firing, and enemy tracking from extending this class.  I was particularly 
 *of consolidating all the ranged firing functionality within this class.  I was able to do so using exclusively Java streams to make the code readable and quick to write.  
 *I believe this to be a good use of Template design pattern, and this implementation
 * saved a great deal of repeated code, created the functionality across all firing modules, and made the front end implementation dramatically easier!  The use of 
 * this class as a template is especially usefully when looking at firing modules as DefaultAffectables.  I was able to encapsulate all the generically affectable
 * attributes of all the firing modules I created into this class.  The current firing modules do not add any attributes to the ObservableList currently, but if 
 * a developer made a new firing module and needed to add additional attributes, that would be easily done in the getSpecificAttributes method.   
 * 
 *
 *The final reason I chose to make firing generally as my masterpiece is because
 *it is a key piece of functionality for Tower Defense games and the firing modules I built functioned extremely well.  Using this superclass within
 *our plug-and-play module framework, Sprites could have any number of Directional (fires consistently in one direction), User-Controlled, or Tracking
 *(fires at specified enemies) that could fire any user-defined missile sprites, and all of they could have interval timers and range applied to them.
 *  
 */
package engine.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.effects.DefaultAffectable;
import engine.sprite.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * This abstract class handles and manages actions and variables that are consistent across all types of firing modules.
 * 
 * @author josephtimko1
 *
 */
public abstract class Firer extends DefaultAffectable implements IFireModule {

    private IAttribute myAmmo;
    private boolean myRanged;
    private IGame myGame;
    private IAttribute myRange;
    private IAttribute myWaitTime;
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
        myRanged = false;
        myFiredSprites = new ArrayList<ISprite>();
        myTracker = new EnemyTracker();
        myWaitTime = new Attribute(AttributeType.FIRE_RATE);
        myGame = game;
    }

    private void removeSpritesBeyondRange () {

        getSpritesBeyondRange().stream()
                .forEach(p -> myGame.getLevelManager().getCurrentLevel().remove(p));
        getSpritesBeyondRange().stream().forEach(p -> myFiredSprites.remove(p));
    }

    private List<ISprite> getSpritesBeyondRange () {

        return myFiredSprites.stream().filter(isOutOfRange())
                .collect(Collectors.<ISprite>toList());
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        ObservableList<IAttribute> attributes = FXCollections.observableArrayList();
        attributes.add(myAmmo);
        attributes.add(myRange);
        attributes.add(myWaitTime);
        attributes.addAll(getSpecificAttributes());
        return attributes;

    }
    
    protected abstract List<IAttribute> getSpecificAttributes ();


    /**
     * This method is called in all the subclasses to handle ranged firing if the user 
     * has specified that a module should have a range.  
     */
    @Override
    public void update (TimeDuration duration) {

        if (myRanged) {
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
        return myRanged;
    }

    public void setRanged (boolean isRanged) {
        myRanged = isRanged;
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

    public IAttribute getMyWaitTime () {
        return myWaitTime;
    }

    public void setMyWaitTime (double waittime) {
        this.myWaitTime.setValue(waittime);
    }

}
