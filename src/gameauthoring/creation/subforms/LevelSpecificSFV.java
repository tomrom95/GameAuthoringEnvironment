package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import splash.LocaleManager;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;


/**
 * Implementation of ILevelSpecificSFV using a CheckEntryView
 * 
 * @author Joe Lilien
 *
 */
public class LevelSpecificSFV extends SubFormView implements ILevelSpecificSFV {

    private CheckEntryView myChoice;   
    private ResourceBundle myLabel;
    private String myChoiceKey;

    public LevelSpecificSFV () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myChoiceKey = myLabel.getString("AssociateResourceWithLevelChoice");
        myChoice = new CheckEntryView(myChoiceKey, AuthoringView.DEFAULT_ENTRYVIEW);
    }

    public void setLevelSpecific (boolean levelSpecific) {
        myChoice.isCheckedProperty().set(levelSpecific);
    }

    @Override
    public boolean isLevelSpecific () {
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
