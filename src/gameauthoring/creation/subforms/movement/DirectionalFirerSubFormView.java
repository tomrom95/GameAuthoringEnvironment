package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * View representing a subform that creates the information required to build a directional mover
 * module
 * 
 * @author Timko
 *
 */
public class DirectionalFirerSubFormView extends SubFormView {

    private GridPane myPane = new GridPane();
    
    private String myWaitTimeKey = "Wait Time: ";
    private String myAngleKey = "Firing Angle: "; 
    
 

    private IEntryView myWaitTime = new TextEntryView(myWaitTimeKey, this.getData(), 20, 150, 30);
    private IEntryView myAngle = new TextEntryView(myAngleKey, this.getData(), 20, 150, 30);

    private List<IEntryView> myEntryViews =
            new ArrayList<IEntryView>(Arrays.asList(myWaitTime, myAngle));

    public DirectionalFirerSubFormView () {
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(myWaitTime.draw(), 0, 0);
        myPane.add(myAngle.draw(), 0, 1);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }

}
