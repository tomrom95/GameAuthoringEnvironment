package gameauthoring.creation.subforms.costs;

import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * Allows user to define an resource and associated cost to build a given sprite
 * 
 * @author Joe Lilien
 *
 */
public interface ICostSFV  extends ISubFormView{

    AttributeDefinition getSelectedAttribute ();

    double getCost();
    
    void populateWithData(AttributeDefinition attribute, double cost);
}
