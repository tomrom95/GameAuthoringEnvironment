package engine.rendering;

import java.util.Collection;
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
    protected String getBackgroundURL () {
        return myLevel.getBackgroundImage().getUrlProperty().get();
    }

    public GridRenderer getGrids () {
        return myTileView;
    }

    public ILevel getLevel () {
        return myLevel;
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
