package gameauthoring.levels.waves;

import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import splash.LocaleManager;


/**
 * Displays the list view of waves the author has already created
 *
 * @author RyanStPierre
 *
 */
public class WaveOptionView implements Glyph {

    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/spawner_view");
    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");

    private ListView<WaveDefinition> myWaveOptions;

    public WaveOptionView (IGame game) {
        myWaveOptions = new ListView<>(game.getAuthorshipData().getCreatedWaves().getItems());
        init();
    }

    private void init () {
        myWaveOptions.setPrefWidth(Double.parseDouble(myBundle.getString("PrefWidth")));
        myWaveOptions.getStyleClass().add(myStyle.getString("GreenList"));
        myWaveOptions.setPlaceholder(new Label(myLang.getString("CreateWavePrompt")));
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
