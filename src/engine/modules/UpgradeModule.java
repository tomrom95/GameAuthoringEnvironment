package engine.modules;

import engine.IAdder;
import engine.IAttribute;
import engine.ICheck;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.ObservableList;
import util.TimeDuration;

public class UpgradeModule implements IModule {

    private SpriteDefinition myUpgrade;
    private ICheck myCheck;
    private IAdder myAdder;
    
    public UpgradeModule (IAdder adder, SpriteDefinition upgrade, ICheck check) {
        myUpgrade = upgrade;
        myCheck = check;
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
