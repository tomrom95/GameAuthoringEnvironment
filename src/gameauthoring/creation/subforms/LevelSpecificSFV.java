package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;

/**
 * Implementation of ILevelSpecificSFV using a CheckEntryView
 * 
 * @author Joe Lilien
 *
 */
public class LevelSpecificSFV extends SubFormView implements ILevelSpecificSFV{
    
    private CheckEntryView myChoice;
    private String myChoiceKey = "Associate Resource With Specific Levels: ";
    
    public LevelSpecificSFV(){
        myChoice = new CheckEntryView(myChoiceKey,AuthoringView.DEFAULT_ENTRYVIEW);        
    }

    public void setLevelSpecific(boolean levelSpecific){
        myChoice.isCheckedProperty().set(levelSpecific);
    }
    @Override
    public boolean isLevelSpecific(){
        return myChoice.isCheckedProperty().get();
    }
    
    @Override
    public Node draw () {
        return myChoice.draw();
    }

    @Override
    protected void initView () { 
        myChoice.setSelected(false);
    }

}
