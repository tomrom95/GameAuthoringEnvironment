package gameauthoring.conditiontab;

import engine.IGame;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnClickCondition;
import engine.definitions.EventPackageDefinition;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.NameCellView;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Need to refractor heavily, crunched for demo
 *
 */
public class OnClickPopUp extends ConditionPopUp {

    private IGame myGame;
    private ComboBox<SpriteGroup> myGroupA;
    private ComboBox<EventPackageDefinition> myEventsA;
    private ComboBox<SpriteGroup> myGroupB;
    private ComboBox<EventPackageDefinition> myEventsB;
    private ComboBox<EventPackageDefinition> myGlobalEvents;

    public OnClickPopUp (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();

    }

    @Override
    protected ICondition subCreation () {
        EventPackageDefinition packageA = myEventsA.getSelectionModel().getSelectedItem();
        packageA.setMySpriteGroup(myGroupA.getSelectionModel().getSelectedItem());
        EventPackageDefinition packageB = myEventsB.getSelectionModel().getSelectedItem();
        packageB.setMySpriteGroup(myGroupB.getSelectionModel().getSelectedItem());
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();
        return new OnClickCondition(myGame, packageA.create(), packageB.create(), global.create());

    }

    @Override
    protected void initializeDisplay () {
        initBoxes();
        add(getHBox(), 0, 1);
    }

    private void initBoxes () {
        // TODO Auto-generated method stub
        myGroupA = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        addCellFactory(myGroupA);

        myEventsA =
                new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());

        addCellFactory(myEventsA);

        myGroupB = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedGroups().getItems());

        addCellFactory(myGroupB);

        myEventsB =
                new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());

        addCellFactory(myEventsB);

        myGlobalEvents =
                new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());

        addCellFactory(myGlobalEvents);

    }

    private Node getHBox () {
        HBox hbox = new HBox(CUSHION);
        hbox.getChildren()
                .add(getCombo("Target",
                              myGroupA));
        hbox.getChildren()
                .add(getCombo("Self Effects",
                              myEventsA));
        hbox.getChildren()
                .add(getCombo("Other Group",
                              myGroupB));
        hbox.getChildren()
                .add(getCombo("Group Effects",
                              myEventsB));
        hbox.getChildren()
                .add(getCombo("Global Effects",
                              myGlobalEvents));
        return hbox;

    }

    private Node getCombo (String label, Node node) {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(label), node);
        return vbox;
    }

}
