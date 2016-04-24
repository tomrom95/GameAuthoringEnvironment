package gameauthoring.levels.waves;

import engine.IGame;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class SpawnerAuthoringView implements Glyph {

    private IGame myGame;
    private VBox myPane = new VBox();
    private WaveOptionView myWaveOptions;
    private SpawnerView mySpawner;

    public SpawnerAuthoringView (IGame game) {
        myGame = game;
        myWaveOptions = new WaveOptionView (game);
        mySpawner = new SpawnerView ();
        new SpawnerAuthoringController(mySpawner, myWaveOptions);
    }

    @Override
    public Node draw () {
        myPane.getChildren().add(mySpawner.draw());
        myPane.getChildren().add(myWaveOptions.draw());
        return myPane;
    }

}
