package gameauthoring;

import java.util.List;


/**
 * 
 * This interface/class is in charge of managing the lists of SpriteListHolders, 
 * and connecting the appropriate SpriteListHolders to the appropriate view controllers
 *      - i.e. Gives the enemies SpriteListHolder to the enemies SpriteEditorController and the enemies Level character selector controller
 *      
 * This may end up being an abstract class (with the given methods private),
 * depending on how other objects need to communicate with it
 * 
 *
 * - Has a List<SpriteListHolder>
 *      - Each SpriteListHolder corresponds to a category of sprites: enemies, defenders, weapons, terrain,
 * obstacles
 * - Has a List<SpriteEditorController>
 *      - Gives each spriteEditorController the corresponding List<SpriteListHolder>
 * - Maybe Has a SpriteListHolderFactory
 * - Maybe instantiates SpriteEditorControllers based on how many SpriteListHolder lists the SpriteListHolder factory 
 * creates. Could store it in a map 
 * 
 * @author Jeremy Schreck
 *
 */
public interface SpriteListHolderManager {

    /**
     * Get the SpriteListHolderManager's list of SpriteListHolders
     * 
     * @return The list of SpriteListHolder objects
     */
    List<SpriteListHolder> getSpriteListHolders ();

    /**
     * Get the SpriteListHolder located at the specified index
     * 
     * @param index The index of the SpriteListHolder of interest
     * @return The SpriteListHolder at the given index
     */
    SpriteListHolder getSpriteListHolderAtIndex (int index);
    
    /**
     * Get the list of SpriteEditorControllers that this manager is in charge of
     * 
     * @return The list of SpriteEditorControllerObjects
     */
    List<SpriteEditorController> getSpriteEditorControllers();
    

}
