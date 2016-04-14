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
import engine.definitions.PathMoverDefinition;
import engine.definitions.SpawnerModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.StaticMovementDefintion;
import engine.definitions.UserMoverDefinition;
import engine.definitions.WaveDefinition;
import engine.definitions.concrete.SpawnerDefinition;
import engine.modules.PathMover;
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
        myGame = game;
        createGlobalAtts(game);
        createSpriteDefs(game);
        setBackground();
        //addSpawner(game);
        
    }

//    private void addSpawner (IGame game) {
//        ILevel level = game.getLevelManager().getCurrentLevel();
//        level.add(createSpawner(), new Coordinate(50, 50));
//    }

    private void setBackground () {
        myGame.getLevelManager().getCurrentLevel().setBackgroundImage(new ImageGraphic(0, 0, "/images/pvz.jpg"));
        
    }

    private ISprite createSpawnerDef () {
        SpawnerDefinition s = new SpawnerDefinition();
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition (sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition (myGame, wave, 4000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(800, 125));
        spawner.setLocation(new Coordinate(0, 200));
        spawner.setPath(path);
        return spawner;
    }

    private SpriteDefinition createBucket () {
        SpriteDefinition sd1 = new SpriteDefinition();
        double c = 8;
        ImageGraphic image = new ImageGraphic(446/c, 774/c, "/images/Buckethead_Zombie.png");
        sd1.setProfile(new Profile("BucketEnemy", "Buckets", image));
        PathMoverDefinition mover = new PathMoverDefinition();
        mover.setSpeed(.05);
        sd1.setMovementDefinition(mover);
        return sd1;
    }
    
    private SpriteDefinition createBalloon () {
        SpriteDefinition sd1 = new SpriteDefinition();
        double c = 6;
        ImageGraphic image = new ImageGraphic(332/c, 600/c, "/images/balloon_zomb.png");
        sd1.setProfile(new Profile("BucketEnemy", "Buckets", image));
        PathMoverDefinition mover = new PathMoverDefinition();
        mover.setSpeed(.05);
        sd1.setMovementDefinition(mover);
        return sd1;
    }

    private SpriteDefinition createPathMover () {
        SpriteDefinition sd1 = new SpriteDefinition();
        PathMoverDefinition mover = new PathMoverDefinition();
        mover.setSpeed(.1);
        sd1.setMovementDefinition(mover);
        return sd1;
    }

    private SpriteDefinition createProjectile () {
        SpriteDefinition sd1 = new SpriteDefinition();
        sd1.setMovementDefinition(getUserMover());
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
        ImageGraphic image = new ImageGraphic(100, 100, "/images/C.png");
        sd2.setProfile(new Profile("User Mover", "Controlled By User", image));
        sd2.setMovementDefinition(getUserMover());
        
        ISprite sd3 = createSpawnerDef();
        
        dc.addItem(sd1);
        dc.addItem(sd2);
        game.add(sd3, new Coordinate (50, 50));
        game.getAuthorshipData()
                .addCreatedSprites(dc);
    }

    private MovementDefinition getUserMover () {
        KeyControlDefinition keys = new KeyControlDefinition();
        keys.setUp("Up");
        keys.setRight("Right");
        keys.setLeft("Left");
        keys.setDown("Down");
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
