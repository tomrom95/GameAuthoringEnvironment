package gameauthoring;

import java.util.function.Consumer;

public interface FormView extends Glyph{
    /**
     * Has SpriteEditorController
     * Has save button (e -> stc.editSprite(List<SubFormView>))
     * Has delete button (e -> stc.deleteSprite())
     * Has List<SubFormView>
     */
    
    void setSaveAction(Consumer<?> action);
    
    void setDeleteAction(Consumer<?> action);
    
}
