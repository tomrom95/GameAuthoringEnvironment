package engine;

import java.util.ArrayList;
import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/***
 * This class manages the collection of conditions that the user specifies in the authoring
 * environment.
 * The conditions are checked during each time interval.
 *
 */

public class ConditionManager implements IConditionManager {

    private ObservableList<ObjectProperty<ICondition>> myConditions;
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
            condition.get().update(duration);
            myMouseQueue.forEach(event -> condition.get().registerMouseEvent(event));
            myKeyQueue.forEach(event -> condition.get().registerKeyEvent(event));
        });
        dequeue();

    }

    private void dequeue () {
        myMouseQueue.clear();
        myKeyQueue.clear();
    }

    @Override
    public ObservableList<ObjectProperty<ICondition>> getConditionListProperty () {
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

}
