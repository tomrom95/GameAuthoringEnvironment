package gameauthoring;

import java.util.List;


/**
 * This may end up being an abstract class (with the given methods private),
 * depending on how other objects need to communicate with it
 * 
 * - Has a List<SpriteHolder>
 *      - Each SpriteHolder corresponds to a category of sprites: enemies, defenders, weapons, terrain,
 * obstacles
 * - Has a List<SpriteEditorController>
 *      - Gives each spriteEditorController the corresponding List<SpriteHolder>
 * - Maybe it instantiates SpriteEditorControllers based on how many SpriteHolder lists the SpriteListHolder factory 
 * creates. Could store it in a map 
 * 
 * @author Jeremy Schreck
 *
 */
public interface SpriteEditorControllerManager {

    /**
     * Get the SpriteHolderManager's list of SpriteHolders
     * 
     * @return The list of SpriteHolder objects
     */
    List<EditableSpriteListHolder> getSpriteHolders ();

    /**
     * Get the SpriteHolder located at the specified index
     * 
     * @param index The index of the SpriteHolder of interest
     * @return The SpriteHolder at the given index
     */
    EditableSpriteListHolder getSpriteHolderAtIndex (int index);
    
    /**
     * Get the list of SpriteEditorControllers that this manager is in charge of
     * 
     * @return The list of SpriteEditorControllerObjects
     */
    List<SpriteEditorController> getSpriteEditorControllers();
    

}
