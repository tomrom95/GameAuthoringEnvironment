package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import engine.definitions.SpriteDefinition;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * This serves to display firing subform view. It creates a combo box which allows the author to
 * select
 * different types of firing modules
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSubFormView extends SubFormView {

    private ObservableList<ISubFormView> myViews;
    private String myFireTypeKey = "Fire Type: ";
    private ObservableList<ProfileDisplay> myListOfTypes;
    private GridPane myPane = new GridPane();
    private IEntryView myFire;
    private List<IEntryView> myEntryViews;
    private Node mySubFiringView;
    private Node myMissileSelectionView;
    SingleChoiceEntryView<SpriteDefinition> myMissileSelection;
    ObservableList<SpriteDefinition> myMissiles;

    public FiringSubFormView (ObservableList<ISubFormView> views,
                              Consumer<Integer> changeFiringTypeAction,
                              Consumer<SpriteDefinition> changeMissileAction,
                              ObservableList<SpriteDefinition> missiles) {
        this.myViews = views;
        myMissiles = missiles;
        updateEntryViews(changeFiringTypeAction, changeMissileAction, myMissiles);
        initView();
    }

    private void updateEntryViews (Consumer<Integer> changeFiringTypeAction,
                                   Consumer<SpriteDefinition> changeMissileAction,
                                   ObservableList<SpriteDefinition> missiles) {
        myListOfTypes = FXCollections.observableArrayList();

        // TODO add titles to SFCs and pass in titles here
        ProfileDisplay directionalFire = new ProfileDisplay("Directional");
        ProfileDisplay trackingFire = new ProfileDisplay("Tracking");

        myListOfTypes.addAll(directionalFire, trackingFire);
        SingleChoiceEntryView<ProfileDisplay> entryView =

                new SingleChoiceEntryView<ProfileDisplay>(myFireTypeKey, myListOfTypes,
                                                          AuthoringView.DEFAULT_ENTRYVIEW);
        entryView.addComboIndexListener(changeFiringTypeAction);
        mySubFiringView = myViews.get(0).draw();
        myPane.add(mySubFiringView, 0, 1);
        entryView.setSelected(directionalFire);
        myFire = entryView;

        // Missiles
        myMissileSelection =
                new SingleChoiceEntryView<>("Missile", missiles, AuthoringView.DEFAULT_ENTRYVIEW);

        myMissileSelection.addComboItemListener(changeMissileAction);
        myMissileSelectionView = myMissileSelection.draw();

        myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myFire, myMissileSelection));
    }

    public void selectMissile (SpriteDefinition missile) {
        // SpriteDefinition missile = (SpriteDefinition)
        // myMissiles.stream().filter(p->p.getProfile().getName().get().equals(missileName)).toArray()[0];
        myMissileSelection.setSelected(missile);
    }

    public void changeSubMovementView (int index) {
        myPane.getChildren().remove(mySubFiringView);
        mySubFiringView = myViews.get(index).draw();
        myPane.add(mySubFiringView, 0, 1);
    }

    @Override
    protected void initView () {
        myPane.add(myFire.draw(), 0, 0);
        myPane.add(myMissileSelectionView, 1, 0);
    }

    public String getMyMoveTypeKey () {
        return myFireTypeKey;
    }

    @Override
    public Node draw () {
        return myPane;
    }

}
