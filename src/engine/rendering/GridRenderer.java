package engine.rendering;

import util.Bounds;
import util.ScaleRatio;
import util.Tile;
import engine.ILevel;
import engine.sprite.ISprite;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Renderer for grid in the authoring environment.
 * Authors can set unplaceable terrain by clicking the tiles
 * 
 * @author Jin An
 *
 */
public class GridRenderer implements IRenderer {
    private GridPane myPane;
    private ILevel myLevel;
    private Tile[][] myBlocks;
    private int myNumBlockRow;
    private int myNumBlockCol;
    public final int BLOCK_SIZE = 25;
    private ScaleRatio myScale;

    public GridRenderer (ILevel level, GridPane pane, ScaleRatio scale) {
        myPane = pane;
        myLevel = level;
        myScale = scale;
        init();
    }

    private void init () {
        calculateTileArraySize();
        myBlocks = new Tile[myNumBlockRow][myNumBlockCol];
        initializeGridLines();
    }

    private void calculateTileArraySize () {
        myNumBlockRow = (int) (myScale.scale(myLevel.getBounds().getHeight()) / BLOCK_SIZE);
        myNumBlockCol = (int) (myScale.scale(myLevel.getBounds().getWidth()) / BLOCK_SIZE);
      
       
    }

    private void initializeGridLines () {
        myPane.setGridLinesVisible(true);
        myPane.setMaxSize(myScale.scale(myLevel.getBounds().getWidth()), myScale.scale(myLevel.getBounds().getHeight()));
        for (int i = 0; i < myNumBlockRow; i++) {
            for (int j = 0; j < myNumBlockCol; j++) {
                Tile tile = new Tile(new Rectangle(BLOCK_SIZE,BLOCK_SIZE), i, j);
                tile.getTile().setFill(Color.TRANSPARENT);
                tile.getTile().setOnMouseClicked(e -> handleMouseClick(tile));
                myBlocks[i][j] = tile;
                myPane.add(tile.getTile(), j, i);
            }
        }
       
    }

    private void handleMouseClick (Tile tile) {
        if (checkClickable(tile)) {
            if (tile.getTile().getFill() == Color.TRANSPARENT) {
                tile.getTile().setFill(Color.RED);
            }
            else if (tile.getTile().getFill() == Color.RED) {
                tile.getTile().setFill(Color.TRANSPARENT);
            }
        }
    }

    private boolean checkClickable (Tile tile) {
        for (ISprite sprite : myLevel.getSprites()) {
            Bounds rectBounds =
                    new Bounds(BLOCK_SIZE * tile.getColPosition(), BLOCK_SIZE *
                                                                   tile.getRowPosition(),
                               (double) BLOCK_SIZE, (double) BLOCK_SIZE);
            if (sprite.getBounds().collide(rectBounds))
                return false;
        }
        return true;
    }

    public Pane getPane () {
        return myPane;
    }

    public Tile[][] getBlocks () {
        return myBlocks;
    }

    public int getNumBlockRow () {
        return myNumBlockRow;
    }

    public int getNumBlockCol () {
        return myNumBlockCol;
    }

    @Override
    public void render () {
        init();
    }
}
