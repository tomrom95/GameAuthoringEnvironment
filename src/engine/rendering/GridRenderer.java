package engine.rendering;

import engine.ILevel;
import engine.sprite.ISprite;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.Bounds;
import util.ScaleRatio;
import util.Tile;


/**
 * 
 * Renderer for grid in the authoring environment.
 * Authors can set unplaceable terrain by clicking the tiles
 * Takes in myScale (Scale Ratio wrapper object) and uses it to dynamically calculate the size of
 * the block and render the updated grid
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
    private double myCurrentBlockSize;
    public final int BLOCK_SIZE = 50;
    private ScaleRatio myScale;

    public GridRenderer (ILevel level, GridPane pane, ScaleRatio scale) {
        myPane = pane;
        myLevel = level;
        myScale = scale;
        init();
    }

    private void init () {
        calculateTileArraySize();
        initializeGridLines();
    }

    private void calculateTileArraySize () {
        myNumBlockRow = (int) (myLevel.getBounds().getHeight() / BLOCK_SIZE);
        myNumBlockCol = (int) (myLevel.getBounds().getWidth() / BLOCK_SIZE);
        myBlocks = new Tile[myNumBlockRow][myNumBlockCol];
    }

    private void initializeGridLines () {
        myPane.getChildren().clear();
        myCurrentBlockSize = myScale.scale(BLOCK_SIZE);
        for (int i = 0; i < myNumBlockRow; i++) {
            for (int j = 0; j < myNumBlockCol; j++) {
                Tile tile = new Tile(myCurrentBlockSize, i, j);
                if (myLevel.getPlaceableTileManager().getPlaceableMap().getBitMap()[i][j]) {
                    tile.setRed();
                }
                tile.getTile().setOnMouseClicked(e -> handleMouseClick(tile));
                myBlocks[i][j] = tile;
                myPane.add(tile.getTile(), j, i);
            }

        }

    }

    private void handleMouseClick (Tile tile) {
        if (checkClickable(tile)) {
            if (tile.getTile().getFill() == Color.TRANSPARENT) {
                myLevel.getPlaceableTileManager().getPlaceableMap().getBitMap()[tile
                        .getRowPosition()][tile
                        .getColPosition()] = true;
                tile.getTile().setFill(Color.RED);
            }
            else if (tile.getTile().getFill() == Color.RED) {
                myLevel.getPlaceableTileManager().getPlaceableMap().getBitMap()[tile
                        .getRowPosition()][tile
                        .getColPosition()] = false;
                tile.getTile().setFill(Color.TRANSPARENT);
            }
        }
    }

    private boolean checkClickable (Tile tile) {
        for (ISprite sprite : myLevel.getSprites()) {
            Bounds rectBounds =
                    new Bounds(myCurrentBlockSize * tile.getColPosition(),
                               myCurrentBlockSize * tile.getRowPosition(),
                               myCurrentBlockSize, myCurrentBlockSize);
            if (sprite.getBounds().collide(rectBounds)) {
                return false;
            }
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

    public double getCurrentBlockSize () {
        return myCurrentBlockSize;
    }

    @Override
    public void render () {
        init();
    }

    public void updateTileNumbers () {
        calculateTileArraySize();
    }

    public void redraw () {
        initializeGridLines();
    }
}
