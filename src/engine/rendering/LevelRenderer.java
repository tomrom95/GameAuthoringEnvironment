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


public abstract class LevelRenderer implements IRenderer {
    private static final int MAX_HEIGHT = 400;
    private static final int MAX_WIDTH = 800;
    private boolean firstTime;

    private Pane myPane;

    public LevelRenderer (Pane pane) {
        myPane = pane;
        firstTime = true;
    }

    @Override
    public void render () {
        // myPane.getChildren().clear();
        if (firstTime) {
            drawBackground(getBackgroundURL());
            firstTime = false;
        }
        drawSprites();

    }

    public Pane getPane () {
        return myPane;
    }

    abstract void drawSprites ();

    abstract String getBackgroundURL ();

    protected void draw (Node node, Drawable sprite) {
        Coordinate location = sprite.getLocation();
        node.relocate(location.getX() - sprite.getDrawer().getGraphic().getWidth().get() / 2,
                      location.getY() - sprite.getDrawer().getGraphic().getHeight().get() / 2);
        myPane.getChildren().add(node);
    }

    private void drawBackground (String url) {
        if (url == null)
            return;
        Image img = new Image(url, MAX_WIDTH, MAX_HEIGHT, true, true);
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
