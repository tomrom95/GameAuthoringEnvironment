package testing;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.Game;
import engine.ISpriteGroup;
import engine.Level;
import engine.LevelManager;
import engine.SpriteGroup;
import engine.conditions.OnCollisionCondition;
import engine.conditions.OnSpriteAttributeCondition;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.KeyControlDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.MovementDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
import engine.definitions.moduledef.UserMoverDefinition;
import engine.events.EventPackage;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.Profile;
import engine.rendering.GameGridConfigNonScaling;
import engine.sprite.ISprite;
import engine.effects.DecreaseEffect;
import engine.effects.IEffect;
import gameplayer.GamePlayer;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Coordinate;
import graphics.ImageGraphic;


public class Launcher extends Application {

    // Health attribute properties
    private static final double HEALTH_START_VAL = 100d;
    private static final String HEALTH_IMAGE_URL = "/images/photo.png";
    private static final String HEALTH_DESCRIPTION = "Health attribute";
    private static final String HEALTH_ATTY_TYPE = "Health";
    // Global Parameters
    private static final double FPS_TARGET = 30;
    private static final double PANE_WIDTH = 1200;
    private static final double PANE_HEIGHT = 800;

    // Sprite Parameters
    private static final double SPRITE_MOVEMENT_SPEED = 0.3;
    private static final double SPRITE_HEIGHT = 50;
    private static final double SPRITE_WIDTH = 50;
    private static final String SPRITE_IMAGE_URL = "/images/photo.png";
    private static final String USER_SPRITE_TYPE = "UserSprite";
    private static final String SPRITE_DESCRIPTION = "A sprite";

    // Enemy Parameters /GitDepends/Images/rat.png";
    private static final String ENEMY_IMAGE_URL = "/images/photo.png";
    private static final String ENEMY_SPRITE_DESCR = "An enemy sprite";
    private static final String ENEMY_SPRITE_TYPE = "EnemySprite";
    private static final int ENEMY_INITIAL_X = 100;
    private static final int ENEMY_INITIAL_Y = 100;

    // Other Parameters
    private static final int SPRITE_INITIAL_X = 10;
    private static final int SPRITE_INITIAL_Y = 10;

    private static final String BACKGROUND_URL = "/images/pvz.jpg";

    private Game myGame;

    public static void main (String[] args) {
        launch(args);
    }

    private void addConditionsToTest (Game game) {
        game.getConditionManager().getConditionListProperty().add(createCollisionCondition(game));
        game.getConditionManager().getConditionListProperty()
                .add(createHealthAttributeZeroDeathCondition(game));
    }

    private OnSpriteAttributeCondition createHealthAttributeZeroDeathCondition (Game game) {
        return new OnSpriteAttributeCondition(game, new AttributeType(HEALTH_ATTY_TYPE),
                                              value -> value < 0, userSpriteDeathEvent(),
                                              createEmptyEventPackage(),
                                              createEmptyEventPackage());

        // return new OnSpriteAttributeCondition(game, createHealthAttributeDefinition().create(),
        // value -> value < 0, userSpriteDeathEvent(),
        // createEmptyEventPackage(),
        // createEmptyEventPackage());

    }

    private OnCollisionCondition createCollisionCondition (Game game) {
        return new OnCollisionCondition(game, userSpriteGetsHurtPackage(),
                                        createEnemyEventPackage(), createEmptyEventPackage(),
                                        createEmptyEventPackage());
    }

    private List<IEffect> dmgHealth () {
        List<IEffect> toReturn = new ArrayList<>();
        toReturn.add(new DecreaseEffect(new AttributeType(HEALTH_ATTY_TYPE),
                                        new Attribute(15d, new AttributeType("cd")), 50));
        return toReturn;
    }

    private List<IEffect> noEffect () {
        return new ArrayList<>();
    }

    private List<GameEvent> noEvent () {
        return new ArrayList<>();
    }

    private List<GameEvent> deathEvent () {
        List<GameEvent> toReturn = new ArrayList<>();
        GameEvent event = new GameEvent(EventType.DEATH);
        toReturn.add(event);
        return toReturn;
    }

    private ISpriteGroup createSpriteGroupForDefinition (SpriteDefinition definition) {
        List<SpriteDefinition> mySpritesInGroup = new ArrayList<>();
        mySpritesInGroup.add(definition);
        return new SpriteGroup(mySpritesInGroup);
    }

    private ISpriteGroup createSpriteGroupForDefinition () {
        return new SpriteGroup(new ArrayList<>());
    }

    private EventPackage userSpriteGetsHurtPackage () {
        return new EventPackage(createSpriteGroupForDefinition(createUserSpriteDefinition(0, 0)),
                                dmgHealth(), noEvent());
    }

