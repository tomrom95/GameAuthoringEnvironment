package gameauthoring.creation.subforms.fire;

import java.util.List;
import java.util.function.Consumer;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.ObservableList;


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
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;

    public FiringSubFormView (List<ISubFormView> views,
                              Consumer<Integer> changeSelectionAction,
                              List<String> options,
                              Consumer<SpriteDefinition> changeMissileAction,
                              ObservableList<SpriteDefinition> missiles) {
        super(views, changeSelectionAction, options);
        initMissileSelectionView(changeMissileAction, missiles);

    }

    private void initMissileSelectionView (
                                           Consumer<SpriteDefinition> changeMissileAction,
                                           ObservableList<SpriteDefinition> missiles) {

        // Missiles
        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", missiles, AuthoringView.DEFAULT_ENTRYVIEW);

        myMissileSelectionView.addComboItemListener(changeMissileAction);
        getMyGridPane().add(myMissileSelectionView.draw(), 1, 0);

    }

    public void selectMissile (SpriteDefinition missile) {
        myMissileSelectionView.setSelected(missile);
    }

    @Override
    protected void initView () {
        setMyCurrentSubViewX(0);
        setMyCurrentSubViewY(1);
        super.initView();
    }

    @Override
    protected String getSelectionKey () {
        return myFireTypeKey;
    }

}
