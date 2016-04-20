package engine.modules;

import engine.IAdder;
import engine.IAttribute;
import engine.ICheck;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.effects.IEffect;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;

public class UpgradeModule implements IModule {

    private SpriteDefinition myUpgrade;
    private ICheck myCheck;
    private IAdder myAdder;
    private Positionable myParent;
    
    public UpgradeModule (IAdder adder, SpriteDefinition upgrade, ICheck check, Positionable parent) {
        myAdder = adder;
        myUpgrade = upgrade;
        myCheck = check;
        myParent = parent;
    }
    
    @Override
    public void update (TimeDuration duration) {
        //do nothing

    }

    @Override
    public void applyEffect (IEffect effect) {
        //do nothing

    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        //do nothing

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        //do nothing

    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        
        return FXCollections.observableArrayList();
    }

    @Override
    public void registerEvent (GameEvent event) {
       
        if(event.getEventType().equals(EventType.UPGRADE) && myCheck.check()) {
            ISprite upgrade = myUpgrade.create();
            myParent.remove();
            myAdder.bufferedAdd(upgrade, myParent.getLocation());
        }
    }
    
    public boolean isUgradeable () {
        return myCheck.check();
    }

}
