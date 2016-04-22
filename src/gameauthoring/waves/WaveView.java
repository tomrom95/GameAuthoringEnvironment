package gameauthoring.waves;

import java.util.ArrayList;
import engine.IGame;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.List;
import javafx.scene.control.ListView;


public class WaveView implements Glyph {

    private ListView<WaveDefinition> myWaveList;
    private IGame myGame;

    public WaveView (IGame game, ObservableList<WaveDefinition> list) {
        setGame(game);
        setWaveList(list);
    }

    @Override
    public Node draw () {
        return myWaveList;
    }

    public void add (List<WaveBlockDefinition> list, String string) {
        if (!list.isEmpty()) {
            WaveDefinition waveDef = new WaveDefinition(new ArrayList<>(list));
            
            myGame.getAuthorshipData().addWave(waveDef);
        }
    }

    private void setWaveList (ObservableList<WaveDefinition> list) {
        myWaveList = new ListView<>(list);
    }
    
    private void setGame (IGame game) {
        myGame = game;
    }


}
