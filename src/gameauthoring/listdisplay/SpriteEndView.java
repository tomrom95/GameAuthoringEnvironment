package gameauthoring.listdisplay;

import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.ILevel;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnSpriteAttributeCondition;
import engine.definitions.EventPackageDefinition;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


public class SpriteEndView extends EndView {

    private ComboBox<SpriteGroup> mySpriteGroup;
    private ComboBox<EventPackageDefinition> mySpriteEvent;

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
        mySpriteEvent =
                createComboBox(getGame().getAuthorshipData().getMyCreatedEventPackages()
                        .getItems());
        super.initBoxes();

    }

    @Override
    protected ICondition subCreation () {
        return new OnSpriteAttributeCondition(getGame(), getAttributeType(), createPredicate(),
                                              createSpritePackage(), createEmpty(), getGlobal());
    }

    private IEventPackage createSpritePackage () {
        EventPackageDefinition self = mySpriteEvent.getSelectionModel().getSelectedItem();
        self.setMySpriteGroup(mySpriteGroup.getSelectionModel().getSelectedItem());
        return self.create();
    }
    
}
