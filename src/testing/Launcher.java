package testing;

import engine.ConditionManager;
import engine.Game;
import engine.GameInformation;
import engine.IOInterpeter;
import engine.Level;
import engine.LevelManager;
import engine.definitions.KeyControlDefinition;
import engine.definitions.LocationDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.UserMoverDefinition;
import engine.profile.Profile;
import engine.rendering.InGameRenderer;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import gameplayer.GameEngine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import graphics.ImageGraphic;
import util.TimeDuration;


public class Launcher extends Application {

    // Global Parameters
    private static final double FPS_TARGET = 30;
    private static final Duration FRAME_DURATION = Duration.seconds(1d / FPS_TARGET);
    private static final double PANE_WIDTH = 1200;
    private static final double PANE_HEIGHT = 800;

    // Sprite Parameters
    private static final double SPRITE_MOVEMENT_SPEED = 0.3;
    private static final double SPRITE_HEIGHT = 10;
    private static final double SPRITE_WIDTH = 10;
    private static final String SPRITE_IMAGE_URL = "/images/photo.png";
    private static final String USER_SPRITE_TYPE = "UserSprite";
    private static final String SPRITE_DESCRIPTION = "A sprite";

    // Other Parameters
    private static final int SPRITE_INITIAL_X = 10;
    private static final int SPRITE_INITIAL_Y = 10;

    private static final String BACKGROUND_URL = "/images/pvz.jpg";

    private IOInterpeter myIO;
    private Pane myPane;
    private Stage myStage;
    private Game myGame;
    private Scene myScene;

    public static void main (String[] args) {
        launch(args);
    }

    private void initJavaFXElements () {
        myStage = new Stage();
        myPane = new Pane();
        myPane.setStyle("-fx-background-color: white;");
        myScene = new Scene(myPane);
        myPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        initJavaFXElements();
        myIO = new IOInterpeter(myScene, myPane);

        
        Level firstLevel = new Level();
        firstLevel.setBackgroundImage(new ImageGraphic(PANE_WIDTH, PANE_HEIGHT, BACKGROUND_URL));
        for(int j = 0; j<30; j++){
            for(int i = 0; i<30; i++){
                firstLevel.add(createUserSpriteDefinition(i*10, j*10).create());
            }
        }

//        firstLevel.add(createUserSpriteDefinition(10, 10).create());
//        firstLevel.add(createUserSpriteDefinition(20, 10).create());
//        firstLevel.add(createUserSpriteDefinition(30, 10).create());
//        firstLevel.add(createUserSpriteDefinition(40, 10).create());
//        firstLevel.add(createUserSpriteDefinition(50, 10).create());
//        firstLevel.add(createUserSpriteDefinition(60, 10).create());
        
        LevelManager lm = new LevelManager(firstLevel);
        ConditionManager cm = new ConditionManager();
        GameInformation gi = new GameInformation("Oui", "Non", "Vrai");
        
        myGame = new Game(lm, gi, cm);
        
        GameEngine gameEngine = new GameEngine(myGame, myPane, myIO);
        
        
        myStage.setScene(myScene);
        myStage.show();
        gameEngine.play();

    }

    private SpriteDefinition createUserSpriteDefinition (int xloc, int yloc) {
        SpriteDefinition mySpriteDefinition = new SpriteDefinition();
        mySpriteDefinition
                .setGraphic(new ImageGraphic(SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_IMAGE_URL));
        mySpriteDefinition.setMovementDefinition(getUserControlledDefinition());
        mySpriteDefinition.setProfile(userSpriteProfile());
        mySpriteDefinition.setLocation(createUserLocationDefinition(xloc, yloc));
        return mySpriteDefinition;
    }

    private LocationDefinition createUserLocationDefinition (int xloc, int yloc) {
        LocationDefinition myLocDef = new LocationDefinition();
        myLocDef.setX(xloc);
        myLocDef.setY(yloc);
        return myLocDef;
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

    private SpriteType createSpriteType () {
        return new SpriteType("Test");
    }

}
