package engine.definitions.concrete;

import engine.definitions.SpriteDefinition;
import engine.definitions.WaveDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import engine.IGame;
import engine.definitions.SpawnerModuleDefinition;


public class SpawnerDefinition extends SpriteDefinition {
    
    private static final String SPAWNER_NAME = "Spawner";
    private static final String SPAWNER_DESCRIPTION = "General Spawner";
    private static final String SPAWNER_URL = "images/plus.png";

    private SpawnerModuleDefinition mySpawningModule;
    
    public SpawnerDefinition (IGame game) {
        this.getProfile().setNew(SPAWNER_NAME, SPAWNER_DESCRIPTION, SPAWNER_URL);
        mySpawningModule = new SpawnerModuleDefinition(game, new WaveDefinition(null), 1);
    }

    @Override
    public ISprite create () {
        ISprite sprite = new Sprite(new SpriteType(getProfile().getName().get()));
        addModule(mySpawningModule);
        sprite.initialize(new StaticMover(sprite), createGraphicModule(), createModules(sprite),
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
