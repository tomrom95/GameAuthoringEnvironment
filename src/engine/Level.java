package engine;

import java.util.List;
import engine.conditions.ICondition;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class represents the notion of a level in an IGame and holds the interfaces required to
 * properly entail a level.
 * This includes a condition, attribute, and sprite manager to hold the rules, objects and
 * interactions for the level.
 *
 */
public class Level implements ILevel {

    private IConditionManager myConditionManager;
    private ImageGraphic myBackgroundImage;
    private ISpriteManager mySpriteManager;
    private IAttributeManager myAttributeManager;
    private INextLevelManager myNextLevelManager;

    public Level () {
        // TODO need to actually instantiate internal manager objects
        // after creating the concrete classes

        myAttributeManager = new AttributeManager();
        myConditionManager = new ConditionManager();
        mySpriteManager = new SpriteManager();
        myNextLevelManager = new NextLevelManager();
        // TODO store these defaults in properties file
        myBackgroundImage = new ImageGraphic(400, 400,"/images/blank.jpg");
    }

    @Override
    public void update (TimeDuration duration) {
        mySpriteManager.update(duration);
        myConditionManager.update(duration);
        myAttributeManager.update(duration);
        myNextLevelManager.update(duration);
    }

    @Override
    public ObservableList<ICondition> getConditionsListProperty () {
        return myConditionManager.getConditionListProperty();
    }

    @Override
    public void addGlobalResource (IResource resource) {
        myAttributeManager.addResource(resource);
    }

    @Override
    public List<ISprite> getSprites () {
        return mySpriteManager.getSprites();
    }

    @Override
    public void bufferedAdd (ISprite sprite, Coordinate coordinate) {
        mySpriteManager.bufferedAdd(sprite, coordinate);
    }

    @Override
    public ILevel getNextLevel () {
        return myNextLevelManager.getNextLevel();
    }

    @Override
    public boolean shouldSwitchLevel () {
        return myNextLevelManager.shouldGoToNextLevel();
    }

    @Override
    public List<? extends Drawable> getDrawables () {
        return mySpriteManager.getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        mySpriteManager.internalizeKeyEvents(list);
        myConditionManager.internalizeKeyEvents(list);

    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        mySpriteManager.internalizeMouseEvents(list);
        myConditionManager.internalizeMouseEvents(list);
    }

    /**
     * Removes a sprite from the level whenever a sprite meets a particular death condition
     */
    @Override
    public void remove (ISprite sprite) {
        mySpriteManager.remove(sprite);
    }

    @Override
    public ImageGraphic getBackgroundImage () {
        return myBackgroundImage;
    }

    @Override
    public IAttributeManager getAttributeManager () {
        return myAttributeManager;
    }

    @Override
    public void bufferedAdd (ISprite sprite) {
        mySpriteManager.bufferedAdd(sprite);
    }

    @Override
    public void setBackgroundImage (ImageGraphic graphic) {
        myBackgroundImage = graphic;
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        mySpriteManager.add(sprite, coordinate);
    }

}
