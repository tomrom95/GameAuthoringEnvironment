package gameauthoring.levels;

import java.io.File;
import engine.ILevel;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
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
        setBackground(newImage.toURI().toString());
    }
    
    public void setBackground(String imageURL) {
        ImageGraphic background = new ImageGraphic(800, 500, imageURL);
        myLevel.getBackgroundImageProperty().set(background);
    }

    public void addSprite (double x, double y, ISprite sprite) {
        myLevel.add(sprite, new Coordinate(x, y));
    }
    
}
