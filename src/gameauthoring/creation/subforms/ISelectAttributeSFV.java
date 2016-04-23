package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.concrete.AttributeDefinition;

/**
 * Allows for users to select any number of created attributes to add to a given sprite definition
 * 
 * Creation of interface helps avoid issues with hidden dependencies and data loss
 * 
 * @author Joe Lilien
 *
 */
public interface ISelectAttributeSFV {

    List<AttributeDefinition> getSelectedAttributes ();

}
