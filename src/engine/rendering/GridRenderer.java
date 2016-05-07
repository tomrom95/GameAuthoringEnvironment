// This entire file is part of my masterpiece.
// Jin An
/**
 * My masterpiece is comprised of GridRenderer, Tile, Shape, ShapeFactory, and Rectangle classes. I
 * chose these classes because they show off strong understanding of many materials that we learned
 * throughout the semester including interface, inheritance hierarchy of Shape, composition
 * relationship that Tile has to Shape, reflection to instantiate class object by its name, and
 * Factory Design Pattern. Moreover, I have extracted methods, renamed variables and methods, and
 * deleted duplicated codes to show off the basic fundamentals of what a good design is. The more
 * details will be covered in each relevant classes.
 * 
 * Its functionalities involve calculating the tile array size when the background image changes and
 * dynamically redrawing the appropriately scaled grid lines when the size of the window changes.
 * Furthermore, when grid lines are initialized, it reads data from BitMap (a 2D boolean array which
 * has information on whether sprites can be placed on each specific location) that the level object
 * contains and re-populate the tile by setting its fill to red color if the location is not
 * placeable. When the game author is on the placeable area edit view, he/she can click each tile to
 * set it as placeable or not. Then, the BitMap, which Level object has, will be updated
 * accordingly.
 * 
 * Several changes I made involve extracting a checkBitMapToPopulateTile method, deleting
 * updateTileNumbers, redraw, init, getpane, checkClickable, and getBlocks methods, refactoring
 * duplicated codes, renaming BLOCK_SIZE method, and using ResourceBundle to avoid hard-coding
 * constant. The reason why I extracted the method is that the previous if statement in
 * initializeGridLines method does not clearly explain what is happening. After extracting it and
 * naming it as "checkBitMapToPopulateTile", the readers who are not familiar with the code can
 * realize that as the program initializes the grid lines, it checks the bit map and repopulate the
 * tiles by coloring it red or transparent. Then, I deleted checkClickable method with a
 * implementation concern. What this method did was to check whether the grid tile that author
 * clicks corresponds with the position of current sprites on screen. However, I now believe that it
 * still makes sense for authors to place a sprite on screen and set it as unplaceable for the game
 * player. I deleted public getPane method because the getter method for pane is not needed as the
 * AuthoringRenderer class has the gridPane and passed it to GridRenderer. This means that myPane
 * within GridPane cannot be accessed from outside as it is a private variable and does not have a
 * getter method. This is a great advantage of using composition over inheritance. I deleted other
 * methods such as updateTileNumbers because they are one line methods that call the private methods
 * within the same class. Thus, it only takes more space and does not use encapsulation effectively.
 * I also spotted a duplicated code in handleMouseClick method where both if and else statements
 * change color of the tile and set the bitmap accordingly. By having changeColor method in Tile
 * class and using ? operator, I managed to delete the duplicated codes. Lastly, in order to avoid
 * hard-coding constant (as our class reading "The Magic of Data-Driven Design" insists), I created
 * a properties file in "defaults/blocksize_gridrenderer" so that when we want to make changes to
 * the game, we can easily change the properties file. All these changes improve the design of the
 * code as it is much easier for readers to understand, follows open-closed principle, and avoids
 * duplicated codes.
 * 
 * Note that a minor change has been made in SceneCreator class to fully support the placeable area
 * implementation in all three of our demo games. Originally, the feature was working well with
 * tower defense and top-down shooting game, but not farmvile. After debugging the problem, I
 * realized that the bug occurred when we convert from double coordinates to integer index of grid.
 * After fixing the round-up issue, the feature works well with all three demo games.
 */
package engine.rendering;

import java.util.ResourceBundle;
import engine.ILevel;
import javafx.scene.layout.GridPane;
import util.ScaleRatio;
import util.Tile;


/**
 * GridRenderer class serves to render a grid that fits to the size of the current background image
 * of current level tab in authoring environment. It is a crucial part of implementing checking
 * tower-placeable area for Tower Defense game (and other games that require this feature).
 * 
 * @author Jin An
 *
 */
public class GridRenderer implements IRenderer {

    private GridPane myPane;
    private ILevel myLevel;
    private ScaleRatio myScale;
    private int myDefaultBlockSize;
    private int myNumBlockRow;
    private String myTileShape;
    private int myNumBlockCol;
    private double myCurrentBlockSize;
    private static final boolean PLACEABLE = false;
    private static final boolean NOT_PLACEABLE = true;
    private ResourceBundle myResources = ResourceBundle
            .getBundle("defaults/blocksize_gridrenderer");

    public GridRenderer (ILevel level, GridPane pane, ScaleRatio scale, String shape) {
        myDefaultBlockSize = Integer.parseInt(myResources.getString("BlockSize"));
        myPane = pane;
        myLevel = level;
        myScale = scale;
        myTileShape = shape;
    }

    @Override
    public void render () {
        calculateTileArraySize();
        initializeGridLines();
    }

    public void calculateTileArraySize () {
        myNumBlockRow = (int) (myLevel.getBounds().getHeight() / myDefaultBlockSize);
        myNumBlockCol = (int) (myLevel.getBounds().getWidth() / myDefaultBlockSize);
    }

    public void initializeGridLines () {
        myPane.getChildren().clear();
        myCurrentBlockSize = myScale.scale(myDefaultBlockSize);
        for (int row = 0; row < myNumBlockRow; row++) {
            for (int column = 0; column < myNumBlockCol; column++) {
                Tile tile = new Tile(myCurrentBlockSize, row, column, myTileShape);
                checkBitMapToPopulateTile(tile, row, column);
                tile.getTileObject().setOnMouseClicked(e -> handleMouseClick(tile));
                myPane.add(tile.getTileObject(), column, row);
            }
        }
    }

    private void handleMouseClick (Tile tile) {
        tile.getShapeObject().changeColor();
        myLevel.getPlaceableTileManager().setBitMap(tile.getRowPosition(),
                                                    tile.getColPosition(),
                                                    tile.getShapeObject().isRed() ? NOT_PLACEABLE
                                                                                 : PLACEABLE);
    }

    private void checkBitMapToPopulateTile (Tile tile, int row, int column) {
        if (myLevel.getPlaceableTileManager().checkBitMap(row, column))
            tile.getShapeObject().setRed();
        else
            tile.getShapeObject().setTransparent();
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
}
