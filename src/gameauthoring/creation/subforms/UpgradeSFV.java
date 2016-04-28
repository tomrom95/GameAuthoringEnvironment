package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
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
    
    private ResourceBundle myLabel;
    private String myUpgradeChoicesKey;
    private String myUpgradableKey;
    private String myGlobalKey;
    private String myAttributeChoicesKey;
    private String myCostKey;
    private TitledPane myContainer;
    private SingleChoiceEntryView<SpriteDefinition> myUpgradeChoices;
    private SingleChoiceEntryView<AttributeDefinition> myAttributeChoices;
    private CheckEntryView isUpgradable;
    private CheckEntryView isGlobalResource;
    private NumberEntryView myCost;
    private GridPane myPane;

    public UpgradeSFV (AuthorshipData data, DefinitionCollection<SpriteDefinition> nextUpgrades) {

        setResourceBundleAndKey();
        // TODO: change list of sprite DefinitionCollections in authorship data to map most likely,
        // or separate them, should decide on that to avoid magic number like this

        myUpgradeChoices =
                new SingleChoiceEntryView<SpriteDefinition>(myUpgradeChoicesKey,
                                                            nextUpgrades.getItems(),
                                                            AuthoringView.DEFAULT_ENTRYVIEW);
        myAttributeChoices =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeChoicesKey,
                                                               data.getMyCreatedAttributes()
                                                                       .getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        isUpgradable = new CheckEntryView(myUpgradableKey, AuthoringView.DEFAULT_ENTRYVIEW);
        isGlobalResource = new CheckEntryView(myGlobalKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myCost = new NumberEntryView(myCostKey, 60, 20, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
        initBinding(data);

    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                                           .getInstance().getCurrentLocaleProperty().get());
        myUpgradeChoicesKey = myLabel.getString("UpgradeChoicesKey");
        myUpgradableKey = myLabel.getString("UpgradableKey");
        myGlobalKey = myLabel.getString("GlobalKey");
        myAttributeChoicesKey = myLabel.getString("AttributeChoicesKey");
        myCostKey = myLabel.getString("CostKey");        
    }

    @Override
    protected void initView () {
        myPane = new GridPane();
        myPane.add(isUpgradable.draw(), 0, 0);
        myPane.add(isGlobalResource.draw(), 0, 1);
        myPane.add(myAttributeChoices.draw(), 1, 1);
        myPane.add(myCost.draw(), 1, 2);
        myPane.add(myUpgradeChoices.draw(), 1, 0);
        myContainer = getMyUIFactory().makeTitledPane(myUpgradableKey, myPane, false);
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

    @Override
    public SpriteDefinition getNextUpgrade () {
        return this.myUpgradeChoices.getSelected();
    }

    @Override
    public AttributeDefinition getDepletedAttribute () {
        return this.myAttributeChoices.getSelected();
    }

    @Override
    public double getMyCost () {
        return myCost.getData();
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
    public void populateWithData (boolean isUpgradable,
                                  SpriteDefinition nextUpgrade,
                                  AttributeDefinition depletedAttribute,
                                  double cost) {
        this.isUpgradable.setSelected(isUpgradable);
        myUpgradeChoices.setSelected(nextUpgrade);
        myAttributeChoices.setSelected(depletedAttribute);
        myCost.setData(cost);
    }

}
