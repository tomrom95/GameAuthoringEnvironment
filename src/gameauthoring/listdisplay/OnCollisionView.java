package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IGame;
import engine.conditions.OnCollisionCondition;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.definitions.EventPackageDefinition;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Need to refractor heavily, crunched for demo
 *
 */

public class OnCollisionView extends ConditionPopUp {

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
    protected void initializeDisplay () {
        initBoxes();
        add(getHBox(), 0, 1);

    }

    private void initBoxes () {
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
        packageA.setMySpriteGroup(myGroupA.getSelectionModel().getSelectedItem());
        EventPackageDefinition packageB = myEventsB.getSelectionModel().getSelectedItem();
        packageB.setMySpriteGroup(myGroupB.getSelectionModel().getSelectedItem());
        EventPackageDefinition other = myThirdEvents.getSelectionModel().getSelectedItem();
        other.setMySpriteGroup(myThirdParty.getSelectionModel().getSelectedItem());
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();
        return new OnCollisionCondition(myGame, packageA.create(), packageB.create(),
                                        other.create(), global.create());
    }

    @Override
    protected String getLabelKey (String key) {
       return myBundle.getString(key);
    }

}
