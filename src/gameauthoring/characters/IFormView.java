package gameauthoring.characters;

import java.util.List;
import java.util.function.Consumer;
import gameauthoring.Glyph;

/**
 * View wrapper for the form to create new objects
 * in the Creation Views. This would be general for
 * creating sprites, attributes, or interactions
 * @author Tommy
 * @author Jin An
 *
 */
public interface IFormView extends Glyph{
    /**
     * Has SpriteEditorController
     * Has save button (e -> stc.editSprite(List<SubFormView>))
     * Has delete button (e -> stc.deleteSprite())
     * Has List<SubFormView>
     */
    
    void setSaveAction(Consumer<?> action);
    
    void setDeleteAction(Consumer<?> action);
    
    List<EntryView> getSubFormView();
}
