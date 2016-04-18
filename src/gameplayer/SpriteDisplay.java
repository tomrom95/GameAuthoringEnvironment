package gameplayer;

import engine.Drawable;
import engine.rendering.UnscaledFactory;
import engine.sprite.ISprite;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SpriteDisplay implements Glyph{

    
    private Pane myPane = new Pane();
    private ISprite mySprite;
   
    public Node draw () {
       return myPane;
    }
    
    public void populate (Drawable drawn) {
        //setSprite(sprite);
        myPane.getChildren().clear();
        myPane.getChildren().add(drawn.getDrawer().getVisualRepresentation(new UnscaledFactory()));
    }
    
    private void setSprite (ISprite sprite) {
        mySprite = sprite;
    }
    
    

}
