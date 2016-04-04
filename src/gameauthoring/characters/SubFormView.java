package gameauthoring.characters;

import java.util.List;
import engine.ISprite;
import gameauthoring.Glyph;

public interface SubFormView extends Glyph{
    
    List<FormData> getData();
    
    void populateWithData(ISprite s);
    
    SubFormController getSubFormController();
    
    SubFormController setSubFormController();

    
}
