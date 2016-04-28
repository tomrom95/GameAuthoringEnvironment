package gameauthoring.levels.waves;

import java.util.Locale;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import engine.rendering.AuthoringRenderer;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Allows the user to generate a spawner on the fly in the view and put it in the level
 * The user can drag any number of waves into a spawner and place it on the level screen
 * 
 * @author RyanStPierre
 *
 */

public class SpawnerAuthoringView implements Glyph {

    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);
    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private VBox myPane = new VBox();
    private UIFactory myFactory = new BasicUIFactory();
    private WaveOptionView myWaveOptions;
    private SpawnerView mySpawner;

    public SpawnerAuthoringView (IGame game, ILevel level, AuthoringRenderer renderer) {
        myWaveOptions = new WaveOptionView(game);
        mySpawner = new SpawnerView(game, level, renderer);
        initPane();
        new SpawnerAuthoringController(mySpawner, myWaveOptions);
    }

    private void initPane () {
        myPane.getStyleClass().add(myStyle.getString("SpawnerVBox"));
        myPane.setAlignment(Pos.TOP_CENTER);
        myPane.getChildren().add(myFactory.createSubTitleLabel(myLang.getString("SpawnerTitle")));
        myPane.getChildren().add(mySpawner.draw());
        myPane.getChildren().add(myWaveOptions.draw());      
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public double getWidth () {
        return myPane.getWidth();
    }

}
