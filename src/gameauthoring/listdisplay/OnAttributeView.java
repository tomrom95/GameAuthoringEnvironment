package gameauthoring.listdisplay;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.conditions.OnGlobalAttributeCondition;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.definitions.EventPackageDefinition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.PredicateGenerator;


public abstract class OnAttributeView extends SubConditionView {

    ResourceBundle myMathBundle = ResourceBundle.getBundle("defaults/math_operations");
    private IGame myGame;
    private ComboBox<AttributeType> myAttributeType;
    private ComboBox<String> myChecks;
    private TextField myValueToCompare;
    private ComboBox<SpriteGroup> myThirdParty;
    private ComboBox<EventPackageDefinition> myThirdEvents;
    private ComboBox<EventPackageDefinition> myGlobalEvents;

    private ObservableList<AttributeType> myAttributeStorage;

    public OnAttributeView (IGame game, ObservableList<AttributeType> attributeStorage) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        myAttributeStorage = attributeStorage;
        initStage();
    }

    protected void initBoxes () {
        myAttributeType = createComboBox(myAttributeStorage);
        myChecks = createStringComboBox(FXCollections.observableArrayList(getCheckTypes()));
        myValueToCompare = createTextField();
        myThirdParty = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myThirdEvents =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
        myGlobalEvents =
                createComboBox(myGame.getAuthorshipData().getMyCreatedEventPackages().getItems());
    }
    
    protected IGame getGame () {
       return myGame;
    }

    private List<String> getCheckTypes () {
        return Collections.list(myMathBundle.getKeys()).stream()
                .map(key -> myMathBundle.getString(key))
                .collect(Collectors.toList());
    }
    
    protected IEventPackage getThirdPartyPackage () {
        EventPackageDefinition other = myThirdEvents.getSelectionModel().getSelectedItem();
        other.setMySpriteGroup(myThirdParty.getSelectionModel().getSelectedItem());
        return other.create();
    }
    
    protected IEventPackage getGlobalPackage () {
        EventPackageDefinition global = myGlobalEvents.getSelectionModel().getSelectedItem();
        return global.create();
    }
    
    protected AttributeType getAttributeType () {
        return myAttributeType.getSelectionModel().getSelectedItem();
    }

    protected DoublePredicate createPredicate () {
        return new PredicateGenerator().generateDouble(myValueToCompare.getText(),
                                                       myChecks.getSelectionModel()
                                                               .getSelectedItem());
    }

}
