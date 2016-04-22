package gameauthoring.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.BundleOperations;


public class TransitionView implements Glyph {

    ResourceBundle myBundle = ResourceBundle.getBundle("defaults/transition_options");
    private VBox myPane = new VBox();
    private ComboBox<ILevel> myWinningLevel;
    private ComboBox<ILevel> myLosingLevel;
    private List<Node> myCombos;

    public TransitionView (IGame game, ILevel level) {
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
        HBox hbox = new HBox();
        hbox.getChildren().add(new Label(title));
        hbox.getChildren().add(node);
        return hbox;
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