package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View class for attribute subform. This serves to create text entry view for max/min/isglobal
 * @author Jin An
 *
 */
public class MakeAttributeSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();
    private String myMaxKey = "Max Value: ";
    private String myMinKey = "Min Value: ";
    private String myIsGlobalKey = "Global or Local Attribute: ";

    //private IEntryView myMaxValue = new TextEntryView(myMaxKey, this.getData(), 20, 150, 30);
   // private IEntryView myMinValue = new TextEntryView(myMinKey, this.getData(), 20, 100, 100);
    private IEntryView myIsGlobal = new TextEntryView(myIsGlobalKey, this.getData(), 20, 150, 90);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(
                                                                                    myIsGlobal));

    public MakeAttributeSubFormView () {
        initView();
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        //myPane.add(myMaxValue.draw(), 0, 0);
        //myPane.add(myMinValue.draw(), 0, 0);
        myPane.add(myIsGlobal.draw(), 1, 0, 1, 1);
    }

    public String getMyMaxKey () {
        return myMaxKey;
    }

    public String getMyMinKey () {
        return myMinKey;
    }

    public String getMyIsGlobalKey () {
        return myIsGlobalKey;
    }

    @Override
    public Node draw () {
        return myPane;
    }
}