package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IEventPackage;
import engine.IGame;
import engine.ILevel;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnClickCondition;
import engine.definitions.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


public class ClickEndView extends SubConditionView {

    ResourceBundle myEndOptions = ResourceBundle.getBundle("defaults/end_condition_options");
    private ComboBox<SpriteGroup> mySpriteGroup;
    private IGame myGame;
    private ComboBox<String> myEventChoices;

    public ClickEndView (IGame game, ILevel level) {
        super(level.getConditionsListProperty());
        myGame = game;
        initializeDisplay();
    }

    @Override
    protected void initBoxes () {
        mySpriteGroup = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        myEventChoices =
                createStringComboBox(FXCollections.observableArrayList(getKeys(myEndOptions)));
    }

    @Override
    protected ICondition subCreation () {
        return new OnClickCondition(myGame, getTargettedSprite(),
                                    new EventPackageDefinition().create(),
                                    getGlobal());
    }

    private IEventPackage getTargettedSprite () {
        EventPackageDefinition spriteTarget = new EventPackageDefinition();
        spriteTarget.setMySpriteGroup(mySpriteGroup.getSelectionModel().getSelectedItem());
        return spriteTarget.create();
    }

    protected IEventPackage getGlobal () {
        EventPackageDefinition global = new EventPackageDefinition();
        global.getMyEventsList().add(new GameEvent(getEventType()));
        return global.create();
    }

    protected EventType getEventType () {
        return new EventTypeFactory()
                .interpret(myEventChoices.getSelectionModel().getSelectedItem());
    }

    @Override
    protected String getLabelKey (String key) {

        return "GroupA";
    }

}
