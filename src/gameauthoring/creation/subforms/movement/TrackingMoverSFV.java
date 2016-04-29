package gameauthoring.creation.subforms.movement;


import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.SpriteGroup;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class TrackingMoverSFV extends SubFormView {
    
    private GridPane myPane = new GridPane();
    private ResourceBundle myLabel;
    private String mySpeedKey;
    private String myTargetsKey;
    
    private IEntryView mySpeed; 
    private SingleChoiceEntryView<SpriteGroup> myTargets;

    public TrackingMoverSFV(DefinitionCollection<SpriteGroup> groupsList) {
        setResourceBundleAndKey();
        myTargets = new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, groupsList.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        mySpeed = new TextEntryView(mySpeedKey, this.getData(), 150, 30,AuthoringView.DEFAULT_ENTRYVIEW);    
        initView();

    }    
    
    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                                           .getInstance().getCurrentLocaleProperty().get());
        mySpeedKey = myLabel.getString("SpeedKey");
        myTargetsKey = myLabel.getString("TargetsKey");
    }

    public SingleChoiceEntryView<SpriteGroup> getTargetsCoice(){
        return myTargets;
    }

    
    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView(){
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
