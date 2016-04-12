package engine.definitions.concrete;

import engine.definitions.SpriteDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;
import engine.definitions.SpawnerModuleDefinition;


public class SpawnerDefinition extends SpriteDefinition {

    private SpawnerModuleDefinition mySpawningModule;

    public ISprite create () {
        ISprite sprite = super.create();
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
