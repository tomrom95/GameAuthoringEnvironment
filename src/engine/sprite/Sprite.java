package engine.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import engine.Affectable;
import engine.AttributeManager;
import engine.IAttribute;
import engine.IResource;
import engine.IStatus;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.modules.SpriteStatus;
import engine.modules.StaticMover;
import javafx.collections.ObservableList;
import util.Bounds;
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
public class Sprite extends DefaultAffectable implements ISprite {

    private SpriteType myType;
    private IMovementModule myMover;
    private IGraphicModule myGraphic;
    private List<IModule> myOtherModules;
    private Coordinate myLocation;
    private IStatus myStatus;
    private AttributeManager myAttributeManager;

    public Sprite (SpriteType type) {
        // TODO add default constructions for some modules so there aren't nulls
        myType = type;
        myStatus = new SpriteStatus();
        myAttributeManager = new AttributeManager();
        myOtherModules = new ArrayList<>();
        myMover = new StaticMover(this);

    }

    @Override
    public void initialize (IMovementModule movementModule,
                            IGraphicModule graphicModule,
                            List<IModule> otherModules,
                            List<IAttribute> attributes,
                            Coordinate coord) {
        myMover = movementModule;
        myGraphic = graphicModule;
        myOtherModules = otherModules;
        myLocation = coord;
    }

    @Override
    public IGraphicModule getDrawer () {
        return myGraphic;
    }

    @Override
    public void update (TimeDuration duration) {
        applyToAffectable(module -> module.update(duration));
    }

    @Override
    public void applyEffect (IEffect effect) {
        applyToAffectable(a -> a.applyEffect(effect));
    }

    private void applyToAffectable (Consumer<Affectable> function) {
        function.accept(myMover);
        function.accept(myGraphic);
        function.accept(myAttributeManager);
        function.accept(myStatus);
        function.accept(myMover);
        function.accept(myGraphic);
        myOtherModules.forEach(m -> function.accept(m));
    }

    @Override
    public Coordinate getLocation () {
        return myLocation;
    }

    @Override
    public IMovementModule getMovementStrategy () {
        return myMover;
    }

    @Override
    public List<IResource> getResources () {
        return myAttributeManager.getResourceList();
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
    public ObservableList<IAttribute> getAttributes () {
        return myAttributeManager.getAttributes();
    }

    @Override
    public Bounds getBounds () {
        double x = getLocation().getX();
        double y = getLocation().getY();
        double width = getDrawer().getGraphic().getWidth().get();
        double height = getDrawer().getGraphic().getHeight().get();
        return new Bounds(x, y, width, height);
    }

    @Override
    public SpriteType getType () {
        return myType;
    }

    @Override
    public AttributeManager getAttributeManager () {
        return myAttributeManager;
    }

    @Override
    public void setLocation (Coordinate location) {
        myLocation = location;
    }

    protected void setMovementModule (IMovementModule mover) {
        myMover = mover;
    }

    @Override
    public void registerEvent (GameEvent event) {
       applyToAffectable(a -> a.registerEvent(event));
    }

    @Override
    public boolean shouldBeRemoved () {
        return myStatus.shouldBeRemoved();
    }

    @Override
    public List<IModule> getModules () {
        return myOtherModules;
    }

    @Override
    public void addModule (IModule module) {
        myOtherModules.add(module);
    }
    
    

}
