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
import util.ScaleRatio;


/**
 *
 * TODO fix scaling of window sizes
 */
public abstract class LevelRenderer implements IRenderer {

    private Pane myPane;
    private ScaleRatio myScale;

    public LevelRenderer (Pane pane, ScaleRatio scale) {
        myScale = scale;
        myPane = pane;
    }

    @Override
    public void render () {
        drawBackground(getBackgroundURL());
        drawSprites();
    }

    public Pane getPane () {
        return myPane;
    }

    abstract void drawSprites ();

    abstract String getBackgroundURL ();

    protected void draw (Node node, Drawable sprite) {
        Coordinate location = sprite.getLocation();
        node.relocate(getScale().scale(location.getX()) -
                      sprite.getDrawer().getGraphic().getHeight().get() / 2,
                      getScale().scale(location.getY()) - sprite.getDrawer().getGraphic()
                              .getHeight().get() / 2);
        node.setScaleX(getScale().getScale());
        node.setScaleY(getScale().getScale());
        node.setVisible(sprite.getDrawer().isVisible());
        node.setRotate(sprite.getOrientation());
    }

    protected void drawBackground (String url) {
        if (url == null) {
            return;
        }
        Image img = getImage(url);
        BackgroundImage background = new BackgroundImage(img,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        myPane.setBackground(new Background(background));
        myPane.setMinWidth(img.getWidth());
        myPane.setMinHeight(img.getHeight());
    }

    public abstract void redrawBackground ();

    protected ScaleRatio getScale () {
        return myScale;
    }

    protected Image getImage (String url) {
        return new Image(url, scaledWidth(), scaledHeight(), true, true);
    }

    protected abstract double scaledHeight ();

    protected abstract double scaledWidth ();
}
