package engine.definitions.concrete;

import engine.definitions.SpriteDefinition;
import engine.definitions.WaveDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import engine.IGame;
import engine.definitions.SpawnerModuleDefinition;


/**
 * This class represents the definition template for a spawner
 *
 */
public class SpawnerDefinition extends SpriteDefinition {

    // TODO put in resource file
    private static final String SPAWNER_NAME = "Spawner";
    private static final String SPAWNER_DESCRIPTION = "General Spawner";
    private static final String SPAWNER_URL = "images/plus.png";
    private static final double DEFAULT_SIZE = 80;

    private SpawnerModuleDefinition mySpawningModule;

    public SpawnerDefinition (IGame game) {
        getProfile().setNew(SPAWNER_NAME, SPAWNER_DESCRIPTION, SPAWNER_URL, DEFAULT_SIZE, DEFAULT_SIZE);
        // TODO wave definition should specify an empty constructor so it can handle its own default
        // rather than passing null
        mySpawningModule = new SpawnerModuleDefinition(game, new WaveDefinition(null), 1);
    }

    @Override
    public ISprite create () {
        ISprite sprite = new Sprite(new SpriteType(getProfile().getName().get()));
        addModule(mySpawningModule);
        sprite.initialize(new StaticMover(sprite), createGraphicModule(), 
                          createUpgrade(sprite),
                          createModules(sprite),
                          createAttributes(),
                          createCoordinate());
        return sprite;

    }

    public SpawnerModuleDefinition getMySpawningModule () {
        return mySpawningModule;
    }

    public void setMySpawningModule (SpawnerModuleDefinition spawningModule) {
        this.mySpawningModule = spawningModule;
    }
}
