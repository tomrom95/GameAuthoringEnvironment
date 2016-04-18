package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.util.ErrorMessage;


/**
 * This class serves to control the view and data in the attribute subform in authoring environment
 * 
 * @author Jin An
 * @author Joe Lilien
 *
 */
public class MakeAttributeSubFormController implements ISubFormControllerAttribute {

    private MakeAttributeSubFormView myView;
    private IFormDataManager myFormData;

    public MakeAttributeSubFormController () {
        this.myView = new MakeAttributeSubFormView();
        this.myFormData = myView.getData();
    }

    /**
     * TODO: most likely move error checking to definition class, or at least standardize it
     * 
     */
    @Override
    public void updateItem (AttributeDefinition item) {
        try {
            double startingValue =
                    Double.parseDouble(myView.getData()
                            .getValueProperty(myView.getMyStartingValueKey()).get());
            item.setStartingValue(startingValue);
        }
        catch (NumberFormatException e) {
            ErrorMessage err = new ErrorMessage("Max and Min Values must be Numbers");
            err.showError();
        }
    }

    @Override
    public void populateViewsWithData (AttributeDefinition item) {
        myFormData.set(myView.getMyStartingValueKey(), String.valueOf(item.getStartingValue()));
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
