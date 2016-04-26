package engine;

import java.util.List;
import java.util.function.Consumer;
import engine.definitions.concrete.AttributeDefinition;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.rendering.GameGridConfigNonScaling;
import engine.rendering.IGameGridConfig;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class manages and structures the layout for the components of a game.
 *
 * @author RyanStPierre
 *
 */
public class Game implements IGame {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;

    private IGameGridConfig myGameGridConfig;
    private ILevelManager myLevelManager;
    private IConditionManager myConditionManager;
    private AuthorshipData myAuthorshipData;
    private IGameInformation myGameInformation;
    private IAttributeManager myAttributeManager;
    private IObstructionManager myObstructionManager;

    

    public Game (IGameGridConfig gridConfiguration) {
        // TODO remove hardcoded strings
        this(new GameInformation(), gridConfiguration);
      
    }
    
    /** @deprecated
     *  Shouldn't create a game without an internal notion of grid size, defaulting
     *  will lead to unexpected behavior given lack of scaling implementation
     * @param gameInfo
     */
    @Deprecated
    public Game (IGameInformation gameInfo) {
        this(gameInfo, new GameGridConfigNonScaling(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    public Game (IGameInformation gameInfo, IGameGridConfig gridConfiguration) {
        myGameGridConfig = gridConfiguration;
        myLevelManager = new LevelManager();
        myConditionManager = new ConditionManager();
        myAuthorshipData = new AuthorshipData();
        myGameInformation = gameInfo;
        myAttributeManager = new AttributeManager();
        myObstructionManager = new ObstructionManager(this);
        
    }

    @Override
    public void update (TimeDuration duration) {
        myLevelManager.update(duration);
        myConditionManager.update(duration);
        myAttributeManager.update(duration);
        myObstructionManager.update(duration);
    }

    @Override
    public IGameInformation getGameInformation () {
        return myGameInformation;
    }

    @Override
    public IAttributeManager getAttributeManager () {
        return myAttributeManager;
    }

    @Override
    public ILevelManager getLevelManager () {
        return myLevelManager;
    }

    @Override
    public IConditionManager getConditionManager () {
        return myConditionManager;
    }

    @Override
    public List<? extends Drawable> getDrawables () {
        return myLevelManager.getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        applyToInternalizers(internalizer -> internalizer.internalizeKeyEvents(list));
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        applyToInternalizers(internalizer -> internalizer.internalizeMouseEvents(list));
    }

    @Override
    public void internalizeGameEvents (List<GameEvent> list) {
        applyToInternalizers(internalizer -> internalizer.internalizeGameEvents(list));

    }

    private void applyToInternalizers (Consumer<IEventInternalizer> internalizer) {
        internalizer.accept(myLevelManager);
        internalizer.accept(myConditionManager);
    }

    @Override
    public AuthorshipData getAuthorshipData () {
        return myAuthorshipData;
    }

    @Override
    public ImageGraphic getBackroundImage () {
        return myLevelManager.getBackgroundImage();
    }

    @Override
    public List<IAttribute> getGlobalAttributes () {
        return getAttributeManager().getAttributes();
    }
    
    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        myLevelManager.add(sprite, coordinate);
    }

    @Override
    public void add (ISprite sprite) {
        myLevelManager.add(sprite);
    }

    @Override
    public void bufferedAdd (ISprite sprite) {
        myLevelManager.getCurrentLevel().bufferedAdd(sprite);
    }

    @Override
    public void bufferedAdd (ISprite sprite, Coordinate coordinate) {
        myLevelManager.bufferedAdd(sprite, coordinate);
    }

    @Override
    public IObstructionManager getObstructionManager () {
        return myObstructionManager;
    }
    
    @Override
    public IGameGridConfig getGameGridConfig () {
        return myGameGridConfig;
    }

    @Override
    public void createAndSortGlobals () {
        for(AttributeDefinition a: myAuthorshipData.getMyCreatedGlobals().getItems()){
            if(a.isLevelSpecific()){
                myLevelManager.getLevels().forEach(c->getAttributeManager().getAttributes().add(a.create()));
            }
            else{
                getAttributeManager().getAttributes().add(a.create());
            }
        }
    }
}
