package engine.definitions.concrete;

import java.util.List;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.modules.StaticMover;
import engine.sprite.ISprite;


public class TowerDefinition extends SpriteDefinition {

    private List<SpriteDefinition> myTargets;
    
    public void setTargets (List<SpriteDefinition> targets) {
        myTargets = targets;
    }
    
    @Override
    public ISprite create () {
        ISprite sprite = super.create();
        addModule(getFirer());
        sprite.initialize(new StaticMover(sprite), createGraphicModule(), createModules(), createAttributes(),
                          createCoordinate());
        return sprite;
    }

    private TrackingFirerDefinition getFirer () {
       return new TrackingFirerDefinition(myTargets);
    }
}
