package gameauthoring.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;


/**
 * Gives the author the ability to chose the levels in which to proceed after winning and losing
 *
 * @author RyanStPierre
 *
 */

public class TransitionView implements Glyph {

    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/transition_options");
    private ResourceBundle mySize = ResourceBundle.getBundle("defaults/level_cond");

    private UIFactory myFactory = new BasicUIFactory();
    private VBox myPane = new VBox();
    private ComboBox<ILevel> myWinningLevel;
    private ComboBox<ILevel> myLosingLevel;
    private List<Node> myCombos;

    public TransitionView (IGame game, ILevel level) {
        stylePane();
        init(game.getLevelManager().getLevels());
        new TransitionController(this, level.getNextLevelManager());
    }

    private void stylePane () {
        myPane.setMinWidth(Double.parseDouble(mySize.getString("PaneWidth")));
        myPane.setAlignment(Pos.TOP_CENTER);
        myPane.getStyleClass().add("transition_pane");
    }

    /**
     * Initializes the combo-boxes and gives them the labels as specified in the d
     *
     * @param levels
     */
    private void init (ObservableList<ILevel> levels) {
        initCombos(levels);
        for (int i = 0; i < myCombos.size(); i++) {
            add(createPacket(myBundle.getString(Integer.toString(i)), myCombos.get(i)));
        }
    }

    private void initCombos (ObservableList<ILevel> levels) {
        myCombos = new ArrayList<>();
        myWinningLevel = createCombo(levels);
        myLosingLevel = createCombo(levels);
    }

    private ComboBox<ILevel> createCombo (ObservableList<ILevel> levels) {
        ComboBox<ILevel> comboBox = new ComboBox<>(levels);
        comboBox.setCellFactory(c -> new NameCellView<>());
        comboBox.setButtonCell(new NameCellView<>());
        myCombos.add(comboBox);
        return comboBox;
    }

    private Node createPacket (String title, Node node) {
        VBox vbox = new VBox(Double.parseDouble(mySize.getString("Padding")));
        vbox.getChildren().add(myFactory.createSubTitleLabel(title));
        vbox.getChildren().add(node);
        return vbox;
    }

    private void add (Node node) {
        myPane.getChildren().add(node);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void setWinAction (EventHandler<ActionEvent> event) {
        myWinningLevel.setOnAction(event);
    }

    public void setLoseAction (EventHandler<ActionEvent> event) {
        myLosingLevel.setOnAction(event);
    }

    public ILevel getWinSelection () {
        return myWinningLevel.getSelectionModel().getSelectedItem();
    }

    public ILevel getLoseSelection () {
        return myLosingLevel.getSelectionModel().getSelectedItem();
    }

}
