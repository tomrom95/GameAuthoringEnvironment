package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
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
    private String myProjectileKey = "Projectile: ";
    private String myRangedKey = "Ranged: ";
    private String myRangeValueKey = "Range: ";
    private IEntryView myAngle;
    private IEntryView myWaitTime; 
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private BasicUIFactory myUIFactory= new BasicUIFactory();
    private RemoveOption myRemove;
    private CheckEntryView isRangedSelectionView;

    public DirectionalFireSFV (IDefinitionCollection<SpriteDefinition> missiles, RemoveOption remove) {
        myRemove = remove;
        myAngle = new NumberEntryView(myAngleKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myWaitTime = new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", missiles.getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        isRangedSelectionView =
                new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane = myUIFactory.makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(), myWaitTime.draw(), myAngle.draw(), myRemove.draw(), isRangedSelectionView.draw());
        myPane.getStyleClass().add("firer");
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


    @Override
    public String getMyRangedKey () {
        return myRangedKey;
    }

    @Override
    public String getMyRangeValueKey () {
        // TODO Auto-generated method stub
        return null;
    }

}
