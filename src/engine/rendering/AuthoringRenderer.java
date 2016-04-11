package engine.rendering;

import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.levels.sprites.OnScreenSprite;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class AuthoringRenderer extends LevelRenderer{
    
    private ILevel myLevel;

    public AuthoringRenderer (ILevel level, Pane pane) {
        super(pane);
        myLevel = level;
    }
    
    private void createOnScreenSprite (ISprite sprite) {
        Node spriteNode = (new OnScreenSprite(this, myLevel, sprite)).draw();
        this.draw(spriteNode, sprite);
    }

    @Override
    void drawSprites () {
        myLevel.getSprites().forEach(sprite -> createOnScreenSprite(sprite));
    }

    @Override
    String getBackgroundURL () {
        return myLevel.getBackgroundImage().getUrlProperty().get();
    }

    

}
