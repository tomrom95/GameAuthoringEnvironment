package testing.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.Game;
import engine.IAttribute;
import engine.IGame;
import engine.ISpriteGroup;
import engine.SpriteGroup;
import engine.conditions.OnCollisionCondition;
import engine.definitions.ConstantMoverDefinition;
import engine.definitions.KeyControlDefinition;
import engine.definitions.MovementDefinition;
import engine.definitions.PathMoverDefinition;
import engine.definitions.SpawnerModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.StaticMovementDefintion;
import engine.definitions.TrackingFirerDefinition;
import engine.definitions.UserMoverDefinition;
import engine.definitions.WaveDefinition;
import engine.definitions.concrete.SpawnerDefinition;
import engine.effects.IEffect;
import engine.events.EventPackage;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.Profile;
import engine.rendering.GameGridConfigNonScaling;
import engine.sprite.ISprite;
import gameauthoring.shareddata.DefinitionCollection;
import gameplayer.GamePlayer;
import graphics.ImageGraphic;
import javafx.application.Application;
import javafx.stage.Stage;
import serialize.GameWriter;
import util.Coordinate;


public class DemoLauncherTracker extends Application {

    private IGame myGame;

    @Override
    public void start (Stage primaryStage) throws Exception {
        makeGame();
        new GameWriter().serialize(new File("/Users/davidmaydew/Desktop/Bloons.xml"), myGame);
        //GamePlayer gp = new GamePlayer(myGame);
    }

    private void makeGame () {
        IGame game = new Game(new GameGridConfigNonScaling(GamePlayer.PREFWIDTH, GamePlayer.PREFHEIGHT));
        myGame = game;
        //createGlobalAtts(game);
        createSpriteDefs(game);
        setBackground();
        addSpawner1(game);
        addSpawner2(game);
        //addSpawner3(game);
        //addSpawner4(game);
        createConditions(game);

    }

    private void createConditions (IGame game) {
        game.getConditionManager().getConditionListProperty().add(createCollisionCondition(game));

    }

    private OnCollisionCondition createCollisionCondition (IGame game) {
        List<SpriteDefinition> g1 = new ArrayList<>();
        g1.add(createMissileDef());
        List<SpriteDefinition> g2 = new ArrayList<>();
        g2.add(createBucket());
        g2.add(createBalloon());
        
        return new OnCollisionCondition(game, packageForSpriteDefinitions(g1),
                                        packageForSpriteDefinitions(g2),
                                        createEmptyEventPackage(),
                                        createEmptyEventPackage());
    }

    private ISpriteGroup createSpriteGroupForDefinition (List<SpriteDefinition> definition) {
        List<SpriteDefinition> mySpritesInGroup = new ArrayList<>();
        mySpritesInGroup.addAll(definition);
        return new SpriteGroup(mySpritesInGroup);
    }

    private EventPackage createEmptyEventPackage () {
        return new EventPackage(createSpriteGroupForDefinition(), noEffect(), noEvent());
    }

    private ISpriteGroup createSpriteGroupForDefinition () {
        return new SpriteGroup(new ArrayList<>());
    }

    private List<GameEvent> noEvent () {
        return new ArrayList<>();
    }

    private EventPackage packageForSpriteDefinitions (List<SpriteDefinition> list) {
        return new EventPackage(createSpriteGroupForDefinition(list),
                                noEffect(), deathEvent());
    }

    private List<IEffect> noEffect () {
        return new ArrayList<>();
    }

    private List<GameEvent> deathEvent () {
        List<GameEvent> toReturn = new ArrayList<>();
        GameEvent event = new GameEvent(EventType.DEATH);
        toReturn.add(event);
        return toReturn;
    }

    // private void addSpawner (IGame game) {
    // ILevel level = game.getLevelManager().getCurrentLevel();
    // level.add(createSpawner(), new Coordinate(50, 50));
    // }

    private void setBackground () {
        myGame.getLevelManager().getCurrentLevel()
                .setBackgroundImage(new ImageGraphic(0, 0, "/images/bloonsBackground.jpg"));

    }

