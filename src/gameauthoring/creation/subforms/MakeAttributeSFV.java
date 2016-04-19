package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
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

    private GridPane myContainer;
    private String myStartingValueKey = "Starting Value: ";
    private IEntryView myStartingValue;

    public MakeAttributeSFV () {
        myStartingValue = new NumberEntryView(myStartingValueKey, this.getData(), 100, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    protected void initView () {
        myContainer = new GridPane();
        myContainer.add(myStartingValue.draw(), 0, 0);
    }

    @Override
    public String getMyStartingValueKey () {
        return myStartingValueKey;
    }

    @Override
    public Node draw () {
        return myContainer;
    }
}
