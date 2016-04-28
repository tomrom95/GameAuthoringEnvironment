package gameauthoring.creation.subforms.costs;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Implementation of ICostSFV with HBox arrangement and combobox layout
 * 
 * @author Tommy
 *
 */
public class CostSFV extends SubFormView {
//    private static final String CHECK_STRING = "Cost Required?";
    private static final int SPACING = 5;
//
//    private String myAttributeChoicesKey = "Resource Required: ";
//    private String myCostKey = "Amount to buy sprite: ";
    private SingleChoiceEntryView<AttributeDefinition> myAttributes;
    private NumberEntryView myCost;
    private HBox myContainer;
    private CheckBox myCheckBox;
    private ResourceBundle myLabel;

    public CostSFV (AuthorshipData data) {
        setResourceBundle();
        myAttributes =
                new SingleChoiceEntryView<AttributeDefinition>(myLabel.getString("ResourceChoice"),
                                                               data.getMyCreatedGlobals()
                                                                       .getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myCost =
                new NumberEntryView(myLabel.getString("CostKey"), super.getData(), 60, 20,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        createCostCheck();
        initView();
    }

    private void setResourceBundle () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    @Override
    protected void initView () {
        myContainer = new HBox(SPACING);
        myContainer.getChildren().addAll(myCheckBox,
                                         drawFields());
    }

    private Node drawFields () {
        VBox box = new VBox(SPACING);
        box.getChildren().addAll(myAttributes.draw(),
                                 myCost.draw());
        box.visibleProperty().bind(myCheckBox.selectedProperty());
        return box;
    }

    private void createCostCheck () {
        myCheckBox = new CheckBox(myLabel.getString("CostCheck"));
    }

    public AttributeDefinition getSelectedAttribute () {
        return myAttributes.getSelected();
    }

    public void setSelectedAttribute (AttributeDefinition attributeDefinition) {
        myAttributes.setSelected(attributeDefinition);
    }

    public String getCostKey () {
        return myLabel.getString("CostKey");
    }

    public boolean costChecked () {
        return myCheckBox.selectedProperty().get();
    }

}
