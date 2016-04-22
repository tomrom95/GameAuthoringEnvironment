package gameauthoring.creation.subforms.fire;

import engine.definitions.SpriteDefinition;

/**
 * Allows users to define properties of a given TrackingFirer ModuleDefinition for a sprite
 * 
 * @author Joe Lilien
 *
 */
public interface ITrackingFireSFV {

    String getTargetsKey ();

    String getWaitTimeKey ();

    SpriteDefinition getSelectedMissile ();

}
