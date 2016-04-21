package gameauthoring.listdisplay;

import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.SpriteGroup;
import engine.definitions.EventPackageDefinition;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public abstract class OnGameAttributeView extends OnAttributeView {

    private ComboBox<SpriteGroup> myThirdParty;
    private ComboBox<EventPackageDefinition> myThirdEvents;
    private ComboBox<EventPackageDefinition> myGlobalEvents;

    public OnGameAttributeView (IGame game, ObservableList<AttributeType> attributeStorage) {
        super(game, game.getConditionManager().getConditionListProperty(), attributeStorage);
        initStage();
    }

    @Override
    protected void initBoxes () {
        super.initBoxes();
        myThirdParty =
                createComboBox(getGame().getAuthorshipData().getMyCreatedGroups().getItems());
        myThirdEvents =
                createComboBox(getGame().getAuthorshipData().getMyCreatedEventPackages()
                        .getItems());
        myGlobalEvents =
                createComboBox(getGame().getAuthorshipData().getMyCreatedEventPackages()
                        .getItems());
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

}
