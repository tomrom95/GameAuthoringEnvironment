package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IGame;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnTimeCondition;
import engine.definitions.concrete.EventPackageDefinition;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.StringParser;
import util.TimeDuration;


public class OnTimeView extends SubConditionView {

    private static final String PATH = "defaults/on_time_view";
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);

    private IGame myGame;
    private TextField myDuration;
    private ComboBox<SpriteGroup> myGroup;
    private ComboBox<EventPackageDefinition> myEvent;
    private ComboBox<EventPackageDefinition> myGlobalEvent;

    public OnTimeView (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
    }

    @Override
    protected void initBoxes () {
        myDuration = createTextField();
        myGroup = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEvent = createComboBox(getEvents());
        myGlobalEvent = createComboBox(getEvents());
    }

    private ObservableList<EventPackageDefinition> getEvents () {
        return myGame.getAuthorshipData().getMyCreatedEventPackages().getItems();
    }

    @Override
    protected ICondition subCreation () throws NumberFormatException {
        EventPackageDefinition sprite = myEvent.getSelectionModel().getSelectedItem();
        EventPackageDefinition global = myGlobalEvent.getSelectionModel().getSelectedItem();
        return new OnTimeCondition(myGame, getTime(),
                                   sprite.create(myGroup.getSelectionModel().getSelectedItem()),
                                   global.create());
    }
    
    private TimeDuration getTime () throws NumberFormatException {
        return new TimeDuration(new StringParser().parseDouble(myDuration.getText())); 
    }

    @Override
    protected String getLabelKey (String key) {
        return myBundle.getString(key);
    }

}
