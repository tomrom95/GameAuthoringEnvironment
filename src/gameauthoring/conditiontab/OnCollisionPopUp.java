package gameauthoring.conditiontab;


import engine.IGame;
import engine.ISpriteGroup;
import engine.OnCollisionCondition;
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

public class OnCollisionPopUp extends ConditionPopUp {

    
    private IGame myGame;
    private ComboBox<ISpriteGroup> myGroupA;
    private ComboBox<EventPackageDefinition> myEventsA;
    private ComboBox<ISpriteGroup> myGroupB;
    private ComboBox<EventPackageDefinition> myEventsB;
    private ComboBox<ISpriteGroup> myThirdParty;
    private ComboBox<EventPackageDefinition> myThirdEvents;
    private ComboBox<EventPackageDefinition> myGlobalEvents;
    
    public OnCollisionPopUp (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
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
        myThirdParty = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myThirdEvents = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGlobalEvents = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        
    }

    private Node getHBox () {
        HBox hbox = new HBox(CUSHION);
        //Connections
        hbox.getChildren().add(getCombo("Group A", myGroupA));
        hbox.getChildren().add(getCombo("GroupB", myGroupB));
        hbox.getChildren().add(getCombo("Third Party", myThirdParty));
        hbox.getChildren().add(getCombo("Group A Effects", myEventsA));
        hbox.getChildren().add(getCombo("Group B Effects", myEventsB));
        hbox.getChildren().add(getCombo("Third Party Effects", myThirdEvents));
        hbox.getChildren().add(getCombo("Global Effects", myGlobalEvents));
        return hbox;
        
     }
    

    private Node getCombo (String label, Node node) {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(label), node);
        return vbox;
    }


    @Override
    protected ICondition createCondition () {
        EventPackageDefinition packageA = myEventsA.getSelectionModel().getSelectedItem();
        packageA.setMySpriteGroup(myGroupA.getSelectionModel().getSelectedItem());
        EventPackageDefinition packageB = myEventsB.getSelectionModel().getSelectedItem();
        packageB.setMySpriteGroup(myGroupB.getSelectionModel().getSelectedItem());
        EventPackageDefinition other = myThirdEvents.getSelectionModel().getSelectedItem();
        packageB.setMySpriteGroup(myThirdParty.getSelectionModel().getSelectedItem());
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();        
        return new OnCollisionCondition(myGame, packageA.create(), packageB.create(), other.create(), global.create());
    }

}
