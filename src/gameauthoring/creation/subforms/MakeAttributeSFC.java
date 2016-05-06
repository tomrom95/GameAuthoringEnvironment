package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;


/**
 * This class serves to control the view and data in the attribute subform in authoring environment
 *
 * @author Jin An
 * @author Joe Lilien
 *
 */
public class MakeAttributeSFC implements ISubFormControllerAttribute {

    private IMakeAttributeSFV myView;
    private double myInitialValue = 0;

    public MakeAttributeSFC () {
        myView = new MakeAttributeSFV();
    }

    @Override
    public void initializeFields () {
        myView.populateWithData(myInitialValue);
    }

    @Override
    public void updateItem (AttributeDefinition item) {
        double startingValue = myView.getStartingValue();
        item.setStartingValue(startingValue);
    }

    @Override
    public void populateViewsWithData (AttributeDefinition item) {
        myView.populateWithData(item.getStartingValue());

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
