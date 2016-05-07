// This entire file is part of my masterpiece.
// Dhrumil Patel

package engine.events;

import java.util.List;
import engine.IEventPackage;
import engine.ISpriteGroup;
import engine.effects.IEffect;


/**
 * This class provides the framework to store information about sprite groups, 
 * effects, and events that are responsible for handling the logic of interactions 
 * that exist in the game. 
 * 
 * I believe this class demonstrates great design because we completely redesigned how we interpret the 
 * notion of interactions in our game engine. The event package is responsible for holding the sprite groups, effects, and
 * events that exists in the game. With this consolidation of information into one package, we were able to create a myriad 
 * of condition, action sequences that alter the game or sprite state of the game. 
 * 
 * With this framework, our game engine can generically handle applying any interaction to a a group or single sprite that 
 * exists in our game. In OnCollisionCondition.java, I demonstrate how my partner and I apply this framework to demonstrate
 * collisions to alter sprite attributes or behaviors. 
 *
 *@author Dhrumil
 *@author Jonathan
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
