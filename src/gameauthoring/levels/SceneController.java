package gameauthoring.levels;

import java.io.File;
import engine.ILevel;
import engine.definitions.SpriteDefinition;
import graphics.ImageGraphic;
import javafx.stage.FileChooser;
import util.Coordinate;

/**
 * Controller for the scene actions. Can do actions like
 * swapping the background and adding a sprite to the screen.
 * This is also used by the game player to add sprites to the screen 
 * @author Tommy
 *
 */
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
        System.out.println("my level should have : " + myLevel.getSprites().size());
    }
    
}
