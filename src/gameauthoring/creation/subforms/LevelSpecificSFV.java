package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import splash.LocaleManager;


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

    @Override
    public void setLevelSpecific (boolean levelSpecific) {
        myChoice.isCheckedProperty().set(levelSpecific);
    }

    @Override
    public boolean isLevelSpecific () {
        return myChoice.isCheckedProperty().get();
    }

    @Override
    public Node draw () {
        HBox box = new HBox(myChoice.draw());
        getMyUIFactory().addStyling(box, getStyleClass());
        return box;
    }

    @Override
    protected void initView () {
        myChoice.setSelected(false);
    }

}
