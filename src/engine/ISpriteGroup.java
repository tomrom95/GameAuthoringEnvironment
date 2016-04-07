package engine;

import javafx.collections.ObservableList;

/**
 * This interface represents a group of sprites that are delineated to hold similar behavior.
 * 
 * Any class implementation of this prives access to the sprites that are contained in this group.

 */

public interface ISpriteGroup {
    
    boolean contains (SpriteType spriteType);
    
    ObservableList<SpriteType> getSpriteTypes ();

}
