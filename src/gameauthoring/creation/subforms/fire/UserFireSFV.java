package gameauthoring.creation.subforms.fire;

import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class UserFireSFV extends SubFormView {

    private GridPane myPane;
    private String myAngleKey = "Angle: ";
    private String myWaitTimeKey = "Wait Time: ";
    private String myIncreaseKey = "Increase Angle Key: ";
    private String myDecreaseKey = "Decrease Angle Key: ";
    private String myFireKey = "Fire Missile Key: ";
    private String myAngleStepKey = "Angle Step Increment: ";
    private IEntryView myAngle;
    private IEntryView myWaitTime;
    private IEntryView myIncrease;
    private IEntryView myDecrease;
    private IEntryView myFire;
    private IEntryView myAngleStep;

    public UserFireSFV () {
        myAngle =
                new NumberEntryView(myAngleKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myIncrease = new TextEntryView(myIncreaseKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        
        myDecrease = new TextEntryView(myDecreaseKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        
        myFire = new TextEntryView(myFireKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        
        myAngleStep = new NumberEntryView(myAngleStepKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane = new GridPane();
        myPane.setGridLinesVisible(true);
        myPane.add(myAngle.draw(), 0, 0);
        myPane.add(myWaitTime.draw(), 0, 1);
        myPane.add(myAngleStep.draw(), 0, 2);
        myPane.add(myIncrease.draw(), 1, 0);
        myPane.add(myDecrease.draw(), 1, 1);
        myPane.add(myFire.draw(), 1, 2);
        
    }

}
