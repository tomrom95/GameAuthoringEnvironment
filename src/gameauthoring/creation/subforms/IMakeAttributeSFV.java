package gameauthoring.creation.subforms;

/**
 * Allows for users to define a starting value for the sprite attribute they are creating
 * 
 * Creation of interface helps avoid issues with hidden dependencies and data loss
 * 
 * @author Joe Lilien
 *
 */
public interface IMakeAttributeSFV extends ISubFormView{

    String getMyStartingValueKey ();

}
