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

    String getMyProjectileKey ();

    String getMyAngleKey ();

    String getMyWaitTimeKey ();

    SpriteDefinition getMissileSelection ();

    void setMissileSelection (SpriteDefinition projectileDefinition);

}
