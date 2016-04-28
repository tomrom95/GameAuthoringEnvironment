package gameauthoring.creation.subforms.movement;

import gameauthoring.creation.entryviews.CharacterEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class UserMoverSFV extends SubFormView implements IUserMoverSFV{
    
    private GridPane myPane = new GridPane();
    private String mySpeedKey = "Speed: ";
    private String myUpKey = "Up Key: ";
    private String myDownKey = "Down Key: ";
    private String myRightKey = "Right Key: ";
    private String myLeftKey = "Left Key:  ";
    private NumberEntryView mySpeed = new NumberEntryView(mySpeedKey,  40, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private CharacterEntryView myUp = new CharacterEntryView(myUpKey,  40, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private CharacterEntryView myDown = new CharacterEntryView(myDownKey,  40, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private CharacterEntryView myRight = new CharacterEntryView(myRightKey,  40, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private CharacterEntryView myLeft = new CharacterEntryView(myLeftKey,  40, 30, AuthoringView.DEFAULT_ENTRYVIEW);


    
    public UserMoverSFV () {
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView(){
        myPane.setGridLinesVisible(true);
        myPane.add(mySpeed.draw(), 0, 0);
        myPane.add(myUp.draw(), 0, 1);
        myPane.add(myDown.draw(), 0, 2);
        myPane.add(myLeft.draw(), 1, 0);
        myPane.add(myRight.draw(), 1, 1);
    }
    
    @Override
    public double getSpeed(){
        return mySpeed.getData();
    }
    
    @Override
    public String getUpKey(){
        return myUp.getData();
    }
   
    
    @Override
    public String getDownKey(){
        return myDown.getData();
    }
    
    @Override
    public String getLeftKey(){
        return myLeft.getData();
    }
    
    @Override
    public String getRightKey(){
        return myRight.getData();
    }
    
    @Override
    public void populateWithData(double speed, String up, String down, String left, String right) {
        mySpeed.setData(speed);
        myUp.setData(up);
        myDown.setData(down);
        myLeft.setData(left);
        myRight.setData(right);
    }

}
