package gameauthoring;

/**
 * View class for individual sprite list in Sprite Tab. 
 * Has "Show and Edit" button for the user to load specific ListCell on the right
 * Has "Create" button to create a FormView and load it on the right
 * @author Jin An
 *
 */
public interface SpriteListView extends Glyph{
    /**
     * Has SpriteEditorController
     * Has buttons that map cells to SpriteTabController.showAndEdit()
     * Create button (e -> stc.createBlankSprite())
     */
}
