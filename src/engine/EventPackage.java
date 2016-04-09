package engine;

import java.util.List;
import engine.effects.IEffect;
import engine.events.GameEvent;

/**
 * Need to alter the definition classes to use this class instead
 * @author jonathanim
 *
 */
public class EventPackage implements IEventPackage {

    private ISpriteGroup mySpriteGroup;
    private List<IEffect> myEffectsList;
    private List<GameEvent> myEventsList;

    EventPackage (ISpriteGroup myGroup, List<IEffect> myEffects, List<GameEvent> myEvents) {
        mySpriteGroup = myGroup;
        myEffectsList = myEffects;
        myEventsList = myEvents;
    }

    @Override
    public List<GameEvent> getMyEvents () {
        return myEventsList;
    }

    @Override
    public List<IEffect> getMyEffects () {
        return myEffectsList;
    }

    @Override
    public ISpriteGroup getTargetedSpriteGroup () {
        return mySpriteGroup;
    }

}
