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

    @Override
    protected Collection<ISprite> getList () {
        return myLevel.getSprites();
    }

    @Override
    protected Node getNode (ISprite item) {
        return createOnScreenSprite(item);
    }
    
    private Node createOnScreenSprite (ISprite sprite) {
        return (new OnScreenSprite(this, myLevel, sprite, getScale())).draw();
    }

    @Override
    protected String getBackgroundURL () {
        return myLevel.getBackgroundImage().getUrlProperty().get();
    }

    @Override
    protected double boundHeight () {
        return myLevel.getBounds().getHeight();
    }

    @Override
    protected double boundWidth () {
        return myLevel.getBounds().getWidth();
    }
    
    public GridRenderer getGrids () {
        return myTileView;
    }

    public void updateNewTiles () {
        myTileView.updateTileNumbers();
    }
    
    public void scaleTitles () {
        myTileView.redraw();
    }

    public boolean[][] getBitMap () {
        return myLevel.getPlaceableTileManager().getPlaceableMap()
                .getBitMap();
    }

}
