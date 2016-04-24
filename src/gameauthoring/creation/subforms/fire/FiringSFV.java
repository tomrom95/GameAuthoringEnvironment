package gameauthoring.creation.subforms.fire;

import java.util.List;
import java.util.function.Consumer;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.UIFactory;
import javafx.collections.ObservableList;


/**
 * This serves to display firing subform view.
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSFV extends DynamicSubFormView {

    private String myFireTypeKey = "Fire Type: ";
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;


    /**
     * Constructor
     * 
     * @param views The sub-subformviews representing different types of firing
     * @param changeSelectionAction The method to call when user selects a different firing type
     * @param options The titles of the different firing options
     * @param changeMissileAction The method to call when a user selects a different missile 
     * @param missiles The list of possible missiles to select
     */
    public FiringSFV (List<ISubFormView> views,
                              Consumer<Integer> changeSelectionAction,
                              List<String> options,
                              Consumer<SpriteDefinition> changeMissileAction,
                              ObservableList<SpriteDefinition> missiles) {
        super(views, changeSelectionAction, options);
        initMissileSelectionView(changeMissileAction, missiles);

    }

    /**
     * Initializes the view that allows a user to select a missile for the firing module
     * 
     * @param changeMissileAction The method to call when the user selects a different missile
     * @param missiles The list of possible missiles to select
     */
    private void initMissileSelectionView (
                                           Consumer<SpriteDefinition> changeMissileAction,
                                           ObservableList<SpriteDefinition> missiles) {

        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", missiles, AuthoringView.DEFAULT_ENTRYVIEW);

        myMissileSelectionView.addComboItemListener(changeMissileAction);
        getMyGridPane().add(myMissileSelectionView.draw(), 1, 0);

    }

    /**
     * This method changes which missile is currently selected
     * 
     * @param missile The missile to select
     */
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
