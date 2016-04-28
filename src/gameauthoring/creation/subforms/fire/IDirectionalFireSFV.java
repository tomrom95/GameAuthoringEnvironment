package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * Allows users to select properties of a directional firing module
 * 
 * @author Joe Lilien
 *
 */
public interface IDirectionalFireSFV extends ISubFormView {


    SpriteDefinition getMissileSelection ();

    double getMyAngle ();

    double getMyWaitTime ();

    void populateWithData (SpriteDefinition missile, double angle, double waitTime);

}
