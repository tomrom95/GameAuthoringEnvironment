package engine.definitions;

import java.util.List;
import java.util.stream.Collectors;
import engine.modules.TrackingFirer;
import engine.sprite.ISprite;


public class TrackingFirerDefinition extends ModuleDefiniton {

    private List<SpriteDefinition> myTargets;

    public TrackingFirerDefinition (List<SpriteDefinition> targets) {
        myTargets = targets;
    }

    public void setTargets (List<SpriteDefinition> targets) {
        myTargets = targets;
    }

    @Override
    public TrackingFirer create () {
        List<ISprite> targets = myTargets.stream()
                .map(spriteDef -> spriteDef.create())
                .collect(Collectors.toList());
        return new TrackingFirer(targets);

    }
}
