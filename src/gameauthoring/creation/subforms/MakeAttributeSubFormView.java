package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View class for attribute subform. This serves to create text entry view for max/min/isglobal
 * @author Jin An Joe Lilien
 *
 */
public class MakeAttributeSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();
    private String myStartingValueKey = "Starting Value: ";
    private TextEntryView myStartingValue = new TextEntryView(myStartingValueKey, this.getData(), 100,30 , AuthoringView.DEFAULT_ENTRYVIEW);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myStartingValue));

    public MakeAttributeSubFormView () {
        initView();
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);      
        myPane.add(myStartingValue.draw(), 0,0);
    }

 

    public String getMyStartingValueKey () {
        return myStartingValueKey;
    }

    @Override
    public Node draw () {
        return myPane;
    }
}