package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * Implementation of IMakeAttributeSFV with GridPane Arrangement and NumberEntryView display
 * 
 * @author Jin An
 * @author Joe Lilien
 *
 */
public class MakeAttributeSFV extends SubFormView implements IMakeAttributeSFV {

    private String myStartingValueKey = "Starting Value: ";
    private IEntryView myStartingValue;

    public MakeAttributeSFV () {
        myStartingValue = new NumberEntryView(myStartingValueKey, 100, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    protected void initView () {
    }

    @Override
    public String getMyStartingValueKey () {
        return myStartingValueKey;
    }

    @Override
    public Node draw () {
        return myStartingValue.draw();
    }
}
