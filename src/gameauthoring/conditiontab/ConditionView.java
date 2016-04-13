package gameauthoring.conditiontab;

import engine.Game;
import engine.conditions.ICondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class ConditionView extends ListDisplay<ICondition> {

    private static final double SPACING = 10;
    private Button myCreateButton;
    private ComboBox<String> myConditionOptions;
    
    public ConditionView (Game myGame) {
        super(myGame.getConditionManager().getConditionListProperty());
        init();
        CondController controller = new CondController(this, myGame);
    }

    private void init () {
        getPane().topProperty().set(createTop());
       
    }

    private Node createTop () {
        HBox hbox = new HBox (SPACING);
        myCreateButton = createButton("+");
        myConditionOptions = new ComboBox<>(getOptions());
        hbox.getChildren().addAll(myConditionOptions, myCreateButton);
        return hbox;
    }
    
    private ObservableList<String> getOptions () {
        //TODO resource file
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("OnClickCondition");
        list.add("OnCollisionCondition");
        list.add("OnGlobalAttribute");
        list.add("OnSpriteAttribute");
        return list;
    }

    private Button createButton (String string) {
        Button button = new Button(string);
        return button;
    }

    public void applyToButton (EventHandler<MouseEvent> event) {
        myCreateButton.setOnMouseClicked(event);
    }

    public String getComboSelection () {
        return myConditionOptions.getSelectionModel().getSelectedItem();
    }

    public void setCenter (Node specific) {
        
        getPane().centerProperty().set(specific);
    }

}
