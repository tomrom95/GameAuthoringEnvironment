package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;

/**
 * Allows users to select properties of a directional firing module
 * 
 * @author Joe Lilien
 *
 */
public interface IDirectionalFireSFV {

    String getMyProjectileKey ();

    String getMyAngleKey ();

    String getMyWaitTimeKey ();

    SpriteDefinition getMissileSelection ();

}
