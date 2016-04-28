package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.util.ErrorMessage;


/**
 * This class serves to control the view and data in the attribute subform in authoring environment
 * 
 * @author Jin An
 * @author Joe Lilien
 *
 */
public class MakeAttributeSFC implements ISubFormControllerAttribute {

    private IMakeAttributeSFV myView;
    private IFormDataManager myFormData;
    private double myInitialValue = 0;

    public MakeAttributeSFC () {
        this.myView = new MakeAttributeSFV();
        this.myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        myFormData.set(myView.getMyStartingValueKey(), String.valueOf(myInitialValue));
    }

    @Override
    public void updateItem (AttributeDefinition item) {
        try {
            double startingValue =
                    Double.parseDouble(myView.getData()
                            .getValueProperty(myView.getMyStartingValueKey()).get());
            item.setStartingValue(startingValue);
        }
        catch (NumberFormatException e) {
            ErrorMessage err = new ErrorMessage("Max and Min Values must be Numbers");// TODO: resource file
            err.showError();
        }
    }

    @Override
    public void populateViewsWithData (AttributeDefinition item) {
        myView.getData()
                .getValueProperty(myView.getMyStartingValueKey())
                .set(Double.toString(item.getStartingValue()));

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
