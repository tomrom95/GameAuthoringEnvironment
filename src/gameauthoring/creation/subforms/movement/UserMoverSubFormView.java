package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class UserMoverSubFormView extends SubFormView {
    
    private GridPane myPane = new GridPane();

    private String mySpeedKey = "Speed: ";
    private String myUpKey = "Up Key: ";
    private String myDownKey = "Down Key: ";
    private String myRightKey = "Right Key: ";
    private String myLeftKey = "Left Key:  ";
    private IEntryView mySpeed = new TextEntryView(mySpeedKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myUp = new TextEntryView(myUpKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myDown = new TextEntryView(myDownKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myRight = new TextEntryView(myRightKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myLeft = new TextEntryView(myLeftKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(mySpeed, myLeft, myRight, myUp, myDown));


    
    public UserMoverSubFormView () {
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    /*
     * jeremy, this feels like it should be coming from the super class?
     */
    private void initView(){
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(mySpeed.draw(), 0, 0);
        myPane.add(myUp.draw(), 0, 1);
        myPane.add(myDown.draw(), 0, 2);
        myPane.add(myLeft.draw(), 1, 0);
        myPane.add(myRight.draw(), 1, 1);
    }
    
    public String getSpeedKey(){
        return mySpeedKey;
    }
    
    public String getUpKey(){
        return myUpKey;
    }
   
    public String getDownKey(){
        return myDownKey;
    }
    public String getLeftKey(){
        return myLeftKey;
    }
    
    public String getRightKey(){
        return myRightKey;
    }

}
