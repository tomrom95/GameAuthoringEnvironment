package gameauthoring;

import engine.ICondition;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;


public class ConditionView extends ListDisplay<ICondition> {

    public ConditionView (Tab tab,
                          ObservableList<ICondition> list,
                          ObservableList<String> myOptions) {
        super(tab, list, myOptions);
        CondController controller = new CondController(this);
    }

}
