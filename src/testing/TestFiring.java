package testing;

import java.util.ArrayList;
import java.util.List;
import engine.ConditionManager;
import engine.Game;
import engine.GameInformation;
import engine.ILevel;
import engine.Level;
import engine.LevelManager;
import engine.definitions.AttributeDefinition;
import engine.definitions.KeyControlDefinition;
import engine.definitions.LocationDefinition;
import engine.definitions.ModuleDefiniton;
import engine.definitions.MovementDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.UserMoverDefinition;
import engine.modules.GraphicModule;
import engine.modules.IFireModule;
import engine.modules.IGraphicModule;
import engine.modules.IMovementModule;
import engine.modules.PathMover;
import engine.modules.UserFirer;
import engine.modules.UserMover;
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

public class TestFiring extends Application {
    
    private Level start = new Level();

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        //XStream xstream = new XStream(new DomDriver());
        //FXConverters.configure(xstream);

        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(start);
        LevelManager levelManager = new LevelManager(startingLevel);
        //xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);

        ISprite sprite = createFollowSprite();
        // levelManager.add(sprite, new Coordinate(0,0));

        levelManager.bufferedAdd(createUserSprite(), new Coordinate(10, 10));
        ConditionManager conditionManager = new ConditionManager();
        Game game = new Game(levelManager, new GameInformation("r", "r", "r"), conditionManager);

        //Game game2 = (Game) xstream.fromXML(xstream.toXML(game));

        GamePlayer player = new GamePlayer(game);

    }

    private ISprite createFollowSprite () {
        ISprite sprite = new Sprite();
        List<Coordinate> list = getListOfCoordinates();
        ObjectProperty<IMovementModule> mover =
                new SimpleObjectProperty<>(new PathMover(.10, list, sprite));
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(20, 20, RGBColor.BLACK)));
        sprite.getMovementStrategyProperty().set(mover.get());
        sprite.getDrawer().set(g.get());
        return sprite;
    }

    private ISprite createUserSprite () {

        ISprite sprite = new Sprite();
        ControlKeys keys =
                new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        Key fireKey = new Key("F");
        ObjectProperty<IMovementModule> mover =
                new SimpleObjectProperty<>(new UserMover(1, keys, sprite));
        
        SpriteDefinition bullet = new SpriteDefinition();
        UserMoverDefinition move = new UserMoverDefinition();
        bullet.setMovementDefinition(move);
        move.setSpeed(10);
        KeyControlDefinition control = new KeyControlDefinition();
        control.setUp("Up");
        control.setDown("Down");
        control.setLeft("Left");
        control.setRight("Right");
        
        ModuleDefinition moduleDef = new ModuleDefiniton();
        
        
        List<ModuleDefiniton> myModuleDefinitions;
        LocationDefinition myLocation;
        List<AttributeDefinition> myAttributes;
        move.setKeyControlDefintion(control);
        
        
        ObjectProperty<IFireModule> firer = new SimpleObjectProperty<>(new UserFirer(bullet, fireKey, start, 10));
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(20, 20, RGBColor.BLACK)));
        sprite.getMovementStrategyProperty().set(mover.get());
        sprite.getDrawer().set(g.get());
        sprite.getModulesProperty().bufferedAdd(firer);
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
