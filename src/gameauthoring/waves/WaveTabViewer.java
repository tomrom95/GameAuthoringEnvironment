package gameauthoring.waves;

import java.util.ArrayList;
import engine.IGame;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * 
 * @author RyanStPierre
 *
 */

public class WaveTabViewer implements Glyph {

    private UIFactory myFactory = new UIFactory();
    private GridPane myPane = new GridPane();
    private ObservableList<WaveBlockDefinition> myBlockList = FXCollections.observableArrayList();
    private ListView<WaveBlockDefinition> myBlockListView = new ListView<>(myBlockList);
    private WaveView myWaveDisplay;
    private BlockAuthorshipView myWaveAuthorship;
    private TextField myWaveName;

    public WaveTabViewer (IGame game) {

        myWaveDisplay = new WaveView(game, game.getAuthorshipData().getCreatedWaves().getItems());
        myWaveAuthorship = new BlockAuthorshipView(game, myBlockList);
        init();
    }

    private void init () {

        myBlockListView.setCellFactory(e -> new WaveDragCell());
        myBlockListView.setPrefWidth(400);
        myPane.add(myWaveAuthorship.draw(), 1, 1);
        myPane.add(myBlockListView, 2, 1, 1, 4);
        myPane.add(myWaveDisplay.draw(), 1, 4);
        myPane.add(createWaveCreationDisplay(), 1, 3);

    }

    private Node createWaveCreationDisplay () {
        
        HBox hbox = new HBox();
        myWaveName = myFactory.createTextField();
        hbox.getChildren().add(myWaveName);
        hbox.getChildren().add(myFactory.createButton("Add wave", e -> transfer()));
        return hbox;
        
    }

    private void transfer () {
        myWaveDisplay.add(myBlockList, myWaveName.getText());
        myBlockList.clear();
    }

    @Override
    public Node draw () {
        return myPane;
    }

}
