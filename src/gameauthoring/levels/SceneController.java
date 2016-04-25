package gameauthoring.levels;

import java.io.File;
import engine.ILevel;
import engine.definitions.concrete.SpriteDefinition;
import graphics.ImageGraphic;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import util.Coordinate;


/**
 * Controller for the scene actions. Can do actions like
 * swapping the background and adding a sprite to the screen.
 * This is also used by the game player to add sprites to the screen.
 * 
 * @author Tommy
 * @author Jin An
 *
 */
public class SceneController {

    private ILevel myLevel;

    public SceneController (ILevel level) {
        myLevel = level;
    }

    public void uploadNewBackground () {
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null)
            return;
        setBackground(newImage.toURI().toString());
    }

    public void setBackground (String imageURL) {
        Image image = new Image(imageURL);
        ImageGraphic background = new ImageGraphic(image.getWidth(), image.getHeight(), imageURL);
        myLevel.setBackgroundImage(background);
    }

    public void addSprite (double x, double y, SpriteDefinition spriteDefinition) {
        myLevel.add(spriteDefinition.create(), new Coordinate(x, y));
    }
    
    public ILevel getLevel(){
        return myLevel;
    }

    public void addSpriteToLevel (SpriteDefinition sprite) {
        myLevel.getAddableSprites().add(sprite);
    }
    
    public void removeSpriteFromLevel (SpriteDefinition sprite) {
        myLevel.getAddableSprites().remove(sprite);
    }

    public boolean isSpriteInLevel (SpriteDefinition profilable) {
        return myLevel.getAddableSprites().contains(profilable);
    }
}
