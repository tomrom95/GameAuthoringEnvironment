package engine.definitions.concrete;

import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;


/**
 * This class represents the definition template for a tower
 *
 */
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

    // TODO remove if truly unused (possibly should be public, need to see how authoring wants them)
    private void setFirer (TrackingFirerDefinition firer) {
        myFirer = firer;
    }

    private TrackingFirerDefinition getFirer () {
        return myFirer;
    }
}
