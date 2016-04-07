package gameauthoring;

import java.util.List;
import engine.sprite.ISprite;

public interface SubFormView extends Glyph{
    
    List<FormData> getData();
    
    void populateWithData(ISprite s);
    
    SubFormController getSubFormController();
    
    SubFormController setSubFormController();

    
}
