package engine.sprite;

import java.util.function.Consumer;
import engine.Affectable;
import engine.AttributeManager;
import engine.IAttribute;
import engine.IResource;
import engine.IStatusModule;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.modules.StatusModule;
import graphics.Block;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Bounds;
import util.Coordinate;
import util.RGBColor;
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
    private ObjectProperty<IProfile> myProfile;
    private ObservableList<ObjectProperty<? extends IModule>> myModules;
    private ObjectProperty<Coordinate> myLocation;
    private ObjectProperty<IStatusModule> myStatusModule;
    private ObjectProperty<AttributeManager> myAttributeManager;
    private ObjectProperty<SpriteType> myType;

    public Sprite () {
        myAttributeManager = new SimpleObjectProperty<>(new AttributeManager());
        myMover = new SimpleObjectProperty<>();
        myGraphic = new SimpleObjectProperty<>(new GraphicModule(new Block(0, 0, RGBColor.BLACK)));
        initializeRequiredModules();
        myLocation = new SimpleObjectProperty<>(new Coordinate(0, 0));
        myProfile = new SimpleObjectProperty<>(new Profile());
    }

    private void initializeRequiredModules () {
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
        myModules.forEach(m -> m.get().update(duration));
    }

    @Override
    public void applyEffect (IEffect effect) {
        applyToAffectable(a -> a.applyEffect(effect));
    }

    private void applyToAffectable (Consumer<Affectable> function) {
        function.accept(myAttributeManager.get());
        myModules.forEach(m -> function.accept(m.get()));

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

        applyToAffectable(a -> a.registerKeyEvent(event));

    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {

        applyToAffectable(a -> a.registerMouseEvent(event));

    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {

        ObservableList<ObjectProperty<IAttribute>> attributes = FXCollections.observableArrayList();
        applyToAffectable(a -> attributes.addAll(a.getAttributes()));
        return attributes;
    }

    @Override
    public Bounds getBounds () {
        double x = getLocation().get().getX();
        double y = getLocation().get().getY();
        double width = getDrawer().get().getGraphic().getWidth().get();
        double height = getDrawer().get().getGraphic().getHeight().get();
        return new Bounds(x, y, width, height);
    }

    @Override
    public ObjectProperty<SpriteType> getType () {
        return myType;
    }

    @Override
    public ObjectProperty<IProfile> getProfile () {
        return myProfile;
    }

 
   
}
