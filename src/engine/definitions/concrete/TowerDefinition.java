package engine.definitions.concrete;

import java.util.List;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;


public class TowerDefinition extends SpriteDefinition {

    private TrackingFirerDefinition myFirer;

    @Override
    public ISprite create () {
        ISprite sprite = super.create();
        addModule(myFirer);
        sprite.initialize(new StaticMover(sprite), createGraphicModule(), createModules(sprite),
                          createAttributes(),
                          createCoordinate());
        return sprite;
    }

    private void setFirer (TrackingFirerDefinition firer) {
        myFirer = firer;
    }
    
    private TrackingFirerDefinition getFirer () {
        return myFirer;
    }
}
