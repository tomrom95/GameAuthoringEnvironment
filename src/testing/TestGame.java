package testing;

import engine.ConditionManager;
import engine.Game;
import engine.LevelManager;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IMovementModule;
import engine.modules.PathFollowMover;
import engine.modules.UserControlledMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
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
import engine.*;


public class TestGame extends Application {

    public static void main (String[] args) {
        launch(args);
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        LevelManager levelManager = new LevelManager(startingLevel);

        ISprite sprite = createFollowSprite();
        levelManager.add(sprite, new Coordinate(0,0));
        
        levelManager.add(createUserSprite(), new Coordinate(10,10));
        ConditionManager conditionManager = new ConditionManager();
        Game game = new Game(levelManager, null, conditionManager);
        
       
        GamePlayer player = new GamePlayer(game);
        player.play();
        
        
    }

    private ISprite createFollowSprite () {
        ISprite sprite = new Sprite();
        List<Coordinate> list = getListOfCoordinates();
        ObjectProperty<IMovementModule> mover = new SimpleObjectProperty<>(new PathFollowMover(.10, list, sprite));
        ObjectProperty<IGraphicModule> g = new SimpleObjectProperty<>(new GraphicModule(new Block(20, 20, RGBColor.BLACK)));
        sprite.getMovementStrategyProperty().set(mover.get());
        sprite.getDrawer().set(g.get());
        return sprite;
    }
    
    private ISprite createUserSprite () {
        ISprite sprite = new Sprite();
        ControlKeys keys = new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        ObjectProperty<IMovementModule> mover = new SimpleObjectProperty<>(new UserControlledMover(1, keys, sprite));
        ObjectProperty<IGraphicModule> g = new SimpleObjectProperty<>(new GraphicModule(new Block(20, 20, RGBColor.BLACK)));
        sprite.getMovementStrategyProperty().set(mover.get());
        sprite.getDrawer().set(g.get());
        return sprite;
    }

    private List<Coordinate> getListOfCoordinates () {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(100,100));
        list.add(new Coordinate(20, 20));
        list.add(new Coordinate(200, 2));
        list.add(new Coordinate(500, 30));
        return list;
    }

}
