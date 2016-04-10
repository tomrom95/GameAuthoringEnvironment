package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAdder;
import engine.IAttribute;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;

public class SpawningModule implements IModule {

    private IAdder myAdder;
    private IWave myWave;
    
    public SpawningModule (IAdder adder, IWave wave) {
        myAdder = adder;
    }
    
    @Override
    public void update (TimeDuration duration) {
        
        myAdder.add(myWave.getNextSprite());
    }

    @Override
    public void applyEffect (IEffect effect) {
        //Do nothing
        
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        //Do nothing
        
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        //Do nothing
        
    }

    @Override
    public List<IAttribute> getAttributes () {
        return new ArrayList<>();
    }

}
