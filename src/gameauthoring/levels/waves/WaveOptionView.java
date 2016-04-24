package gameauthoring.levels.waves;

import engine.IGame;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class WaveOptionView implements Glyph{

    private ListView<WaveDefinition> myWaveOptions;
    
    public WaveOptionView (IGame game) {
        myWaveOptions = new ListView<>(game.getAuthorshipData().getCreatedWaves().getItems());
        init();
    }

    private void init () {
        myWaveOptions.setMaxWidth(100);
        myWaveOptions.setCellFactory(cell -> new NameCellView<WaveDefinition>());        
    }

    @Override
    public Node draw () {
       return myWaveOptions;
    }

    public void setTarget (SpawnerView mySpawnerView) {
        myWaveOptions.setCellFactory(cell -> new WaveDropCell(mySpawnerView));
        
    }
}
