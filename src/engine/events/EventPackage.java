package engine.events;

import java.util.List;
import engine.IEventPackage;
import engine.ISpriteGroup;
import engine.effects.IEffect;

/**
 * TODO Need to alter the definition classes to use this class instead
 * @author jonathanim
 *
 */
public class EventPackage implements IEventPackage {

    private ISpriteGroup mySpriteGroup;
    private List<IEffect> myEffectsList;
    private List<GameEvent> myEventsList;

    public EventPackage (ISpriteGroup myGroup, List<IEffect> myEffects, List<GameEvent> myEvents) {
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
