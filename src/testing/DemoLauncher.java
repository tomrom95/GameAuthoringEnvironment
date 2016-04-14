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
        addSpawner1(game);
        addSpawner2(game);
        addSpawner3(game);
        addSpawner4(game);
        
    }

//    private void addSpawner (IGame game) {
//        ILevel level = game.getLevelManager().getCurrentLevel();
//        level.add(createSpawner(), new Coordinate(50, 50));
//    }

    private void setBackground () {
        myGame.getLevelManager().getCurrentLevel().setBackgroundImage(new ImageGraphic(0, 0, "/images/pvz.jpg"));
        
    }

    private void addSpawner1 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition();
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition (sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition (myGame, wave, 1000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(0, 000));
        spawner.setLocation(new Coordinate(800, 000));
        spawner.setPath(path);
        game.add(spawner);
    }
    private void addSpawner2 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition();
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition (sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition (myGame, wave, 3000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(0, 100));
        spawner.setLocation(new Coordinate(800, 100));
        spawner.setPath(path);
        game.add(spawner);
    }
    private void addSpawner3 (IGame game) {
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
        path.add(new Coordinate(0, 200));
        spawner.setLocation(new Coordinate(800, 200));
        spawner.setPath(path);
        game.add(spawner);
    }
    private void addSpawner4 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition();
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition (sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition (myGame, wave, 2000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(0, 300));
        spawner.setLocation(new Coordinate(800, 300));
        spawner.setPath(path);
        game.add(spawner);
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
        ImageGraphic plantImage = new ImageGraphic(50, 50, "/images/plant.png");
        sd1.setProfile(new Profile("Tower 1", "Plant", plantImage));
        sd1.setMovementDefinition(getStaticMover());
        
        SpriteDefinition sd2 = new SpriteDefinition();
        ImageGraphic image = new ImageGraphic(100, 100, "/images/C.png");
        sd2.setProfile(new Profile("User Mover", "Controlled By User", image));
        sd2.setMovementDefinition(getUserMover());
        
        dc.addItem(sd1);
        dc.addItem(sd2);
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
