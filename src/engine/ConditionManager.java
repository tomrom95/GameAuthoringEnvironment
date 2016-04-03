package engine;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


public class ConditionManager implements IConditionManager {

    private ObservableList<ObjectProperty<ICondition>> myConditions;

    public ConditionManager () {
        myConditions = FXCollections.observableArrayList();
    }

    @Override
    public void update (TimeDuration duration) {
        getConditionListProperty().forEach(condition -> condition.get().update(duration));
    }

    @Override
    public ObservableList<ObjectProperty<ICondition>> getConditionListProperty () {
        return myConditions;
    }

}
