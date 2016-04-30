package engine;

import java.util.List;
import java.util.function.Consumer;
import engine.conditions.ICondition;
import engine.definitions.concrete.SpriteDefinition;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.ISprite;
import engine.waves.IWaveSet;
import engine.waves.WaveSet;
import graphics.ImageGraphic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BackgroundImage;
import util.Bounds;
import util.Coordinate;
import util.Tile;
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
    private double myBackgroundImageWidth;
    private double myBackgroundImageHeight;
    private ISpriteManager mySpriteManager;
    private IAttributeManager myAttributeManager;
    private INextLevelManager myNextLevelManager;
    private PlaceableTileManager myPlaceableTileManager;
    private IWaveSetManager myWaveSetManager;
    private IProfile myProfile;
    private ObservableList<SpriteDefinition> myAddableSprites;
    private Bounds myBounds;

    public Level () {
        // TODO need to actually instantiate internal manager objects
        // after creating the concrete classes
        myProfile = new Profile();
        myAttributeManager = new AttributeManager();
        myConditionManager = new ConditionManager();
        mySpriteManager = new SpriteManager();
        myNextLevelManager = new NextLevelManager();
        // TODO store these defaults in properties file
        myBackgroundImage = new ImageGraphic(400, 400, "/images/blank.jpg");
        myWaveSetManager = new WaveSetManager();
        myPlaceableTileManager = new PlaceableTileManager(this,0,0);
        myAddableSprites = FXCollections.observableArrayList();
        myBounds = new Bounds(myBackgroundImageWidth, myBackgroundImageHeight);
    }

    @Override
    public void update (TimeDuration duration) {
        myWaveSetManager.update(duration);
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

    private void applyToEventInternalizers (Consumer<IEventInternalizer> internalizer) {
        internalizer.accept(mySpriteManager);
        internalizer.accept(myConditionManager);
        internalizer.accept(myNextLevelManager);
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        applyToEventInternalizers(internalizer -> internalizer.internalizeKeyEvents(list));
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        applyToEventInternalizers(internalizer -> internalizer.internalizeMouseEvents(list));
    }

    @Override
    public void internalizeGameEvents (List<GameEvent> list) {
        applyToEventInternalizers(internalizer -> internalizer.internalizeGameEvents(list));

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

    @Override
    public void add (ISprite sprite) {
        mySpriteManager.add(sprite);
    }

    @Override
    public INextLevelManager getNextLevelManager () {
        return myNextLevelManager;
    }

    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;

    }

    @Override
    public IWaveSetManager getWaveSetManager () {
        return myWaveSetManager;
    }

    @Override
    public ObservableList<SpriteDefinition> getAddableSprites () {
        return myAddableSprites;
    }

    @Override
    public double getBackgroundImageWidth () {
        return myBackgroundImageWidth;
    }

    @Override
    public double getBackgroundImageHeight () {
        return myBackgroundImageHeight;
    }

    @Override
    public void setBackgroundImageSize (double width, double height) {
        myBackgroundImageWidth = width;
        myBackgroundImageHeight = height;
    }

    @Override
    public IPlaceableTileManager getPlaceableTileManager () {
        return myPlaceableTileManager;
    }

    @Override
    public void initializePlaceableTiles (int row, int column) {
        myPlaceableTileManager.initialize(row, column);
    }

    @Override
    public Bounds getBounds () {
        return myBounds;
    }
    
    @Override
    public void setBounds (Bounds bound) {
        myBounds = bound;
    }
}
