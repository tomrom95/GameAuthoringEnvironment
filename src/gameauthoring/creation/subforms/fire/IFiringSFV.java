package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * Generic interface for all firing subform views. As of now contains the method to store and pass
 * data on ranged firing
 *
 * @author josephtimko1
 *
 */

public interface IFiringSFV extends ISubFormView {

    double getMyRange ();

    boolean getMyIsRanged ();

    double getMyWaitTime ();

    SpriteDefinition getMissileSelection ();

}
