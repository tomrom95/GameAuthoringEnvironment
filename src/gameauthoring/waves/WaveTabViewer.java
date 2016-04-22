package gameauthoring.waves;

import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.creation.cellviews.WaveDragCell;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * 
 * @author RyanStPierre
 *
 */

public class WaveTabViewer implements Glyph {

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

    private void init () {

        myBlockListView.setCellFactory(e -> new WaveDragCell());
        myBlockListView.setPrefWidth(400);
        myPane.add(myWaveAuthorship.draw(), 1, 1);
        myPane.add(myBlockListView, 2, 1, 1, 4);
        myPane.add(myWaveDisplay.draw(), 1, 4);
        myPane.add(myCreationZone.draw(), 1, 3);

    }

    public void createWave () {
        myWaveDisplay.add(myBlockList, myCreationZone.getText());
        myBlockList.clear();
    }
    
    public void transfer (List<WaveBlockDefinition> blocks) {
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
        myWaveDisplay.getSelected().setListSprites(new ArrayList<>(myBlockList));
    }
    
    public void exitEdit () {
        myBlockList.clear();
    }

}
