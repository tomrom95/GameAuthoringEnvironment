package testing;

import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.Game;
import engine.IAttribute;
import engine.IGame;
import engine.ILevel;
import engine.definitions.KeyControlDefinition;
import engine.definitions.MovementDefinition;
import engine.definitions.SpawnerModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.StaticMovementDefintion;
import engine.definitions.UserMoverDefinition;
import engine.definitions.WaveDefinition;
import engine.definitions.concrete.SpawnerDefinition;
import engine.modules.SpawningModule;
import engine.profile.Profile;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import gameauthoring.shareddata.DefinitionCollection;
import gameplayer.GamePlayer;
import graphics.ImageGraphic;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Coordinate;


public class DemoLauncher extends Application {

    private IGame myGame;

    @Override
    public void start (Stage primaryStage) throws Exception {
        makeGame();
        GamePlayer gp = new GamePlayer(myGame);
    }

    private void makeGame () {
        IGame game = new Game();
        createGlobalAtts(game);
        createSpriteDefs(game);
        //addSpawner(game);
        myGame = game;
    }

//    private void addSpawner (IGame game) {
//        ILevel level = game.getLevelManager().getCurrentLevel();
//        level.add(createSpawner(), new Coordinate(50, 50));
//    }

    private SpriteDefinition createSpawnerDef () {
        SpawnerDefinition s = new SpawnerDefinition();
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createProjectile());
        WaveDefinition wave = new WaveDefinition (sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition (myGame, wave);
        s.setMySpawningModule(sM);
        return s;
    }

    private SpriteDefinition createProjectile () {
        SpriteDefinition sd1 = new SpriteDefinition();
        sd1.setMovementDefinition(getStaticMover());
        return sd1;
    }

    private void createGlobalAtts (IGame game) {
        IAttribute lives = new Attribute(new AttributeType("Lives"));
        IAttribute coins = new Attribute(new AttributeType("Coins"));
        game.getAttributeManager().getAttributes().add(lives);
        game.getAttributeManager().getAttributes().add(coins);
    }

    private void createSpriteDefs (IGame game) {
        DefinitionCollection<SpriteDefinition> dc = new DefinitionCollection<>("Towers");
        SpriteDefinition sd1 = new SpriteDefinition();
        sd1.setMovementDefinition(getStaticMover());
        
        SpriteDefinition sd2 = new SpriteDefinition();
        ImageGraphic image = new ImageGraphic(20, 20, "/images/plus.png");
        sd2.setProfile(new Profile("User Mover", "Controlled By User", image));
        sd2.setMovementDefinition(getUserMover());
        
        //SpriteDefinition sd3 = createSpawnerDef();
        
        dc.addItem(sd1);
        dc.addItem(sd2);
        //dc.addItem(sd3);
        game.getAuthorshipData()
                .addCreatedSprites(dc);
    }

    private MovementDefinition getUserMover () {
        KeyControlDefinition keys = new KeyControlDefinition();
        keys.setUp("W");
        keys.setRight("D");
        keys.setLeft("A");
        keys.setDown("S");
        UserMoverDefinition user = new UserMoverDefinition();
        user.setSpeed(.5);
        user.setKeyControlDefintion(keys);
        return user;

    }

    private MovementDefinition getStaticMover () {
        return new StaticMovementDefintion();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
