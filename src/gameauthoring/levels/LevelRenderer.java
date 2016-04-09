package gameauthoring.levels;

import engine.ILevel;
import engine.rendering.IRenderer;
import engine.sprite.ISprite;
import gameauthoring.levels.sprites.OnScreenSprite;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class LevelRenderer implements IRenderer{
    
    private Pane myPane;
    private ILevel myLevel;

    public LevelRenderer (ILevel level, Pane pane) {
        myPane = pane;
        myLevel = level;
    }
    
    public Pane getPane(){
        return myPane;
    }
    
    @Override
    public void render () {
        myPane.getChildren().clear();
        myLevel.getSprites().forEach(sprite -> createOnScreenSprite(sprite));
    }
    
    private void createOnScreenSprite (ObjectProperty<ISprite> sprite) {
        myPane.getChildren().add((new OnScreenSprite(this, myLevel, sprite)).draw());
    }

    public void setBackground (String imagePath) {
        Image img = new Image(imagePath, SceneCreator.WIDTH, SceneCreator.HEIGHT, true, true);
        BackgroundImage background = new BackgroundImage(img,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        myPane.setBackground(new Background(background));
        myPane.setMinWidth(img.getWidth());
        myPane.setMinHeight(img.getHeight());
    }

    

}
