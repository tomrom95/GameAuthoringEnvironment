package engine;


/**
 * This class represents a resource, or a group of attributes that manages their behavior so as to
 * prevent the current value from going below the minimum or above the maximum
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IResource extends Updateable, Affectable {

    ResourceType getType ();

}
