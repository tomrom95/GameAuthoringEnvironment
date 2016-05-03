package engine.conditions;

import engine.IEventPackage;
import engine.IGame;
import util.TimeDuration;


public class OnTimeCondition extends Condition implements ICondition {

    private IGame myGame;
    private TimeDuration myCycleLength;

    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    private TimeDuration myTimeSinceLastTrigger = new TimeDuration();

    public OnTimeCondition (IGame game,
                            TimeDuration cycleLength,
                            IEventPackage otherPackage,
                            IEventPackage globalPackage) {
        myGame = game;
        myCycleLength = cycleLength;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;
    }

    @Override
    public void update (TimeDuration duration) {
        myTimeSinceLastTrigger.increase(duration);
        checkTrigger();
    }

    private void checkTrigger () {
        if (myTimeSinceLastTrigger.getMillis() > myCycleLength.getMillis()) {
            myTimeSinceLastTrigger.setToZero();
            applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
        }
    }

}
