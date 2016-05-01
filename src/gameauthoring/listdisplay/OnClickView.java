package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IGame;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnClickCondition;
import engine.definitions.concrete.EventPackageDefinition;
import javafx.scene.control.ComboBox;


public class OnClickView extends SubConditionView {

    private static final String PATH = "defaults/on_click_tab";
    private ResourceBundle myBundle = ResourceBundle.getBundle(PATH);

    private IGame myGame;
    private ComboBox<SpriteGroup> myGroupA;
    private ComboBox<EventPackageDefinition> myEventsA;
    private ComboBox<SpriteGroup> myGroupB;
    private ComboBox<EventPackageDefinition> myEventsB;
    private ComboBox<EventPackageDefinition> myGlobalEvents;

    public OnClickView (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
    }

    @Override
    protected ICondition subCreation () {
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();
        EventPackageDefinition packageA = myEventsA.getSelectionModel().getSelectedItem();
        EventPackageDefinition packageB = myEventsB.getSelectionModel().getSelectedItem();
        return new OnClickCondition(myGame,
                                    packageA.create(myGroupA.getSelectionModel().getSelectedItem()),
                                    packageB.create(myGroupB.getSelectionModel().getSelectedItem()),
                                    global.create());
    }

    protected void initBoxes () {
        myGroupA = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEventsA =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGroupB = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEventsB =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGlobalEvents =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
    }

    @Override
    protected String getLabelKey (String key) {
        return myBundle.getString(key);
    }

}
