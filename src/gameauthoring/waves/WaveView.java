package gameauthoring.waves;

import java.util.ArrayList;
import engine.IGame;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import engine.profile.Profile;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class WaveView implements Glyph {

    ResourceBundle myBundle = ResourceBundle.getBundle("defaults/default_names");
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
            waveDef.setProfile(new Profile(getName(string)));
            myGame.getAuthorshipData().addWave(waveDef);
        }
    }

    private String getName (String string) {
        
        if (string != null) {
            return string;
        }
        
        return myBundle.getString("Wave");
    }

    private void setWaveList (ObservableList<WaveDefinition> list) {
        myWaveList = new ListView<>(list);
        myWaveList.setCellFactory(e -> new NameCellView<WaveDefinition>());
    }
    
    private void setGame (IGame game) {
        myGame = game;
    }

    public void interpret (EventHandler<MouseEvent> event) {
       myWaveList.setOnMouseClicked(event);
        
    }

    public WaveDefinition getSelected () {
       return myWaveList.getSelectionModel().getSelectedItem();
    }


}
