package testing;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.ConditionManager;
import engine.Game;
import engine.GameInformation;
import engine.IAttribute;
import engine.ICondition;
import engine.ILevel;
import engine.ISpriteGroup;
import engine.Level;
import engine.LevelManager;
import engine.OnClickCondition;
import engine.OnGlobalAttributeCondition;
import engine.OnSpriteAttributeCondition;
import engine.SpriteGroup;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import gameplayer.GamePlayer;
import graphics.Block;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import util.ControlKeys;
import util.Coordinate;
import util.Key;
import util.RGBColor;
import util.TimeDuration;
import engine.effects.*;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IMovementModule;
import engine.modules.UserMover;


public class TestConditions extends Application {

    private ISprite mySprite;
    private Game myGame;

    private AttributeType myHealthAttyType;
    private AttributeType myEffectDurationAttribute;
    private Attribute myEffectDuration;

    private void initThings () {
        myHealthAttyType = new AttributeType("Health");
        myEffectDurationAttribute = new AttributeType("CoolDown");
        myEffectDuration = new Attribute(10d, myEffectDurationAttribute);
    }

    private List<IEffect> effectToApplyToSelf () {
        List<IEffect> effectList = new ArrayList<>();
        effectList.add(new IncreaseEffect(myHealthAttyType, myEffectDuration, -10));
        return effectList;

    }

    private List<IEffect> emptyEffects () {
        List<IEffect> effectList = new ArrayList<>();
        // effectList.add(new IncreaseEffect(myHealthType, myEffectDuration, -10));
        return effectList;
    }

    private void addConditionsToTest (ConditionManager conditionManager) {

        ICondition myCondition =
                new OnSpriteAttributeCondition(myGame,
                                               createUserSpriteGroup(createListWithUserSpriteType()),
                                               myHealthAttyType, x -> x < 120,
                                               effectToApplyToSelf(), emptyEffects(),
                                               new SpriteGroup(), emptyEffects());
        //conditionManager.getConditionListProperty().add(new SimpleObjectProperty<>(myCondition));
        myCondition = new OnClickCondition(myGame,
                                           createUserSpriteGroup(createListWithUserSpriteType()),
                                           effectToApplyToSelf(),
                                           emptyEffects(),
                                           new SpriteGroup(),
                                           emptyEffects());
        conditionManager.getConditionListProperty().add(new SimpleObjectProperty<>(myCondition));
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        initThings ();
        ConditionManager conditionManager = new ConditionManager();
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        LevelManager levelManager = new LevelManager(startingLevel);
        myGame = new Game(levelManager, new GameInformation("r", "r", "r"), conditionManager);

        levelManager.add(createUserSprite(), new Coordinate(10, 10));
        addConditionsToTest(conditionManager);

        GamePlayer player = new GamePlayer(myGame);
    }

    private ISprite createUserSprite () {
        ISprite sprite = new Sprite();
        ObjectProperty<SpriteType> currentSpriteType = sprite.getType();
        currentSpriteType.set(createUserSpriteType());
        ControlKeys keys =
                new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        ObjectProperty<IMovementModule> mover =
                new SimpleObjectProperty<>(new UserMover(1, keys, sprite));
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(60, 60, RGBColor.BLACK)));
        sprite.getMovementStrategyProperty().set(mover.get());
        sprite.getDrawer().set(g.get());
        sprite.getAttributeManager().get().getAttributes()
                .add(new SimpleObjectProperty<IAttribute>(createAttribute(myHealthAttyType, 100)));
        return sprite;
    }

    private SpriteType createUserSpriteType () {
        return new SpriteType("User");
    }

    private List<SpriteType> createListWithUserSpriteType () {
        List<SpriteType> list = new ArrayList<>();
        list.add(createUserSpriteType());
        return list;
    }

    private IAttribute createAttribute (AttributeType type, double startingValue) {
        Attribute myAttribute = new Attribute(myHealthAttyType);
        myAttribute.setValue(startingValue);
        return myAttribute;
    }

    private SpriteGroup createUserSpriteGroup (List<SpriteType> myList) {
        SpriteGroup toReturn = new SpriteGroup(myList);
       
        
       // System.out.println(createUserSpriteType().equals(toReturn));
        return toReturn;
    }

}
