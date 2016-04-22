package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IEventPackage;
import engine.IGame;
import engine.ILevel;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnClickCondition;
import engine.definitions.concrete.EventPackageDefinition;
import javafx.scene.control.ComboBox;


public class ClickEndView extends SubConditionView {

    private EndOptions myEndOptions = new EndOptions();
    private ComboBox<SpriteGroup> mySpriteGroup;
    private IGame myGame;

    public ClickEndView (IGame game, ILevel level) {
        super(level.getConditionsListProperty());
        myGame = game;
        initializeDisplay();
    }

    @Override
    protected void initBoxes () {
        mySpriteGroup = createComboBox(myGame.getAuthorshipData().getMyCreatedGroups().getItems());
        addStringComboBox(myEndOptions.getBox());
    }

    @Override
    protected ICondition subCreation () {
        return new OnClickCondition(myGame, getTargettedSprite(),
                                    new EventPackageDefinition().create(),
                                    myEndOptions.getGlobal());
    }

    private IEventPackage getTargettedSprite () {
        EventPackageDefinition spriteTarget = new EventPackageDefinition();
        spriteTarget.setMySpriteGroup(mySpriteGroup.getSelectionModel().getSelectedItem());
        return spriteTarget.create();
    }

    @Override
    protected String getLabelKey (String key) {
        return ResourceBundle.getBundle("defaults/end_click").getString(key);
    }
}
