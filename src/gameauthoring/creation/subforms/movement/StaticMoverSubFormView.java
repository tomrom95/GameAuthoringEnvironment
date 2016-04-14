package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View representing a sub form that creates the information required to build a static mover
 * module
 * 
 * @author Dhrumil
 *
 */
public class StaticMoverSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();

    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>();

    public StaticMoverSubFormView () {
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);

    }
}
