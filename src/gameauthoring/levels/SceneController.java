package gameauthoring.levels;

import java.io.File;
import engine.ILevel;
import engine.definitions.concrete.SpriteDefinition;
import graphics.ImageGraphic;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import util.Coordinate;
import util.LevelBound;


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

    private static final int MAX_HEIGHT = 400;
    private static final int MAX_WIDTH = 1150;
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
        System.out.println(image.getWidth() + " " + image.getHeight());
        System.out.println("lb " + getLevelBound(image.getWidth(), image.getHeight()).getWidth() +
                           " " + getLevelBound(image.getWidth(), image.getHeight()).getHeight());
        ImageGraphic background = new ImageGraphic(image.getWidth(), image.getHeight(), imageURL);
        myLevel.setBounds(getLevelBound(image.getWidth(), image.getHeight()));
        myLevel.setBackgroundImage(background);
    }

    private LevelBound getLevelBound (double width, double height) {
        if (width > MAX_WIDTH || height > MAX_HEIGHT) {
            double r = (1 - ((width - MAX_WIDTH) /width));
            double r2 = (1 - ((height - MAX_HEIGHT) /height));
            System.out.println(r + " " + r2);
            if(r < r2) {
                width = MAX_WIDTH;
                height = height * r;
            } else {
                height = MAX_HEIGHT;
                width = width * r2;
            }
        }
        return new LevelBound(width, height);
    }

    public void addSprite (double x, double y, SpriteDefinition spriteDefinition) {
        myLevel.add(spriteDefinition.create(), new Coordinate(x, y));
    }

    public ILevel getLevel () {
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
