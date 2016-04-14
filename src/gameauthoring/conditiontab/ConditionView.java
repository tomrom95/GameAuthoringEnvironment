package gameauthoring.conditiontab;

import engine.Game;
import engine.conditions.ICondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;


public class ConditionView extends ListDisplay<ICondition> {

    private static final double SPACING = 10;
    private ComboBox<String> myConditionOptions;

    public ConditionView (Game myGame) {
        super(myGame.getConditionManager().getConditionListProperty());
        init();
        new CondController(this, myGame);
    }

    private void init () {
        getPane().topProperty().set(createTop());

    }

    private Node createTop () {
        HBox hbox = new HBox(SPACING);

        myConditionOptions = new ComboBox<>(getOptions());
        hbox.getChildren().addAll(myConditionOptions);
        return hbox;
    }

    private ObservableList<String> getOptions () {
        // TODO resource file
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("OnClickCondition");
        list.add("OnCollisionCondition");
        list.add("OnGlobalAttribute");
        list.add("OnSpriteAttribute");
        return list;
    }

    public void applyToCombo (EventHandler<ActionEvent> event) {
        myConditionOptions.setOnAction(event);
    }

    public String getComboSelection () {
        return myConditionOptions.getSelectionModel().getSelectedItem();
    }

    public void setCenter (Node specific) {

        getPane().centerProperty().set(specific);
    }

}
