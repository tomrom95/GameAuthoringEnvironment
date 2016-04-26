package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.ILevel;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnSpriteAttributeCondition;
import engine.definitions.concrete.EventPackageDefinition;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


public class SpriteEndView extends AttributeEndView {

    private ComboBox<SpriteGroup> mySpriteGroup;

    public SpriteEndView (IGame game, ILevel level) {
        super(game, level, FXCollections
                .observableArrayList(game.getAuthorshipData().getMyCreatedAttributes().getItems()
                        .stream()
                        .map(atty -> new AttributeType(atty.getType()))
                        .collect(Collectors.toList())));
    }

    @Override
    protected void initBoxes () {
        mySpriteGroup =
                createComboBox(getGame().getAuthorshipData().getMyCreatedGroups().getItems());
        super.initBoxes();

    }

    @Override
    protected ICondition subCreation () {
        return new OnSpriteAttributeCondition(getGame(), getAttributeType(), createPredicate(),
                                              createSpritePackage(), createEmpty(), getGlobal());
    }

    private IEventPackage createSpritePackage () {
        EventPackageDefinition self = new EventPackageDefinition();
        return self.create(mySpriteGroup.getSelectionModel().getSelectedItem());
    }
    
    @Override
    protected String getLabelKey (String key) {
        return ResourceBundle.getBundle("defaults/end_sprite").getString(key);
    }
    
}
