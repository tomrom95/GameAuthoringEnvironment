package gameauthoring.creation.subforms.fire;

import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;


/**
 * Allows users to define properties of a given TrackingFirer ModuleDefinition for a sprite
 * 
 * @author Joe Lilien Timko
 *
 */
public interface ITrackingFireSFV extends IFiringSFV {

    SpriteGroup getTargetsChoice ();

    void populateWithData (SpriteDefinition missile,
                           SpriteGroup target,
                           double waitTime,
                           double range,
                           boolean isRanged);

}
