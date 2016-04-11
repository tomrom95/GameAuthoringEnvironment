package engine;

import java.util.List;


/**
 * This interface represents a component of a game that manages the global attributes that a game
 * can contain
 *
 * Any implementation class will also be responsible for tracking any tied groups of these
 * attributes, and then indirectly maintaining resource behavior be calling update
 * on the resource objects when all attributes are told to update
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IAttributeManager extends Updateable, Affectable {

    List<IResource> getResourceList ();

    void addResource (IResource resource);

}
