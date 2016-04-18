package engine.modules;

import engine.AttributeManager;
import engine.AttributeType;
import engine.Check;
import engine.IAttribute;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.ObservableList;
import util.TimeDuration;

public class UpgradeModule implements IModule {

    public UpgradeModule (SpriteDefinition upgrade, Check check) {
        
    }
    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applyEffect (IEffect effect) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerEvent (GameEvent event) {
        // TODO Auto-generated method stub

    }

}
