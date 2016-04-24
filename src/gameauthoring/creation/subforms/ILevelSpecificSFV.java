package gameauthoring.creation.subforms;

/**
 * Allows user to determine if a created global attribute will be included in the Game as a whole or
 * in a particular level
 * 
 * @author Joe Lilien
 *
 */
public interface ILevelSpecificSFV extends ISubFormView{

    boolean isLevelSpecific ();

}
