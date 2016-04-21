package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.Game;
import engine.IGame;
import engine.conditions.ICondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class GameConditionView extends ListDisplay<ICondition> {

    private static final String PATH = "defaults/ConditionView";
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);
    private Pane myEditor;
    private ListView<String> myOptions;

    public GameConditionView (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        init();
        new GameConditionController(this, game);
    }

    private void init () {
        initListView();
        add(createLeft(), 0, 0, 1, 3);
        add(createTop(), 1, 1, 1, 1);
        
    }

    private void initListView () {
        getListView().setPrefWidth(1100);
        getListView().setPrefHeight(200);
        add(getListView(), 0, 3, 2, 1);
    }

    private Node createLeft () {
        myOptions = new ListView<>(getOptions());
        return myOptions;
    }

    private Node createTop () {
        myEditor = new Pane();
        myEditor.setPrefWidth(700);
        myEditor.setPrefHeight(400);
        myEditor.setStyle("-fx-background-color: #d0d0e1;");
        myEditor.getChildren().add(getPlus());
        return myEditor;
    }

    private Node getPlus () {
        Image image = new Image("/images/gray-plus-md.png");
        return new ImageView(image);
    }

    public void populate (Node node) {
        myEditor.getChildren().clear();
        myEditor.getChildren().add(node);
    }

    public void applyToOptions (EventHandler<MouseEvent> onClick) {
        myOptions.setOnMouseClicked(onClick);
    }

    public String getSelection () {
        return myOptions.getSelectionModel().getSelectedItem();
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

    public void setCenter (Node specific) {
        add(specific, 1, 2, 1, 1);
    }

}
