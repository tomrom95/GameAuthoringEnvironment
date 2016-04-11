package gameauthoring.levels;

import java.io.File;
import engine.ILevel;
import engine.definitions.SpriteDefinition;
import graphics.ImageGraphic;
import javafx.stage.FileChooser;
import util.Coordinate;


public class SceneController {

    private ILevel myLevel;
    
    public SceneController(ILevel level) {
        myLevel = level;
    }
    
    public void uploadNewBackground () {
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null)
            return;
        setBackground(newImage.toURI().toString());
    }
    
    public void setBackground(String imageURL) {
        ImageGraphic background = new ImageGraphic(0, 0, imageURL);
        myLevel.setBackgroundImage(background);
    }

    public void addSprite (double x, double y, SpriteDefinition spriteDefinition) {
        myLevel.add(spriteDefinition.create(), new Coordinate(x, y));
    }
    
}
