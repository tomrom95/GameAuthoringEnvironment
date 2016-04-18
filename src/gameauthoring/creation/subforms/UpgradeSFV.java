package gameauthoring.creation.subforms;

import engine.AuthorshipData;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * Allow users to define upgrades for sprites
 * 
 * @author Joe Lilien
 *
 */
public class UpgradeSFV extends SubFormView {
    private String myUpgradeChoicesKey = "Next Level Defender: ";
    private String myUpgradableKey = "Upgradable: ";
    private String myGlobalKey = "Deplete Global Resource: ";
    private String myAttributeChoicesKey = "Depelted Resource: ";
    private String myCostKey = "Cost: ";
    private SingleChoiceEntryView<SpriteDefinition> myUpgradeChoices;
    private SingleChoiceEntryView<AttributeDefinition> myAttributeChoices;
    private CheckEntryView isUpgradable;
    private CheckEntryView isGlobalResource;
    private NumberEntryView myCost;
    private GridPane myContainer;

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

    private void initView () {
        myContainer = new GridPane();
        myContainer.add(isUpgradable.draw(), 0, 0);
        myContainer.add(isGlobalResource.draw(), 0, 1);
        myContainer.add(myAttributeChoices.draw(), 1, 1);
        myContainer.add(myCost.draw(), 1, 2);
        myContainer.add(myUpgradeChoices.draw(), 1, 0);

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
            myAttributeChoices.setItems(data.getMyCreatedAttributes().getItems());
        }
    }

    public BooleanProperty isUpgradableProperty () {
        return this.isUpgradable.isCheckedProperty();
    }

    public void setSelected (boolean isSelected) {
        this.isUpgradable.setSelected(isSelected);
    }

    public SingleChoiceEntryView<SpriteDefinition> getUpgradeChoices () {
        return this.myUpgradeChoices;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