    private void addSpawner1 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition(game);
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition(sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition(myGame, wave, 5000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(400, 150));
        path.add(new Coordinate(420, 500));
        spawner.setLocation(new Coordinate(000, 000));
        spawner.setPath(path);
        game.add(spawner);
    }

    private void addSpawner2 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition(game);
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition(sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition(myGame, wave, 3000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(400, 200));
        path.add(new Coordinate(420, 500));
        spawner.setLocation(new Coordinate(900, 000));
        spawner.setPath(path);
        game.add(spawner);
    }

    private void addSpawner3 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition(game);
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition(sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition(myGame, wave, 4000);
        s.setMySpawningModule(sM);
        ISprite spawner = s.create();
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(0, 200));
        spawner.setLocation(new Coordinate(800, 200));
        spawner.setPath(path);
        game.add(spawner);
    }

    private void addSpawner4 (IGame game) {
        SpawnerDefinition s = new SpawnerDefinition(game);
        List<SpriteDefinition> sprites = new ArrayList<SpriteDefinition>();
        sprites.add(createBucket());
        sprites.add(createBalloon());
        sprites.add(createBucket());
        WaveDefinition wave = new WaveDefinition(sprites);
        SpawnerModuleDefinition sM = new SpawnerModuleDefinition(myGame, wave, 7000);
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
        double c = 20;
        ImageGraphic image = new ImageGraphic(446 / c, 774 / c, "/images/Pink_Bloon.png");
        sd1.setProfile(new Profile("BucketEnemy", "Buckets", image));
        PathMoverDefinition mover = new PathMoverDefinition();
        mover.setSpeed(.02);
        sd1.setMovementDefinition(mover);
        return sd1;
    }

    private SpriteDefinition createBalloon () {
        SpriteDefinition sd1 = new SpriteDefinition();
        double c = 20;
        ImageGraphic image = new ImageGraphic(332 / c, 600 / c, "/images/Rainbow_Bloon.png");
        sd1.setProfile(new Profile("Balloon Enemy", "Buckets", image));
        PathMoverDefinition mover = new PathMoverDefinition();
        mover.setSpeed(.02);
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

        SpriteDefinition sd1 = createShooterDef();

        SpriteDefinition sd2 = new SpriteDefinition();
        ImageGraphic image = new ImageGraphic(100, 100, "/images/C.png");
        sd2.setProfile(new Profile("User Mover", "Controlled By User", image));
        sd2.setMovementDefinition(getUserMover());

        dc.addItem(sd1);
        dc.addItem(sd2);
        game.getAuthorshipData()
                .addCreatedSprites(dc);
    }

    private SpriteDefinition createShooterDef () {
        SpriteDefinition sd1 = new SpriteDefinition();
        ImageGraphic plantImage = new ImageGraphic(80, 80, "/images/tower.png");
        sd1.setProfile(new Profile("Tower 1", "Clash of Clans", plantImage));
        sd1.setMovementDefinition(getStaticMover());
        
//        DirectionalFirerDefinition fireDef = new DirectionalFirerDefinition();
        TrackingFirerDefinition fireDef = new TrackingFirerDefinition();
        fireDef.setGame(myGame);
//        fireDef.setAngle(0);
        fireDef.setWaitTime(2000);
        List<SpriteDefinition> myTargets = new ArrayList<SpriteDefinition>();
        myTargets.add(createBucket());
        myTargets.add(createBalloon());
        fireDef.setTargets(new SpriteGroup(myTargets));
        fireDef.setProjectileDefinition(createMissileDef());
        sd1.addModule(fireDef);
        return sd1;
    }

    private SpriteDefinition createMissileDef () {
        SpriteDefinition sd1 = new SpriteDefinition();
        ImageGraphic plantImage = new ImageGraphic(20, 20, "/images/pea.png");
        sd1.setProfile(new Profile("Pea", "Pea Bullet", plantImage));
        ConstantMoverDefinition mover = new ConstantMoverDefinition();
        double c = 1;
        sd1.setMovementDefinition(mover);

        return sd1;
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
