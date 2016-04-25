package engine.sprite;

import java.util.List;
import engine.Affectable;
import engine.AttributeManager;
import engine.Drawable;
import engine.IAttribute;
import engine.Positionable;
import engine.IResource;
import engine.IStatus;
import engine.Updateable;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.modules.UpgradeModule;
import util.Bounds;
import util.Coordinate;


/**
 * This interface represents a sprite in a game, that can be updated and can respond to external
 * events and stimuli.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 * @author Jeremy Schreck
 *
 */

public interface ISprite extends Drawable, Updateable, Affectable, Positionable {

    void initialize (IMovementModule movementModule,
                     IGraphicModule graphicModule,
                     UpgradeModule upgradeModule,
                     List<IModule> otherModules,
                     List<IAttribute> attributes,
                     Coordinate coord);

    IMovementModule getMovementStrategy ();

    List<IModule> getModules ();

    void addModule (IModule module);

    List<IResource> getResources ();

    AttributeManager getAttributeManager ();

    Bounds getBounds ();

    SpriteType getType ();

    boolean shouldBeRemoved ();
    
    boolean doesObstruct ();
    
    void setObstruction (boolean value);

    void setPath (List<Coordinate> path);
    
    IStatus getStatusModule();

}
