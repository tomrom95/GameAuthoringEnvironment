package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.SpriteGroup;
import engine.conditions.ICondition;
import engine.conditions.OnGlobalAttributeCondition;
import engine.conditions.OnSpriteAttributeCondition;
import engine.definitions.EventPackageDefinition;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


public class OnSpriteView extends OnAttributeView {

    private ComboBox<SpriteGroup> mySpriteGroup;
    private ComboBox<EventPackageDefinition> mySpriteEvent;
    
    public OnSpriteView (IGame game) {
        super(game, FXCollections
                .observableArrayList(game.getAuthorshipData().getMyCreatedAttributes().getItems()
                        .stream()
                        .map(atty -> new AttributeType(atty.getType()))
                        .collect(Collectors.toList())));
    }

    @Override
    protected void initBoxes () {
        mySpriteGroup = createComboBox(getGame().getAuthorshipData().getMyCreatedGroups().getItems());
        mySpriteEvent = createComboBox(getGame().getAuthorshipData().getMyCreatedEventPackages().getItems());
        super.initBoxes();
    }
    
    @Override
    protected String getLabelKey (String key) {
        return ResourceBundle.getBundle("defaults/on_sprite_tab").getString(key);
    }

    @Override
    protected ICondition subCreation () {
        return new OnSpriteAttributeCondition(getGame(),
                                              getAttributeType(),
                                              createPredicate(),
                                              getSpritePackage(),
                                              getThirdPartyPackage(), 
                                              getGlobalPackage());
    }

    private IEventPackage getSpritePackage () {
        EventPackageDefinition self = mySpriteEvent.getSelectionModel().getSelectedItem();
        self.setMySpriteGroup(mySpriteGroup.getSelectionModel().getSelectedItem());
        return self.create();
    }


}
