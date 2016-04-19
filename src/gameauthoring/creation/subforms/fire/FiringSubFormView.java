package gameauthoring.creation.subforms.fire;

import java.util.List;
import java.util.function.Consumer;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 * This serves to display firing subform view. It creates a combo box which allows the author to
 * select
 * different types of firing modules
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSubFormView extends DynamicSubFormView {

    private String myFireTypeKey = "Fire Type: ";
    private Node myMissileSelectionView;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelection;
    private ObservableList<SpriteDefinition> myMissiles;
    private Consumer<SpriteDefinition> myChangeMissileAction;

    public FiringSubFormView (List<ISubFormView> views,
                              Consumer<Integer> changeSelectionAction,
                              List<String> options,
                              Consumer<SpriteDefinition> changeMissileAction,
                              ObservableList<SpriteDefinition> missiles) {
        super(views, changeSelectionAction, options);
        myChangeMissileAction = changeMissileAction;
        myMissiles = missiles;
    }

    @Override
    protected void initEntryViews (Consumer<Integer> action, List<String> options) {
        super.initEntryViews(action, options);
        initMissileSelectionView(myChangeMissileAction, myMissiles);
    }

    private void initMissileSelectionView (
                                           Consumer<SpriteDefinition> changeMissileAction,
                                           ObservableList<SpriteDefinition> missiles) {

        // Missiles
        myMissileSelection =
                new SingleChoiceEntryView<>("Missile", missiles, AuthoringView.DEFAULT_ENTRYVIEW);

        myMissileSelection.addComboItemListener(changeMissileAction);
        myMissileSelectionView = myMissileSelection.draw();

    }

    public void selectMissile (SpriteDefinition missile) {
        myMissileSelection.setSelected(missile);
    }

    @Override
    protected void initView () {
        setMyCurrentSubViewX(0);
        setMyCurrentSubViewY(1);
        super.initView();
        getMyGridPane().add(myMissileSelectionView, 1, 0);

    }

    @Override
    protected String getSelectionKey () {
        return myFireTypeKey;
    }

}
