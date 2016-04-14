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

    public FiringSubFormView (ObservableList<ISubFormView> views,
                              Consumer<Integer> changeFiringTypeAction,
                              Consumer<SpriteDefinition> changeMissileAction,
                              ObservableList<SpriteDefinition> missiles) {
        this.myViews = views;
        updateEntryViews(changeFiringTypeAction, changeMissileAction, missiles);
        initView();
    }

    private void updateEntryViews (Consumer<Integer> changeFiringTypeAction,Consumer<SpriteDefinition> changeMissileAction,  ObservableList<SpriteDefinition> missiles) {
        myListOfTypes = FXCollections.observableArrayList();

        // TODO add titles to SFCs and pass in titles here
        ProfileDisplay directionalFire = new ProfileDisplay("Directional");
        ProfileDisplay trackingFire = new ProfileDisplay("Tracking");

        myListOfTypes.addAll(directionalFire, trackingFire);
        SingleChoiceEntryView<ProfileDisplay> entryView =
                new SingleChoiceEntryView<ProfileDisplay>(myFireTypeKey, myListOfTypes, 20);
        entryView.addComboIndexListener(changeFiringTypeAction);
        mySubFiringView = myViews.get(0).draw();
        myPane.add(mySubFiringView, 0, 1);
        entryView.setSelected(directionalFire);
        myFire = entryView;

        // Missiles
        SingleChoiceEntryView<SpriteDefinition> myMissileSelection = new SingleChoiceEntryView<>("Missile", missiles, 20);
        myMissileSelection.addComboItemListener(changeMissileAction);
        myMissileSelectionView = myMissileSelection.draw();

        myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myFire, myMissileSelection));
    }

    public void changeSubMovementView (int index) {
        myPane.getChildren().remove(mySubFiringView);
        mySubFiringView = myViews.get(index).draw();
        myPane.add(mySubFiringView, 0, 1);
    }

    private void initView () {
        // TODO: Whats the point of setting entry views?
        super.setMyEntryViews(myEntryViews);
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
