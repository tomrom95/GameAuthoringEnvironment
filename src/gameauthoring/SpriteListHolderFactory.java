package gameauthoring;

import java.util.List;


/**
 * This is in charge of initializing SpriteListHolder objects based on a resource file
 * 
 * Each SpriteListHolder will store a list of possible sprites to create in a certain
 * category (i.e. Enemies, Defenders, Weapons, Terrain, Obstacles).
 * 
 * @author Jeremy Schreck
 *
 */
public interface SpriteListHolderFactory {

    /**
     * Creates a list of sprite holder objects based on a resource file
     * 
     * @return A list of sprite holders
     */
    List<SpriteListHolder> createSpriteHolders ();

}
