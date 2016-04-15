package engine;

import java.util.List;
import engine.effects.IEffect;
import engine.events.GameEvent;


public interface IEventPackage {

    /**
     * The events to be applied
     *
     * @return
     */
    List<GameEvent> getMyEvents ();

    /**
     * The effects to be applied
     *
     * @return
     */
    List<IEffect> getMyEffects ();

    /**
     * The condition will iterate over the members of the game and compare
     * with this group, selectivity will vary depending on condition
     *
     * @return
     */
    ISpriteGroup getTargetedSpriteGroup ();
}
