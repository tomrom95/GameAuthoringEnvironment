package engine;

import java.util.function.Consumer;
import effects.IEffect;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.IGraphicModule;
import modules.IModule;
import modules.IMovementModule;
import modules.StatusModule;
import util.Bound;
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
    private ObservableList<ObjectProperty<? extends IModule>> myModules;
    private ObjectProperty<Coordinate> myLocation;
    private ObjectProperty<IStatusModule> myStatusModule;
    private ObjectProperty<AttributeManager> myAttributeManager;
    private ObjectProperty<SpriteType> myType;

    public Sprite () {
        myAttributeManager = new SimpleObjectProperty<>(new AttributeManager());
        myMover = new SimpleObjectProperty<>();
        myGraphic = new SimpleObjectProperty<>();
        initializeRequiredModules();
        myLocation = new SimpleObjectProperty<>(new Coordinate(0,0));
    }

    private void initializeRequiredModules () {
        //TODO will get nulls if something is not put in here
        myModules = FXCollections.observableArrayList();
        myStatusModule = new SimpleObjectProperty<>(new StatusModule());
        myModules.add(myMover);
        myModules.add(myStatusModule);
        myModules.add(myGraphic);
    }

    @Override
    public ObjectProperty<IGraphicModule> getDrawer () {
        return myGraphic;
    }

    @Override
    public void update (TimeDuration duration) {
        myAttributeManager.get().update(duration);
        forAllModules((m) -> m.get().update(duration));
    }

    @Override
    public void applyEffect (IEffect effect) {
       myAttributeManager.get().applyEffect(effect);
       forAllModules((m) -> m.get().applyEffect(effect));
    }

    @Override
    public ObjectProperty<Coordinate> getLocation () {
        return myLocation;
    }

    @Override
    public ObjectProperty<IMovementModule> getMovementStrategyProperty () {
        return myMover;
    }

    @Override
    public ObservableList<ObjectProperty<? extends IModule>> getModulesProperty () {
        return myModules;
    }

    @Override
    public ObservableList<ObjectProperty<IResource>> getResourcesProperty () {
        ObservableList<ObjectProperty<IResource>> resources = FXCollections.observableArrayList();
        resources.addAll(myAttributeManager.get().getResourceList());
        return resources;
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        myAttributeManager.get().registerKeyEvent(event);
        forAllModules((m) -> m.get().registerKeyEvent(event));

    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        myAttributeManager.get().registerMouseEvent(event);
        forAllModules((m) -> m.get().registerMouseEvent(event));
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        ObservableList<ObjectProperty<IAttribute>> attributes = FXCollections.observableArrayList();
        attributes.addAll(myAttributeManager.get().getAttributes());
        forAllModules((m) -> attributes.addAll(m.get().getAttributes()));
        return attributes;
    }
    
    private void forAllModules(Consumer<? super ObjectProperty<? extends IModule>> action) {
        myModules.forEach(action);
    }
    
    @Override
    public Bound getBounds () {
        double x = getLocation().get().getX();
        double y = getLocation().get().getY();
        double width = getDrawer().get().getGraphic().getWidth().get();
        double height = getDrawer().get().getGraphic().getHeight().get();
        return new Bound(x, y, width, height);
    }

    @Override
    public ObjectProperty<SpriteType> getType () {
        return myType;
    }
}
