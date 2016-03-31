package gameauthoring;

import java.util.List;
import engine.ISprite;

public interface SubFormView extends Glyph{
    
    List<FormData> getData();
    
    void populateWithData(ISprite s);
    
    SubFormController getSubFormController();
    
    SubFormController setSubFormController();

    
}
