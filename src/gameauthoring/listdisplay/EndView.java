package gameauthoring.listdisplay;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.ILevel;
import engine.conditions.ICondition;
import engine.conditions.OnGlobalAttributeCondition;
import engine.definitions.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.PredicateGenerator;


public abstract class EndView extends SubConditionView {

    ResourceBundle myMathBundle = ResourceBundle.getBundle("defaults/math_operations");
    private ComboBox<AttributeType> myAttributeType;
    private TextField myValueToCompare;
    private ComboBox<String> myChecks;
    private ObservableList<AttributeType> myAttributeStorage;
    private IGame myGame;

    public EndView (IGame game, ILevel level) {
        super(level.getConditionsListProperty());
        myGame = game;
        myAttributeStorage = FXCollections
                .observableArrayList(game.getAttributeManager().getAttributes().stream()
                        .map(atty -> atty.getType()).collect(Collectors.toList()));
        initializeDisplay();

    }

    protected void initBoxes () {
        myAttributeType = createComboBox(myAttributeStorage);
        myValueToCompare = createTextField();
        myChecks = createStringComboBox(FXCollections.observableArrayList(getCheckTypes()));
    }

    protected IEventPackage getGlobal () {
        EventPackageDefinition global = new EventPackageDefinition();
        global.getMyEventsList().add(new GameEvent(getEventType()));
        return global.create();
    }

    protected abstract EventType getEventType ();

    protected IGame getGame () {
        return myGame;
    }

    protected AttributeType getAttributeType () {
        return myAttributeType.getSelectionModel().getSelectedItem();
    }

    protected DoublePredicate createPredicate () {
        return new PredicateGenerator().generateDouble(myValueToCompare.getText(),
                                                       myChecks.getSelectionModel()
                                                               .getSelectedItem());
    }

    private List<String> getCheckTypes () {
        return Collections.list(myMathBundle.getKeys()).stream()
                .map(key -> myMathBundle.getString(key))
                .collect(Collectors.toList());
    }

    @Override
    protected ICondition subCreation () {
        return new OnGlobalAttributeCondition(getGame(), getAttributeType(), createPredicate(),
                                              createEmpty(), getGlobal());
    }

    @Override
    protected String getLabelKey (String key) {
        // TODO Auto-generated method stub
        return "GroupA";
    }

    private IEventPackage createEmpty () {
        return new EventPackageDefinition().create();
    }

}
