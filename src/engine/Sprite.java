package engine;

import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class represents a generic sprite, and allows for specific functionality via adding
 * different modules. Each sprite has a sense of location/movement, graphics, and status.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public class Sprite implements ISprite {

    private ObjectProperty<IMovementModule> myMover;
    private ObjectProperty<IGraphicModule> myGraphic;
    private ObservableList<IModule> myModules;
    private IStatusModule myStatusModule;
    
    public Sprite () { 
        //Initialized for testing
        myGraphic = new SimpleObjectProperty<>();
        myMover = new SimpleObjectProperty<>();
        
    }
    
    @Override
    public ObjectProperty<IGraphicModule> getDrawer () {
        return myGraphic;
    }
    @Override
    public void update (TimeDuration duration) {
        // TODO Auto-generated method stub
        myMover.get().update(duration);
    }
    @Override
    public void applyEffect (IEffect effect) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public ObjectProperty<IProfile> getProfileProperty () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ObjectProperty<Coordinate> getLocation () {
        return myMover.get().getLocationProperty();
    }
    @Override
    public ObjectProperty<IMovementModule> getMovementStrategyProperty () {
        // TODO Auto-generated method stub
        return myMover;
    }
    @Override
    public ObservableList<ObjectProperty<IModule>> getModulesProperty () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributesProperty () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ObservableList<ObjectProperty<IResource>> getResourcesProperty () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        myMover.get().registerKeyEvent(event);
        
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        // TODO Auto-generated method stub
        return null;
    }

    
}
