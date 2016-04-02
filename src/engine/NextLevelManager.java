package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


public class NextLevelManager implements INextLevelManager {
    private ObservableList<ObjectProperty<INextLevelCondition>> myNextLevelConditions;
    private SimpleBooleanProperty myShouldProceedToNext;
    private ILevel myNextLevel;

    NextLevelManager () {
        myNextLevelConditions = FXCollections.observableArrayList();
        myShouldProceedToNext = new SimpleBooleanProperty(false);
    }

    @Override
    public ObservableList<ObjectProperty<INextLevelCondition>> getConditionListProperty () {
        return myNextLevelConditions;
    }

    @Override
    public void update (TimeDuration duration) {
        for (ObjectProperty<INextLevelCondition> condition : myNextLevelConditions) {
            condition.get().update(duration);
            if (condition.get().shouldProceed()) {
                myShouldProceedToNext.set(true);
                myNextLevel = condition.get().getNextLevel();
            }
        }
    }

    @Override
    public boolean shouldGoToNextLevel () {
        return myShouldProceedToNext.get();
    }

    @Override
    public ILevel getNextLevel () {
        return myNextLevel;
    }

}
