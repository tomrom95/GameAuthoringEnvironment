package gameauthoring;

import java.util.List;

public interface ISpriteEditorController {
    /**
     * Has an observable list of sprites
     */
    void setSprites(SpriteListHolder sprites);
    
    void showAndEdit();
    
    //void createBlankSprite(); // Empty sprite
    
    void deleteSprite();
    
    void editSprite(List<SubFormView> subForms); 
    
    void createSprite();
}
