package gameauthoring.creation.subforms.movement;


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
    
    private String mySpeedKey = "Speed: ";
    private String myTargetsKey = "Targets: ";
    
    private IEntryView mySpeed; 
    private SingleChoiceEntryView<SpriteGroup> myTargets;

    public TrackingMoverSFV(DefinitionCollection<SpriteGroup> groupsList) {
        myTargets = new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, groupsList.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        mySpeed = new TextEntryView(mySpeedKey, this.getData(), 150, 30,AuthoringView.DEFAULT_ENTRYVIEW);    
        initView();

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
