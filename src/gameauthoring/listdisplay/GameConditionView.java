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
import util.BundleOperations;


public class GameConditionView extends ConditionView {

    private static final String PATH = "defaults/game_condition_view";

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
        return getOptions();
    }

    private Node createTop () {
        
        getEditor().setPrefWidth(700);
        getEditor().setPrefHeight(400);
        getEditor().setStyle("-fx-background-color: #d0d0e1;");
        getEditor().getChildren().add(getPlus());
        return getEditor();
    }

    private Node getPlus () {
        Image image = new Image("/images/gray-plus-md.png");
        return new ImageView(image);
    }

    protected ObservableList<String> getList () {
        return BundleOperations.getValuesAsObservable(getBundle());
    }

    private ResourceBundle getBundle () {
        return ResourceBundle.getBundle(PATH);
    }

    public void setCenter (Node specific) {
        add(specific, 1, 2, 1, 1);
    }

}
