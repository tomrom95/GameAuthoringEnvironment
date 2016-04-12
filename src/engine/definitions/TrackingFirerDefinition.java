package engine.definitions;

import java.util.List;
import java.util.stream.Collectors;
import engine.modules.TrackingFirer;
import engine.sprite.ISprite;


public class TrackingFirerDefinition extends ModuleDefinition {
    
    
    private List<SpriteDefinition> myTargets;
    private double waitTime;

    public TrackingFirerDefinition (List<SpriteDefinition> targets) {
        myTargets = targets;
    }

    public void setTargets (List<SpriteDefinition> targets) {
        myTargets = targets;
    }
    
    public void setWaitTime (double wait){
        waitTime = wait;
    }

    @Override
    public TrackingFirer create () {
        List<ISprite> targets = myTargets.stream()
                .map(spriteDef -> spriteDef.create())
                .collect(Collectors.toList());
        return new TrackingFirer(targets, waitTime);

    }

 
}
