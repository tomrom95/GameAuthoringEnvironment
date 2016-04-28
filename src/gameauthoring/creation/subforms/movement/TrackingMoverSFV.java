package gameauthoring.creation.subforms.movement;


import engine.SpriteGroup;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TrackingMoverSFV extends SubFormView implements ITrackingMoverSFV{
    
    private HBox myPane;    
    private String mySpeedKey = "Speed: ";
    private String myTargetsKey = "Targets: ";    
    private NumberEntryView mySpeed; 
    private SingleChoiceEntryView<SpriteGroup> myTargets;

    public TrackingMoverSFV(DefinitionCollection<SpriteGroup> groupsList) {
        myTargets = new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, groupsList.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        mySpeed = new NumberEntryView(mySpeedKey, 150, 30,AuthoringView.DEFAULT_ENTRYVIEW);    
        initView();

    }    
    
    @Override
    public SpriteGroup getTargetsCoice(){
        return myTargets.getSelected();
    }

    
    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView(){
        myPane = getMyUIFactory().makeHBox(20, Pos.CENTER, mySpeed.draw(), myTargets.draw());
    }
    
    @Override
    public void populateWithData(double speed, SpriteGroup targets) {
        mySpeed.setData(speed);
        myTargets.setSelected(targets);
    }
    
    @Override
    public double getSpeed(){
        return mySpeed.getData();
    }
    
    
}
