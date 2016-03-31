package gameauthoring;

import java.util.List;

public interface SubFormView extends Glyph{
    
    List<FormData> getData();
    
    void populateWithData(EditableSprite s);
    
    SubFormController getSubFormController();
    
}
