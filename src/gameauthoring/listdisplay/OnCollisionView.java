package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IGame;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnCollisionCondition;
import engine.definitions.concrete.EventPackageDefinition;
import javafx.scene.control.ComboBox;


/**
 * Need to refractor heavily, crunched for demo
 *
 */

public class OnCollisionView extends SubConditionView {

    private static final String PATH = "defaults/on_collision_tab";
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);

    private IGame myGame;
    private ComboBox<SpriteGroup> myGroupA;
    private ComboBox<EventPackageDefinition> myEventsA;
    private ComboBox<SpriteGroup> myGroupB;
    private ComboBox<EventPackageDefinition> myEventsB;
    private ComboBox<SpriteGroup> myThirdParty;
    private ComboBox<EventPackageDefinition> myThirdEvents;
    private ComboBox<EventPackageDefinition> myGlobalEvents;

    public OnCollisionView (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();

    }

    @Override
    protected void initBoxes () {
        myGroupA = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myGroupB = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myThirdParty = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEventsA =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myEventsB =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myThirdEvents =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGlobalEvents =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
    }

    @Override
    protected ICondition subCreation () {
        EventPackageDefinition packageA = myEventsA.getSelectionModel().getSelectedItem();
        EventPackageDefinition packageB = myEventsB.getSelectionModel().getSelectedItem();
        EventPackageDefinition other = myThirdEvents.getSelectionModel().getSelectedItem();
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();
        return new OnCollisionCondition(myGame, packageA
                .create(myGroupA.getSelectionModel().getSelectedItem()),
                                        packageB.create(myGroupB.getSelectionModel()
                                                .getSelectedItem()),
                                        other.create(myThirdParty.getSelectionModel()
                                                .getSelectedItem()),
                                        global.create());
    }

    @Override
    protected String getLabelKey (String key) {
        return myBundle.getString(key);
    }

}
