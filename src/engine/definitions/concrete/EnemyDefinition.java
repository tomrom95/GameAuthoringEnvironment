package engine.definitions.concrete;

import engine.definitions.SpawnerModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;

public class EnemyDefinition extends SpriteDefinition {


    public SpawnerDefinition (SpawnerModuleDefinition spawningModule) {
        setSpawningModule(spawningModule);
    }
    
    private void setSpawningModule (SpawnerModuleDefinition spawningModule) {
        mySpawningModule = spawningModule;   
    }

    public ISprite create () {
        ISprite sprite = super.create();
        addModule(mySpawningModule);
        sprite.initialize(new StaticMover(sprite), createGraphicModule(), createModules(), createAttributes(),
                          createCoordinate());
        return sprite;

    }
}
