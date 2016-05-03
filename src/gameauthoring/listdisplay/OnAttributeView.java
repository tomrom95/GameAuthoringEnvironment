package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IGame;
import engine.conditions.ICondition;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.BundleOperations;
import util.PredicateGenerator;


public abstract class OnAttributeView extends SubConditionView {

    ResourceBundle myMathBundle = ResourceBundle.getBundle("defaults/math_operations");
    private IGame myGame;
    private ComboBox<AttributeType> myAttributeType;
    private ComboBox<String> myChecks;
    private TextField myValueToCompare;

    private ObservableList<AttributeType> myAttributeStorage;

    public OnAttributeView (IGame game,
                            ObservableList<ICondition> conditionList,
                            ObservableList<AttributeType> attributeStorage) {
        super(conditionList);
        myGame = game;
        myAttributeStorage = attributeStorage;
    }

    @Override
    protected void initBoxes () {
        myAttributeType = createComboBox(myAttributeStorage);
        myChecks = createStringComboBox(BundleOperations.getValuesAsObservable(myMathBundle));
        myValueToCompare = createTextField();
    }

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

}
