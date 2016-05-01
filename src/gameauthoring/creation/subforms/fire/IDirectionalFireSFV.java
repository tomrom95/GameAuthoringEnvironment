package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;


/**
 * Allows users to select properties of a directional firing module
 * 
 * @author Joe Lilien
 *
 */
public interface IDirectionalFireSFV extends IFiringSFV {

    double getMyAngle ();

    void populateWithData (SpriteDefinition missile,
                           double angle,
                           double waitTime,
                           double range,
                           boolean isRanged);

}
