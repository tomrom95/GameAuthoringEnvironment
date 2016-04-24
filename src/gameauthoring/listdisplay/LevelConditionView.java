package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import engine.conditions.ICondition;
import gameauthoring.creation.cellviews.CardCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.BundleOperations;


public class LevelConditionView extends ConditionView {

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
        getEditor().setPrefWidth(700);
        getEditor().setPrefHeight(200);
        return getEditor();
    }

    private Node createLeft () {
        return getOptions();
    }

    protected ObservableList<String> getList () {
        return BundleOperations.getValuesAsObservable(getMyBundle());
    }

    private ResourceBundle getMyBundle () {
        return ResourceBundle.getBundle("defaults/end_condition_options");
    }

    private void initListView () {
        getListView().setPrefWidth(80);
        getListView().setPrefHeight(200);
        add(getListView(), 3, 0, 1, 1);
        getOptions().setCellFactory(cell -> new CardCell(50));
    }

}
