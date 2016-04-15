package engine;

import java.util.List;
import engine.effects.IEffect;
import engine.events.GameEvent;


public interface IEventPackage {

    /**
     * @return The events to be applied
     */
    List<GameEvent> getMyEvents ();

    /**
     * @return The effects to be applied
     */
    List<IEffect> getMyEffects ();

    /**
     * The condition will iterate over the members of the game and compare
     * with this group, selectivity will vary depending on condition
     *
     * @return the sprite group associated with this package
     */
    ISpriteGroup getTargetedSpriteGroup ();
}
