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

    /**
     * TODO: most likely move error checking to definition class, or at least standardize it
     * 
     */
    @Override
    public void updateItem (AttributeDefinition item) {
        try {
            double max =
                    Double.parseDouble(myFormData.getValueProperty(myView.getMyMaxKey()).get());
            double min =
                    Double.parseDouble(myFormData.getValueProperty(myView.getMyMinKey()).get());
            boolean isGlobal =
                    Boolean.parseBoolean(myFormData.getValueProperty(myView.getMyIsGlobalKey())
                            .get());
            item.setMaxValue(max);
            item.setMinValue(min);
            item.setIsGlobal(isGlobal);
        }
        catch (NumberFormatException e) {
            ErrorMessage err = new ErrorMessage("Max and Min Values must be Numbers");
            err.showError();
        }
    }

    @Override
    public void populateViewsWithData (AttributeDefinition item) {
        myFormData.set(myView.getMyMaxKey(), String.valueOf(item.getMaxValue()));
        myFormData.set(myView.getMyMinKey(), String.valueOf(item.getMinValue()));
        myFormData.set(myView.getMyIsGlobalKey(), String.valueOf(item.getIsGlobal()));
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
