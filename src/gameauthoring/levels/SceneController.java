package gameauthoring.levels;

import java.io.File;
import engine.ILevel;
import engine.ISprite;
import javafx.stage.FileChooser;
import util.Coordinate;


public class SceneController {

    private ILevel myLevel;
    
    public SceneController(ILevel level) {//, LevelRenderer renderer) {
        myLevel = level;
    }
    
    public void uploadNewBackground () {
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null)
            return;
        //myScene.setBackground(newImage.toURI().toString());
    }

    public void addSprite (double x, double y, ISprite sprite) {
        myLevel.add(sprite, new Coordinate(x, y));
    }
    
}
