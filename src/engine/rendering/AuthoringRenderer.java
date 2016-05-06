// This entire file is part of my masterpiece
// Ryan St.Pierre
/**
 * Most of the changes that were made to this class were related to extracting methods into the
 * parent class
 * 
 * I did delete the getLevel method, which gave public access to the Level. The reason that access
 * was needed was for the OnScreenSprite to grab access to the bit map. Thus, I created a method to
 * give access to this needed bit map without giving access to the whole level. This class should
 * not give access to the level to anyone
 */
package engine.rendering;

import java.util.Collection;
import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.levels.sprites.OnScreenSprite;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import util.ScaleRatio;


/**
 * Represents the current level being displayed in the authoring environment
 * 
 * @author RyanStPierre
 *
 */
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

    /**
     * @param sprite to be represented
     * @return the node of an OnScreenSprite. An OnScreenSprite is a more robust than a drawable. It
     *         is used by authoring to allow users to interact with the sprite (relocate, add paths,
     *         etc.)
     */
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
