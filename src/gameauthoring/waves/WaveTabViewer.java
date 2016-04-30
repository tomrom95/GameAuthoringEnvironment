package gameauthoring.waves;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.creation.cellviews.WaveDragCell;
import gameauthoring.tabs.ITabViewer;
import gameauthoring.util.ErrorMessage;
import gameauthoring.util.Glyph;
import gameauthoring.util.BasicUIFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Controls the wave for creating a wave. This includes creating wave blocks and using
 * them to build a wave.
 * 
 * @author RyanStPierre
 *
 */

public class WaveTabViewer implements Glyph, ITabViewer {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/wave_tab_size");
    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private GridPane myPane = new GridPane();
    private ObservableList<WaveBlockDefinition> myBlockList = FXCollections.observableArrayList();
    private ListView<WaveBlockDefinition> myBlockListView = new ListView<>(myBlockList);
    private WaveView myWaveDisplay;
    private CreationZone myCreationZone;
    private BlockAuthorshipView myWaveAuthorship;

    public WaveTabViewer (IGame game) {
        myWaveDisplay = new WaveView(game, game.getAuthorshipData().getCreatedWaves().getItems());
        myWaveAuthorship = new BlockAuthorshipView(game, myBlockList);
        myCreationZone = new CreationZone();
        init();
        new WaveTabController(this, myCreationZone);
    }

    public void init () {
        listInit();
        myPane.getStyleClass().add(myStyle.getString("WaveTab"));
        myPane.add(myWaveAuthorship.draw(), 1, 1, 1, 2);
        myPane.add(myBlockListView, 1, 3);
        myPane.add(myWaveDisplay.draw(), 2, 2, 1, 2);
        myPane.add(myCreationZone.draw(), 2, 1);
    }

    private void listInit () {
        myBlockListView.setPlaceholder(new Label(myLang.getString("NoBlockContent")));
        myBlockListView.setCellFactory(e -> new WaveDragCell());
        myBlockListView.setPrefWidth(Double.parseDouble(myBundle.getString("BlockListWidth")));
        myBlockListView.setPrefHeight(Double.parseDouble(myBundle.getString("BlockListHeight")));
    }

    public void createWave () {
        if (myBlockList.isEmpty()) {
            ErrorMessage error = new ErrorMessage(myLang.getString("NoBlocks"));
            error.showError();
            return;
        }
        Optional<String> option = new BasicUIFactory().getTextDialog(
                                                                myLang.getString("nameHolder"),
                                                                myLang.getString("WaveTitle"),
                                                                myLang.getString("WaveNameInstr"));
        if (option.isPresent()) {
            myWaveDisplay.add(myBlockList, option.get(), myCreationZone.isInfiniteProperty().get());
            myBlockList.clear();
        }
        
        myCreationZone.setInfiniteCheckBox(false);
    }

    public void transfer (List<WaveBlockDefinition> blocks, boolean b) {
        //TODO: set the value of the infinite box here
        myCreationZone.setInfiniteCheckBox(b);
        myBlockList.setAll(blocks);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void setWaveDislayAction (EventHandler<MouseEvent> event) {
        myWaveDisplay.interpret(event);
    }

    public WaveDefinition getWaveSelection () {
        return myWaveDisplay.getSelected();
    }

    public void save () {
        myWaveDisplay.getSelected().setInfinite(myCreationZone.isInfiniteProperty().get());
        myWaveDisplay.getSelected().setListSprites(new ArrayList<>(myBlockList));

    }

    public void exitEdit () {
        myBlockList.clear();
    }

    public void rescale (double width, double height) {
        myPane.setAlignment(Pos.CENTER);
        myPane.setScaleX(width/WIDTH);
        myPane.setScaleY(height/(HEIGHT - 15));
       
    }

}
