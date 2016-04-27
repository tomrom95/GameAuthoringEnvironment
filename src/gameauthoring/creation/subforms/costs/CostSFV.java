package gameauthoring.creation.subforms.costs;

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
public class CostSFV extends SubFormView{
    private static final String CHECK_STRING = "Cost Required?";
    private static final int SPACING = 5;
    
    private String myAttributeChoicesKey = "Resource Required: ";
    private String myCostKey = "Amount to buy sprite: ";
    private SingleChoiceEntryView<AttributeDefinition> myAttributes;
    private NumberEntryView myCost;
    private HBox myContainer;
    private CheckBox myCheckBox;
    
    public CostSFV (AuthorshipData data) {
        myAttributes =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeChoicesKey,
                                                               data.getMyCreatedGlobals()
                                                                       .getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myCost =
                new NumberEntryView(myCostKey, super.getData(), 60, 20,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        createCostCheck();
        initView();
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
        myCheckBox = new CheckBox(CHECK_STRING);
    }

    public AttributeDefinition getSelectedAttribute () {
        return myAttributes.getSelected();
    }
    
    public String getCostKey () {
        return myCostKey;
    }
    
    public boolean costChecked () {
        return myCheckBox.selectedProperty().get();
    }

}
