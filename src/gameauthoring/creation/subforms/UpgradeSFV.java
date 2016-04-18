package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class UpgradeSFV extends SubFormView {
    private String myUpgradeChoicesKey = "Next Level Defender: ";
    private String myUpgradableKey = "Upgradable: ";
    private SingleChoiceEntryView<SpriteDefinition> myUpgradeChoices;
    private CheckEntryView isUpgradable;
    private GridPane myContainer;

    public UpgradeSFV (IDefinitionCollection<SpriteDefinition> sprites) {
        myUpgradeChoices =
                new SingleChoiceEntryView<SpriteDefinition>(myUpgradeChoicesKey, sprites.getItems(),
                                                            AuthoringView.DEFAULT_ENTRYVIEW);
        isUpgradable =
                new CheckEntryView(myUpgradableKey, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private void initView () {
        myContainer = new GridPane();
        myContainer.add(isUpgradable.draw(), 0, 0);
        myContainer.add(myUpgradeChoices.draw(), 1, 0);
        myUpgradeChoices.draw().visibleProperty().bind(isUpgradable.isCheckedProperty());
    }
    
    public BooleanProperty isUpgradableProperty(){
        return this.isUpgradable.isCheckedProperty();
    }
    
    public SingleChoiceEntryView<SpriteDefinition> getUpgradeChoices(){
        return this.myUpgradeChoices;
    }
    @Override
    public Node draw () {
        return myContainer;
    }

}
