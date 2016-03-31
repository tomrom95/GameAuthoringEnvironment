package engine;

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

    private SimpleObjectProperty<IMovementModule> myMover;
    private SimpleObjectProperty<IGraphicModule> myGraphic;
    private ObservableList<IModule> myModules;
    private IStatusModule myStatusModule;

    @Override
    public SimpleObjectProperty<IGraphicModule> getDrawer () {
        // TODO Auto-generated method stub
        return null;
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
    public SimpleObjectProperty<SpriteType> getType () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SimpleObjectProperty<Coordinate> getLocation () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SimpleObjectProperty<IMovementModule> getMovementStrategyProperty () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObservableList<IModule> getModulesProperty () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObservableList<IAttribute> getAttributesProperty () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObservableList<IResource> getResourcesProperty () {
        // TODO Auto-generated method stub
        return null;
    }

}
