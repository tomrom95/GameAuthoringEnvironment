package gameauthoring.creation.subforms;

import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.UIFactory;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;


/**
 * Allow users to define upgrades for sprites
 * 
 * @author Joe Lilien
 *
 */
public class UpgradeSFV extends SubFormView implements IUpgradeSFV {
    
    private String myUpgradeChoicesKey = "Next Level Defender: ";
    private String myUpgradableKey = "Upgradable: ";
    private String myGlobalKey = "Deplete Global Resource: ";
    private String myAttributeChoicesKey = "Depelted Resource: ";
    private String myCostKey = "Cost: ";
    private TitledPane myContainer;
    private SingleChoiceEntryView<SpriteDefinition> myUpgradeChoices;
    private SingleChoiceEntryView<AttributeDefinition> myAttributeChoices;
    private CheckEntryView isUpgradable;
    private CheckEntryView isGlobalResource;
    private NumberEntryView myCost;
    private GridPane myPane;
    private BasicUIFactory myUIFactory = new BasicUIFactory();

    public UpgradeSFV (AuthorshipData data) {

        // TODO: change list of sprite DefinitionCollections in authorship data to map most likely,
        // or separate them, should decide on that to avoid magic number like this

        myUpgradeChoices =
                new SingleChoiceEntryView<SpriteDefinition>(myUpgradeChoicesKey, data
                        .getMyCreatedSprites().get(1).getItems(),
                                                            AuthoringView.DEFAULT_ENTRYVIEW);
        myAttributeChoices =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeChoicesKey,
                                                               data.getMyCreatedAttributes()
                                                                       .getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);

        isUpgradable =
                new CheckEntryView(myUpgradableKey, AuthoringView.DEFAULT_ENTRYVIEW);

        isGlobalResource = new CheckEntryView(myGlobalKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myCost =
                new NumberEntryView(myCostKey, super.getData(), 60, 20,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
        initBinding(data);

    }

    @Override
    protected void initView () {        
        myPane = new GridPane();
        myPane.add(isUpgradable.draw(), 0, 0);
        myPane.add(isGlobalResource.draw(), 0, 1);
        myPane.add(myAttributeChoices.draw(), 1, 1);
        myPane.add(myCost.draw(), 1, 2);
        myPane.add(myUpgradeChoices.draw(), 1, 0);
        myContainer = myUIFactory.makeTitledPane(myUpgradableKey, myPane, false);
    }

    private void initBinding (AuthorshipData data) {
        myUpgradeChoices.draw().visibleProperty().bind(isUpgradableProperty());
        myAttributeChoices.draw().visibleProperty().bind(isUpgradableProperty());
        isGlobalResource.draw().visibleProperty().bind(isUpgradableProperty());
        myCost.draw().visibleProperty().bind(isUpgradableProperty());
        isGlobalResource.isCheckedProperty()
                .addListener(c -> updateAttributeChoices(data,
                                                         isGlobalResource.isCheckedProperty()));
    }

    private void updateAttributeChoices (AuthorshipData data, BooleanProperty isGlobal) {
        if (isGlobal.get()) {
            myAttributeChoices.setItems(data.getMyCreatedGlobals().getItems());
        }
        else {
            //TODO: maybe this should be from mySprite.getAttributes() so that you can only pick attributes that the sprite has
            myAttributeChoices.setItems(data.getMyCreatedAttributes().getItems());
        }
    }

    @Override
    public SpriteDefinition getNextUpgrade () {
        return this.myUpgradeChoices.getSelected();
    }

    @Override
    public AttributeDefinition getDepletedAttribute () {
        return this.myAttributeChoices.getSelected();
    }

    @Override
    public String getMyCostKey () {
        return this.myCostKey;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    @Override
    public BooleanProperty isUpgradableProperty () {
        return this.isUpgradable.isCheckedProperty();
    }

    @Override
    public BooleanProperty isGlobalProperty () {
        return this.isGlobalResource.isCheckedProperty();
    }

    @Override
    public void setIsUpgradable (boolean isSelected) {
        this.isUpgradableProperty().set(isSelected);
    }

    @Override
    public void setDepletedAttribute (AttributeDefinition item) {
        this.myAttributeChoices.setSelected(item);
    }

    @Override
    public void setNextUpgrade (SpriteDefinition item) {
        this.myUpgradeChoices.setSelected(item);
    }


}
