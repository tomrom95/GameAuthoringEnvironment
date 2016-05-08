// This entire file is part of my masterpiece.
// David Maydew
// This class represents an example subclass of the Condition hierarchy, which takes advantage of
// some pre-specified behavior for applying EventPackages to specific groups. This class manifests
// the extensible nature of the Condition hierarchy and shows how it is easily adaptable to add new
// features. By separating this classes interface from its implementation, it allows the game
// loop to treat conditions equally and let them handle their own behavior internally. Additionally,
// this class utilizes the TimeDuration class to remove ambiguity about the trigger timing unit. The
// different actions of TimeDuration and the fact that it implements Comparable<> is also employed
// to facilitate the timing process and check when to trigger an event payload.
package engine.conditions;

import engine.IEventPackage;
import engine.IGame;
import util.TimeDuration;


/**
 * This class represents a specific {@link engine.conditions.ICondition} that is executed on a
 * repeated, time driven loop.
 * 
 * @author David Maydew
 *
 */
public class OnTimeCondition extends Condition implements ICondition {

    private IGame myGame;
    private TimeDuration myCycleLength;
    private IEventPackage myOtherGroupPackage;
    private IEventPackage myGlobalPackage;
    private TimeDuration myTimeSinceLastTrigger = new TimeDuration();

    /**
     * 
     * @param game to apply events to
     * @param cycleLength length of time between each trigger
     * @param otherPackage to apply to a third party group of {@link engine.sprite.ISprite}
     * @param globalPackage to apply to the global attributes of an {@link engine.IGame}
     */
    public OnTimeCondition (IGame game,
                            TimeDuration cycleLength,
                            IEventPackage otherPackage,
                            IEventPackage globalPackage) {
        myGame = game;
        myCycleLength = cycleLength;
        myOtherGroupPackage = otherPackage;
        myGlobalPackage = globalPackage;
    }

    /**
     * Increments the time since the condition was last triggered, and checks to see if the event
     * payload should fire
     */
    @Override
    public void update (TimeDuration duration) {
        myTimeSinceLastTrigger.increase(duration);
        checkTrigger();
    }

    /**
     * Checks the time since the trigger last fired, and if appropriate, fires the other and global
     * event packages and resets the timer
     */
    private void checkTrigger () {
        if (myTimeSinceLastTrigger.compareTo(myCycleLength) > 0) {
            myTimeSinceLastTrigger.setToZero();
            applyOtherAndGlobalEventPackages(myGame, myOtherGroupPackage, myGlobalPackage);
        }
    }

}
