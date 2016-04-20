package engine;

import java.util.List;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.TimeDuration;


/**
 * This class serves as an implementation of INextLevelManager and contains the logical checks to
 * manage
 * valid level progression. This class is updateable and checks for level completion on each update.
 *
 */

public class NextLevelManager implements INextLevelManager {

    private boolean myShouldProceedToNext;
    private ILevel myWinLevel;
    private ILevel myLoseLevel;

    private ILevel myNextLevel;

    public NextLevelManager () {
        this(null, null);
    }

    public NextLevelManager (ILevel winLevel, ILevel loseLevel) {
        myNextLevel = null;
        myWinLevel = winLevel;
        myLoseLevel = loseLevel;
        myShouldProceedToNext = false;
    }

    @Override
    public void update (TimeDuration duration) {
        // Do nothing
    }

    @Override
    public boolean shouldGoToNextLevel () {
        return myShouldProceedToNext;
    }

    @Override
    public void internalizeGameEvents (List<GameEvent> list) {
        list.forEach(event -> checkAndRespondGameEvent(event));
    }

    private void checkAndRespondGameEvent (GameEvent event) {
        if (event.equals(EventType.WIN)) {
            setNextLevel(myWinLevel);
        }
        if (event.equals(EventType.LOSE)) {
            setNextLevel(myLoseLevel);
        }
    }

    private void setNextLevel (ILevel setLevel) {
        myNextLevel = setLevel;
        myShouldProceedToNext = true;
    }

    @Override
    public ILevel getNextLevel () {
        if (myNextLevel == null) {
            return new Level();
        }
        return myNextLevel;
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        // Do Nothing

    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        // Do Nothing

    }

    @Override
    public void setWinLevel (ILevel winLevel) {
        myWinLevel = winLevel;

    }

    @Override
    public void setLoseLevel (ILevel loseLevel) {
        myLoseLevel = loseLevel;

    }

}
