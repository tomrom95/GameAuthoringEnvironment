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

/**
 * Winning, losing and other end conditions
 * @author RyanStPierre
 *
 */

public class LevelConditionView extends ConditionViewer {

    ResourceBundle myBundle = ResourceBundle.getBundle("defaults/level_cond");
    
    public LevelConditionView (IGame game, ILevel level) {
        super(level.getConditionsListProperty());
        init();
        new LevelConditionController(this, game, level);
    }

    private void init () {
        add(createLeft(), 1, 0, 1, 1);
        add(createMiddle(), 2, 0, 1, 1);
        initListView();
        setOptionStyle();
    }

    private void setOptionStyle () {
        getOptions().setCellFactory(cell -> new CardCell(parse(myBundle.getString("CardSize"))));
        getOptions().getStyleClass().add("purple_outline");
    }

    private Node createMiddle () {
        getEditor().setPrefWidth(parse(myBundle.getString("MiddleWidth")));
        getEditor().setPrefHeight(parse(myBundle.getString("MiddleHeight")));
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
        getListView().setMinWidth(Double.parseDouble(myBundle.getString("ListViewWidth")));
        getListView().setMaxWidth(Double.parseDouble(myBundle.getString("ListViewWidth")));
        getListView().setPrefHeight(Double.parseDouble(myBundle.getString("ListViewHeight")));
        getListView().getStyleClass().add("purple_outline");
        add(getListView(), 3, 0, 1, 1);
    }
    
    private double parse (String string) {
       return Double.parseDouble(string);
    }

}
