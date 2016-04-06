package gameauthoring.object_creation_tab;

import javafx.scene.control.Tab;
import gameauthoring.Glyph;


public abstract class SubTabEditorView implements Glyph {

    protected Tab myTab;
    
    public SubTabEditorView (Tab tab){
        myTab = tab;
    }
    
    public Tab getTab(){
        return myTab;
    }
}
