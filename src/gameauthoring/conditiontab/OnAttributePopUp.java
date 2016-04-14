package gameauthoring.conditiontab;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IGame;
import engine.conditions.OnGlobalAttributeCondition;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.definitions.EventPackageDefinition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.RealDoublePredicate;


public abstract class OnAttributePopUp extends ConditionPopUp {

    private IGame myGame;
    private ComboBox<AttributeType> myAttributeType;
    private ComboBox<String> myChecks;
    private TextField myValueToCompare;
    private ComboBox<SpriteGroup> myThirdParty;
    private ComboBox<EventPackageDefinition> myThirdEvents;
    private ComboBox<EventPackageDefinition> myGlobalEvents;

    private ObservableList<AttributeType> myAttributeStorage;

    public OnAttributePopUp (IGame game, ObservableList<AttributeType> attributeStorage) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        myAttributeStorage = attributeStorage;
        initStage();
    }

    @Override
    protected void initializeDisplay () {
        initBoxes();
        add(getHBox(), 0, 0);
    }

    private void initBoxes () {
        myAttributeType =
                new ComboBox<>(myAttributeStorage);
        myChecks =
                new ComboBox<>(FXCollections.observableArrayList(getCheckTypes()));
        myValueToCompare = new TextField();
        myThirdParty = new ComboBox<>(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myThirdEvents =
                new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGlobalEvents =
                new ComboBox<>(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());

    }

    private List<String> getCheckTypes () {
        List<String> checks = new ArrayList<String>();
        checks.add("==");
        checks.add(">");
        checks.add("<");
        checks.add(">=");
        checks.add("<=");
        return checks;
    }

    private Node getHBox () {
        HBox hbox = new HBox(CUSHION);
        // Connections
        hbox.getChildren().add(getCombo("Attribute", myAttributeType));
        hbox.getChildren().add(getCombo("Comparison", myChecks));
        hbox.getChildren().add(getCombo("Double value to compare with", myValueToCompare));
        hbox.getChildren().add(getCombo("Third party", myThirdParty));
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
        EventPackageDefinition other = myThirdEvents.getSelectionModel().getSelectedItem();
        other.setMySpriteGroup(myThirdParty.getSelectionModel().getSelectedItem());
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();
        return new OnGlobalAttributeCondition(myGame,
                                              myAttributeType.getSelectionModel().getSelectedItem(),
                                              createPredicate(),
                                              other.create(), global.create());
    }

    private DoublePredicate createPredicate () {
        double valToCompare = Double.parseDouble(myValueToCompare.getText());
        if (myChecks.getSelectionModel().getSelectedItem().equals("==")) {
            return new RealDoublePredicate(val -> val == valToCompare);
        }
        else if (myChecks.getSelectionModel().getSelectedItem().equals(">")) {
            return new RealDoublePredicate(val -> val > valToCompare);
        }
        else if (myChecks.getSelectionModel().getSelectedItem().equals("<")) {
            return new RealDoublePredicate(val -> val < valToCompare);
        }
        else if (myChecks.getSelectionModel().getSelectedItem().equals(">=")) {
            return new RealDoublePredicate(val -> val >= valToCompare);
        }
        else {
            return new RealDoublePredicate(val -> val <= valToCompare);
        }
    }

}
