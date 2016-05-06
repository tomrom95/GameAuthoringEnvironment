package engine.rendering;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.levels.sprites.OnScreenSprite;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import util.ScaleRatio;


public class AuthoringRenderer extends LevelRenderer<ISprite> {

    private ILevel myLevel;
    private GridRenderer myTileView;

    public AuthoringRenderer (ILevel level, Pane pane, GridPane gridPane, ScaleRatio scale) {
        super(pane, scale);
        myLevel = level;
        myTileView = new GridRenderer(level, gridPane, scale);
    }

    private Node createOnScreenSprite (ISprite sprite) {
        return (new OnScreenSprite(this, myLevel, sprite, getScale())).draw();
        // this.draw(spriteNode, sprite);
    }

    public void updateNewTiles () {
        myTileView.updateTileNumbers();
    }

    @Override
    String getBackgroundURL () {
        return myLevel.getBackgroundImage().getUrlProperty().get();
    }

    public GridRenderer getGrids () {
        return myTileView;
    }

    public ILevel getLevel () {
        return myLevel;
    }

    @Override
    public void redrawBackground () {
        for (Drawable sprite : getNodeMap().keySet()) {
            resize(sprite, getNodeMap().get(sprite));
        }
    }

    private void resize (Drawable sprite, Node node) {
        draw(node, sprite);
    }

    @Override
    protected double scaledHeight () {
        return getScale().scale(myLevel.getBounds().getHeight());
    }

    @Override
    protected double scaledWidth () {
        return getScale().scale(myLevel.getBounds().getWidth());
    }

    public void scaleTitles () {
        myTileView.redraw();

    }

    @Override
    protected Collection<ISprite> getList () {
        return myLevel.getSprites();
    }

    @Override
    protected Node getNode (ISprite item) {
        return createOnScreenSprite(item);
    }

}
