package gameauthoring;

import java.util.List;
import java.util.function.Consumer;

/**
 * View wrapper for the form to create new objects
 * in the Creation Views. This would be general for
 * creating sprites, attributes, or interactions
 * @author Tommy
 * @author Jin An
 *
 */
public interface FormView extends Glyph{
    /**
     * Has SpriteEditorController
     * Has save button (e -> stc.editSprite(List<SubFormView>))
     * Has delete button (e -> stc.deleteSprite())
     * Has List<SubFormView>
     */
    
    void setSaveAction(Consumer<?> action);
    
    void setDeleteAction(Consumer<?> action);
    
    List<SubFormView> getSubFormView();
}
