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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.BundleOperations;

/**
 * Gives the author the ability to chose the levels in which to proceed after winning and losing
 * @author RyanStPierre
 *
 */

public class TransitionView implements Glyph {

    private static final double CUSHION = 10;
    private UIFactory myFactory = new BasicUIFactory();
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/transition_options");
    private VBox myPane = new VBox(CUSHION);
    private ComboBox<ILevel> myWinningLevel;
    private ComboBox<ILevel> myLosingLevel;
    private List<Node> myCombos;

    public TransitionView (IGame game, ILevel level) {
        myPane.setMinWidth(180);
        myPane.setAlignment(Pos.TOP_CENTER);
        myPane.getStyleClass().add("transition_pane");
        init(game.getLevelManager().getLevels());
        new TransitionController(this, level.getNextLevelManager());
    }

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
        VBox vbox = new VBox(CUSHION/2);
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
