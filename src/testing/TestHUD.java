package testing;

import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.modules.UserMover;
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
import java.util.ArrayList;
import java.util.List;
import engine.Game;
import engine.*;


public class TestHUD extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        LevelManager levelManager = new LevelManager(startingLevel.get());


        //levelManager.add(createUserSprite(), new Coordinate(10, 10));
        ConditionManager conditionManager = new ConditionManager();
        Game game = new Game(levelManager, new GameInformation("r", "r", "r"), conditionManager);

        addSomeAttributes(game);

        GamePlayer player = new GamePlayer(game);
        player.start();
    }

    private void addSomeAttributes (Game game) {
        game.getGlobalAttributes().add(new Attribute(50, new AttributeType("Gold")));
        game.getGlobalAttributes().add(new Attribute(5, new AttributeType("Lives")));
    }


    private ISprite createUserSprite () {

        ISprite sprite = new Sprite(new SpriteType("User"));
        ControlKeys keys =
                new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        ObjectProperty<IMovementModule> mover =
                new SimpleObjectProperty<>(new UserMover(1, keys, sprite));
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(20, 20, RGBColor.BLACK)));
        sprite.initialize(mover.get(), g.get(), new ArrayList<IModule>(), new ArrayList<IAttribute>(), new Coordinate(10,10));
        return sprite;
    }

    private static List<Coordinate> getListOfCoordinates () {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(100, 100));
        list.add(new Coordinate(20, 20));
        list.add(new Coordinate(200, 2));
        list.add(new Coordinate(500, 30));
        return list;
    }

}

