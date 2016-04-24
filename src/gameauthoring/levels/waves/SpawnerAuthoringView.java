package gameauthoring.levels.waves;

import engine.IGame;
import engine.ILevel;
import engine.rendering.AuthoringRenderer;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


public class SpawnerAuthoringView implements Glyph {

    private VBox myPane = new VBox();
    private WaveOptionView myWaveOptions;
    private SpawnerView mySpawner;

    public SpawnerAuthoringView (IGame game, ILevel level, AuthoringRenderer renderer) {
        myWaveOptions = new WaveOptionView (game);
        mySpawner = new SpawnerView (game, level, renderer);
        new SpawnerAuthoringController(mySpawner, myWaveOptions);
    }

    @Override
    public Node draw () {
        myPane.getChildren().add(mySpawner.draw());
        myPane.getChildren().add(myWaveOptions.draw());
        return myPane;
    }

}
