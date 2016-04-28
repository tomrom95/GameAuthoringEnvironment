package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IGame;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnTimeCondition;
import engine.conditions.OutOfBoundsCondition;
import engine.definitions.concrete.EventPackageDefinition;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OnOutOfBoundsView extends SubConditionView {

    private static final String PATH = "defaults/out_of_bounds";
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);

    private IGame myGame;
    private ComboBox<SpriteGroup> mySelf;
    private ComboBox<EventPackageDefinition> myEvent;
    private ComboBox<SpriteGroup> myOther;
    private ComboBox<EventPackageDefinition> myOtherEvent;
    private ComboBox<EventPackageDefinition> myGlobalEvent;

    public OnOutOfBoundsView (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
    }
    
    @Override
    protected void initBoxes () {
        mySelf = createComboBox(getGroups());
        myEvent = createComboBox(getEvents());
        myOther = createComboBox(getGroups());
        myOtherEvent = createComboBox(getEvents());
        myGlobalEvent = createComboBox(getEvents());
    }
    
    @Override
    protected ICondition subCreation () {
        EventPackageDefinition self = myEvent.getSelectionModel().getSelectedItem();
        EventPackageDefinition other = myOtherEvent.getSelectionModel().getSelectedItem();
        EventPackageDefinition global = myGlobalEvent.getSelectionModel().getSelectedItem();
        return new OutOfBoundsCondition(myGame, self.create(mySelf.getSelectionModel().getSelectedItem()),
                                   other.create(myOther.getSelectionModel().getSelectedItem()),
                                   global.create());
    }

    private ObservableList<EventPackageDefinition> getEvents () {
        return myGame.getAuthorshipData().getMyCreatedEventPackages().getItems();
    }

    private ObservableList<SpriteGroup> getGroups () {
        return myGame.getAuthorshipData().getMyCreatedGroups().getItems();
    }

    @Override
    protected String getLabelKey (String key) {
        return myBundle.getString(key);
    }
}
