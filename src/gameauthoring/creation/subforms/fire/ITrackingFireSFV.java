package gameauthoring.creation.subforms.fire;

import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * Allows users to define properties of a given TrackingFirer ModuleDefinition for a sprite
 * 
 * @author Joe Lilien Timko
 *
 */
public interface ITrackingFireSFV  extends IFiringSFV{

    SpriteDefinition getSelectedMissile ();

    SpriteGroup getTargetsCoice ();

    void populateWithData (SpriteDefinition missile, SpriteGroup target, double waitTime, double range, boolean isRanged);

}
