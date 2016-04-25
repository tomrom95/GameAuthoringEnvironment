package gameauthoring.creation.subforms.fire;

import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * Allows users to define properties of a given TrackingFirer ModuleDefinition for a sprite
 * 
 * @author Joe Lilien
 *
 */
public interface ITrackingFireSFV  extends ISubFormView{

    String getWaitTimeKey ();

    SpriteDefinition getSelectedMissile ();

    SpriteGroup getTargetsCoice ();

}
