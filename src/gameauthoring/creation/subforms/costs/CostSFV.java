package gameauthoring.creation.subforms.costs;

import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * Implementation of ICostSFV with HBox arrangement and combobox layout
 * 
 * @author Tommy
 *
 */
public class CostSFV extends SubFormView implements ICostSFV {

    private static final int SPACING = 5;
    private String myAttributeChoicesKey = "Resource Required: ";
    private String myCostKey = "Amount to buy sprite: ";
    private SingleChoiceEntryView<AttributeDefinition> myAttributes;
    private NumberEntryView myCost;
    private HBox myContainer;

    public CostSFV (AuthorshipData data) {
        myAttributes =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeChoicesKey,
                                                               data.getMyCreatedGlobals()
                                                                       .getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myCost =
                new NumberEntryView(myCostKey, 60, 20, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    @Override
    protected void initView () {
        myContainer = getMyUIFactory().makeHBox(SPACING, Pos.CENTER, myAttributes.draw(), myCost.draw());
    }

    @Override
    public AttributeDefinition getSelectedAttribute () {
        return myAttributes.getSelected();
    }

    @Override
    public double getCost () {
        return myCost.getData();
    }

    @Override
    public void populateWithData (AttributeDefinition attribute, double cost) {
        myAttributes.setSelected(attribute);
        myCost.setData(cost);
    }

}
