package gameauthoring.characters;

import engine.definitions.AttributeDefinition;


/**
 * This class serves to control the view and data in the attribute subform in authoring environment
 * 
 * @author Jin An
 *
 */
public class AttributeSubFormController implements ISubFormControllerAttribute {

    private AttributeSubFormView myView;
    private IFormDataManager myFormData;

    public AttributeSubFormController () {
        this.myView = new AttributeSubFormView();
        this.myFormData = myView.getData();
    }

    @Override
    public void updateItem (AttributeDefinition item) {

        double max = Double.parseDouble(myFormData.getValueProperty(myView.getMyMaxKey()).get());
        double min = Double.parseDouble(myFormData.getValueProperty(myView.getMyMinKey()).get());
        boolean isGlobal = Boolean.parseBoolean(myFormData.getValueProperty(myView.getMyIsGlobalKey()).get());

        AttributeDefinition attributeDef = new AttributeDefinition(max, min, isGlobal);
        item.setAttributeDefinition(attributeDef);
    }

    @Override
    public void populateViewsWithData (AttributeDefinition item) {
        myFormData.set(myView.getMyMaxKey(), String.valueOf(item.getAttributeDefinition().getMaxValue()));
        myFormData.set(myView.getMyMinKey(), String.valueOf(item.getAttributeDefinition().getMinValue()));
        myFormData.set(myView.getMyIsGlobalKey(), String.valueOf(item.getAttributeDefinition().getIsGlobal()));
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
