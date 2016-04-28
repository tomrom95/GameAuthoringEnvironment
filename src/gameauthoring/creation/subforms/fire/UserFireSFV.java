package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import splash.LocaleManager;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class UserFireSFV extends SubFormView {

    private GridPane myPane;
    private ResourceBundle myLabel;
    private String myAngleKey;
    private String myWaitTimeKey;
    private String myIncreaseKey;
    private String myDecreaseKey;
    private String myFireKey;
    private String myAngleStepKey;
    private IEntryView myAngle;
    private IEntryView myWaitTime;
    private IEntryView myIncrease;
    private IEntryView myDecrease;
    private IEntryView myFire;
    private IEntryView myAngleStep;

    public UserFireSFV () {
        setResourceBundleAndKey();
        createEntryViews();
        initView();
    }

    private void createEntryViews () {
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
        
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                                           .getInstance().getCurrentLocaleProperty().get());
        myAngleKey = myLabel.getString("AngleKey");
        myWaitTimeKey = myLabel.getString("WaitTimeKey");
        myIncreaseKey = myLabel.getString("IncreaseKey");
        myDecreaseKey = myLabel.getString("DecreaseKey");
        myFireKey = myLabel.getString("FireKey");
        myAngleStepKey = myLabel.getString("AngleStepKey");
        
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
