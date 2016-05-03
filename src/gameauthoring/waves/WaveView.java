package gameauthoring.waves;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import engine.profile.Profile;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import splash.LocaleManager;


public class WaveView implements Glyph {

    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/default_names");
    private ResourceBundle mySize = ResourceBundle.getBundle("defaults/wave_tab_size");
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

    public void add (List<WaveBlockDefinition> list, String string, boolean infinite) {
        if (!list.isEmpty()) {

            WaveDefinition waveDef = new WaveDefinition(new ArrayList<>(list));
            waveDef.setInfinite(infinite);

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
        myWaveList.getStyleClass().add(myStyle.getString("WaveList"));
        myWaveList.setPlaceholder(new Label(myLang.getString("NoWaveContent")));
        myWaveList.setPrefWidth(Double.parseDouble(mySize.getString("WaveWidth")));
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
