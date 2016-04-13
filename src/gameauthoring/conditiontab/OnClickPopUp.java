package gameauthoring.conditiontab;

import engine.IGame;
import engine.ISpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnClickCondition;
import engine.definitions.EventPackageDefinition;
import engine.definitions.GroupDefinition;
import engine.definitions.SpriteDefinition;
import engine.profile.IProfilable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OnClickPopUp extends ConditionPopUp {

    private IGame myGame;
    private ComboBox<ISpriteGroup> myGroupA;
    private ComboBox<EventPackageDefinition> myEventsA;
    private ComboBox<ISpriteGroup> myGroupB;
    private ComboBox<EventPackageDefinition> myEventsB;
    private ComboBox<EventPackageDefinition> myGlobalEvents;
    

    public OnClickPopUp (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
        
    }

    @Override
    protected ICondition createCondition () {
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
        add(getHBox(), 0, 0);
    }

    private void initBoxes () {
        // TODO Auto-generated method stub
        myGroupA = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEventsA = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGroupB = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEventsB = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGlobalEvents = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        
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
