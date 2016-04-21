package gameauthoring.listdisplay;

import java.util.ResourceBundle;
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


public abstract class EndView extends OnAttributeView {

    ResourceBundle myEndOptions = ResourceBundle.getBundle("defaults/end_condition_options");
    private IGame myGame;
    private ComboBox<String> myEventChoices;

    public EndView (IGame game, ILevel level, ObservableList<AttributeType> attributes) {
        super(game, level.getConditionsListProperty(),
              attributes);
        myGame = game;
        initializeDisplay();

    }

    protected void initBoxes () {
        super.initBoxes();
        myEventChoices =
                createStringComboBox(FXCollections.observableArrayList(getKeys(myEndOptions)));

    }

    protected IEventPackage getGlobal () {
        EventPackageDefinition global = new EventPackageDefinition();
        global.getMyEventsList().add(new GameEvent(getEventType()));
        return global.create();
    }

    protected EventType getEventType () {
        return new EventTypeFactory().interpret(getChoice());
    }

    private String getChoice () {
        return myEventChoices.getSelectionModel().getSelectedItem();
    }

    protected IGame getGame () {
        return myGame;
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
