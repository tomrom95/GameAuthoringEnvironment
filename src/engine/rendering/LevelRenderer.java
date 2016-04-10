package engine.rendering;

import engine.Drawable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import util.Coordinate;

public abstract class LevelRenderer implements IRenderer{
    
    private Pane myPane;

    public LevelRenderer (Pane pane) {
        myPane = pane;
    }

    @Override
    public void render () {
        myPane.getChildren().clear();
        drawBackground(getBackgroundURL());
        drawSprites();
    }
    
    public Pane getPane() {
        return myPane;
    }

    abstract void drawSprites ();
    
    abstract String getBackgroundURL();

    protected void draw (Node node, Drawable sprite) {
        Coordinate location = sprite.getLocation().get();
        node.relocate(location.getX(), location.getY());
        myPane.getChildren().add(node);
    }    

    private void drawBackground (String url) {
        Image img = new Image(url);
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