    private EventPackage userSpriteDeathEvent () {
        return new EventPackage(createSpriteGroupForDefinition(createUserSpriteDefinition(0, 0)),
                                noEffect(), deathEvent());
    }

    private EventPackage createEnemyEventPackage () {
        return new EventPackage(createSpriteGroupForDefinition(createEnemySpriteDefinition(0, 0)),
                                noEffect(), noEvent());
    }

    private EventPackage createEmptyEventPackage () {
        return new EventPackage(createSpriteGroupForDefinition(), noEffect(), noEvent());
    }

    private void addSpritesToGame (Game game) {
        ISprite test = createUserSpriteDefinition(SPRITE_INITIAL_X, SPRITE_INITIAL_Y).create();
        game.bufferedAdd(createEnemySpriteDefinition(ENEMY_INITIAL_X, ENEMY_INITIAL_Y).create());
        game.bufferedAdd(createUserSpriteDefinition(SPRITE_INITIAL_X, SPRITE_INITIAL_Y).create());
    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        Level firstLevel = new Level();
        firstLevel.setBackgroundImage(new ImageGraphic(PANE_WIDTH, PANE_HEIGHT, BACKGROUND_URL));
        LevelManager lm = new LevelManager();
        lm.createNewLevel(firstLevel);

        myGame =
                new Game(new GameGridConfigNonScaling((int)GamePlayer.PREFWIDTH, (int)GamePlayer.PREFHEIGHT));
        myGame.getLevelManager().getLevels().add(firstLevel);
        addSpritesToGame(myGame);
        addConditionsToTest(myGame);
        GamePlayer gp = new GamePlayer(myGame);
        gp.play();

    }

    private void addUserControlledSprite (Game game, int numToAdd) {
        for (int i = 0; i < numToAdd; i++) {
            myGame.bufferedAdd(createUserSpriteDefinition(i * 10, 10).create());

        }

    }

    private SpriteDefinition createEnemySpriteDefinition (int xloc, int yloc) {
        SpriteDefinition enemyDefinition = new SpriteDefinition();
        enemyDefinition.setMovementDefinition(getStationaryDefintion());
        enemyDefinition.setProfile(enemySpriteProfile());
        enemyDefinition.setLocation(new Coordinate(xloc, yloc));
        enemyDefinition.addAttribute(createHealthAttributeDefinition());
        return enemyDefinition;
    }

    private MovementDefinition getStationaryDefintion () {
        MovementDefinition myStationary = new StaticMovementDefintion();
        myStationary.setSpeed(0);
        return myStationary;
    }

    private Profile enemySpriteProfile () {
        return new Profile(ENEMY_SPRITE_TYPE, ENEMY_SPRITE_DESCR,
                           new ImageGraphic(SPRITE_HEIGHT, SPRITE_WIDTH, ENEMY_IMAGE_URL));
    }

    private SpriteDefinition createUserSpriteDefinition (int xloc, int yloc) {
        SpriteDefinition mySpriteDefinition = new SpriteDefinition();
        mySpriteDefinition.setMovementDefinition(getUserControlledDefinition());
        mySpriteDefinition.setProfile(userSpriteProfile());
        mySpriteDefinition.setLocation(new Coordinate(xloc, yloc));
        mySpriteDefinition.addAttribute(createHealthAttributeDefinition());
        return mySpriteDefinition;
    }

    private AttributeDefinition createHealthAttributeDefinition () {
        AttributeDefinition myDef = new AttributeDefinition();
        myDef.setProfile(healthAttyProfile());
        myDef.setType(healthAttyProfile().getName().get());
        myDef.setStartingValue(HEALTH_START_VAL);
        return myDef;
    }

    private Profile healthAttyProfile () {
        return new Profile(HEALTH_ATTY_TYPE, HEALTH_DESCRIPTION,
                           new ImageGraphic(SPRITE_HEIGHT, SPRITE_WIDTH, HEALTH_IMAGE_URL));
    }


    private Profile userSpriteProfile () {
        return new Profile(USER_SPRITE_TYPE, SPRITE_DESCRIPTION,
                           new ImageGraphic(SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_IMAGE_URL));
    }

    private UserMoverDefinition getUserControlledDefinition () {
        UserMoverDefinition myMoverDef = new UserMoverDefinition();
        myMoverDef.setSpeed(SPRITE_MOVEMENT_SPEED);
        KeyControlDefinition myKeys = new KeyControlDefinition();
        setKeysForDefinition(myKeys);
        myMoverDef.setKeyControlDefintion(myKeys);
        return myMoverDef;
    }

    private void setKeysForDefinition (KeyControlDefinition definition) {
        definition.setDown("Down");
        definition.setUp("Up");
        definition.setLeft("Left");
        definition.setRight("Right");
    }

}
