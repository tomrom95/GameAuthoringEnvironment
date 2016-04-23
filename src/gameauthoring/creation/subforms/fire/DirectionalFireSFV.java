package gameauthoring.creation.subforms.fire;

import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.UIFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
    private String myProjectileKey = "Projectile: ";
    private IEntryView myAngle;
    private IEntryView myWaitTime; 
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private UIFactory myUIFactory= new UIFactory();

    public DirectionalFireSFV (IDefinitionCollection<SpriteDefinition> missiles, EventHandler<ActionEvent> e) {
        myAngle = new NumberEntryView(myAngleKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime = new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", missiles.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane = myUIFactory.makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(), myWaitTime.draw(), myAngle.draw());
    }

    @Override
    public String getMyProjectileKey () {
        return myProjectileKey;
    }

    @Override
    public String getMyAngleKey () {
        return myAngleKey;
    }

    @Override
    public String getMyWaitTimeKey () {
        return myWaitTimeKey;
    }
    
    @Override
    public SpriteDefinition getMissileSelection () {
        return myMissileSelectionView.getSelected();
    }

}
