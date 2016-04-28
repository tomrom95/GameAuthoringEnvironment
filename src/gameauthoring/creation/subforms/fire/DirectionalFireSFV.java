package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * View representing a subform that creates the information required to build a directional mover
 * module
 * 
 * @author Dhrumil
 *
 */
public class DirectionalFireSFV extends SubFormView implements IDirectionalFireSFV{

    private HBox myPane;
    private String myAngleKey = "Angle: ";
    private String myWaitTimeKey = "Wait Time: ";
    private String myMissileKey = "Missile: ";
    private NumberEntryView myAngle;
    private NumberEntryView myWaitTime; 
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private RemoveOption myRemove;

    public DirectionalFireSFV (IDefinitionCollection<SpriteDefinition> missiles, RemoveOption remove) {
        myRemove = remove;
        myAngle = new NumberEntryView(myAngleKey, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime = new NumberEntryView(myWaitTimeKey, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>(myMissileKey, missiles.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane = getMyUIFactory().makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(), myWaitTime.draw(), myAngle.draw(), myRemove.draw());
        myPane.getStyleClass().add("firer");
    }


    @Override
    public double getMyAngle() {
        return myAngle.getData();
    }

    @Override
    public double getMyWaitTime () {
        return myWaitTime.getData();
    }
    
    @Override
    public SpriteDefinition getMissileSelection () {
        return myMissileSelectionView.getSelected();
    }

    @Override
    public void populateWithData (SpriteDefinition missile, double angle, double waitTime) {        
        myMissileSelectionView.setSelected(missile);
        myAngle.setData(angle);
        myWaitTime.setData(waitTime);
    }

}
