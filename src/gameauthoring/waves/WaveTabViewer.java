package gameauthoring.waves;

import engine.IGame;
import engine.definitions.spawnerdef.WaveDataDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.Glyph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;


/**
 * 
 * @author RyanStPierre
 *
 */

public class WaveTabViewer implements Glyph {

    private GridPane myPane = new GridPane();
    private ListView<WaveDefinition> myWaveList;
    private ObservableList<WaveDataDefinition> myDataList = FXCollections.observableArrayList();
    private ListView<WaveDataDefinition> mySpriteList = new ListView<>(myDataList);
    private DataAuthorshipView myWaveAuthorship;

    public WaveTabViewer (IGame game) {
        setWaveList(game.getAuthorshipData().getCreatedWaves().getItems());
        myWaveAuthorship = new DataAuthorshipView(game, myDataList);
        init();
    }

    private void init () {
        myPane.add(myWaveAuthorship.draw(), 1, 1);
        myPane.add(mySpriteList, 2, 1);

    }

    private void setWaveList (ObservableList<WaveDefinition> list) {
        myWaveList = new ListView<>(list);
    }

    @Override
    public Node draw () {
        return myPane;
    }

}
