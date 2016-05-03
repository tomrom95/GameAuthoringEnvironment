package engine;

import java.util.ArrayList;
import java.util.List;
import engine.conditions.ICondition;
import engine.events.GameEvent;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/***
 * This class manages the collection of conditions that the user specifies in the authoring
 * environment. The conditions are checked during each time interval.
 *
 */

public class ConditionManager implements IConditionManager {

    private ObservableList<ICondition> myConditions;
    private List<MouseIOEvent> myMouseQueue;
    private List<KeyIOEvent> myKeyQueue;

    public ConditionManager () {
        myConditions = FXCollections.observableArrayList();
        myMouseQueue = new ArrayList<>();
        myKeyQueue = new ArrayList<>();
    }

    @Override
    public void update (TimeDuration duration) {
        getConditionListProperty().forEach(condition -> {
            condition.update(duration);
            myMouseQueue.forEach(event -> condition.registerMouseEvent(event));
            myKeyQueue.forEach(event -> condition.registerKeyEvent(event));
        });
        dequeue();
    }

    private void dequeue () {
        myMouseQueue.clear();
        myKeyQueue.clear();
    }

    @Override
    public ObservableList<ICondition> getConditionListProperty () {
        return myConditions;
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        myMouseQueue.addAll(list);
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        myKeyQueue.addAll(list);
    }

    @Override
    public void internalizeGameEvents (List<GameEvent> list) {
        // Do Nothing
    }

}
