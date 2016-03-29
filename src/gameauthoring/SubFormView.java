package gameauthoring;

import java.util.List;

public interface SubFormView extends Glyph{
    
    public List<FormData> getData();
    
    public void populateWithData(EditableSprite s);
    
    public SubFormController getSubFormController();
    
}
