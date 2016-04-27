package gameauthoring.creation.subforms.events;

import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * View to allow users to define and create an Effect to be added to an EventPackageDefinition
 * 
 * @author Joe Lilien
 *
 */
public interface IEffectSFV extends ISubFormView{

    AttributeDefinition getAttribute ();

    String getEffectType ();

    double getValue ();

    double getLength ();

    void populateWithData (String type, AttributeDefinition attribute, double value, double length);

}
