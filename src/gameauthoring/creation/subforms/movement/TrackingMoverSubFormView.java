package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class TrackingMoverSubFormView extends SubFormView {
    
    private GridPane myPane = new GridPane();
    
    private String mySpeedKey = "Speed: ";
    private String myTargetsKey = "Targets: ";
    
    private IEntryView mySpeed = new TextEntryView(mySpeedKey, this.getData(), 20, 150, 30);
    private IEntryView myTargets = new TextEntryView(myTargetsKey, this.getData(), 20, 150, 30);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(mySpeed, myTargets));
    
    public TrackingMoverSubFormView(){
        initView();
    }

    
    @Override
    public Node draw () {
        // TODO Auto-generated method stub
        return myPane;
    }

    private void initView(){
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(mySpeed.draw(), 0, 0);
        myPane.add(myTargets.draw(), 0, 1);
    }
    
    public String getSpeedKey(){
        return mySpeedKey;
    }
    
    public String getTargetsKey(){
        return myTargetsKey;
    }
    
}
