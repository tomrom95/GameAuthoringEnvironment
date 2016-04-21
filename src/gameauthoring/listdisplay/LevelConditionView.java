package gameauthoring.listdisplay;

import engine.IGame;
import engine.ILevel;
import engine.conditions.ICondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class LevelConditionView extends ListDisplay<ICondition> {

    private Pane myEditor = new Pane();
    private ListView<String> myOptions;

    public LevelConditionView (IGame game, ILevel level) {
        super(level.getConditionsListProperty());
        init();
        new LevelConditionController(this, game, level);
    }

    private void init () {
        add(createLeft(), 1, 0, 1, 1);
        add(createMiddle(), 2, 0, 1, 1);
        initListView();

    }

    private Node createMiddle () {
        myEditor = new Pane();
        myEditor.setPrefWidth(700);
        myEditor.setPrefHeight(200);
        myEditor.setStyle("-fx-background-color: #d0d0e1;");
        return myEditor;
    }

    private Node createLeft () {
        myOptions = new ListView<>(getOptions());
        return myOptions;
    }

    private ObservableList<String> getOptions () {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Win");
        list.add("Lose");
        return list;
    }

    private void initListView () {
        getListView().setPrefWidth(200);
        getListView().setPrefHeight(200);
        add(getListView(), 3, 0, 1, 1);
    }
    
    public void applyToOptions (EventHandler<MouseEvent> onClick) {
        myOptions.setOnMouseClicked(onClick);
    }
    
    public String getSelection () {
        return myOptions.getSelectionModel().getSelectedItem();
    }
    
    public void populate (Node node) {
        myEditor.getChildren().clear();
        myEditor.getChildren().add(node);
    }


}
