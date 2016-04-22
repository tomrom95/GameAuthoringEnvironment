package engine.rendering;

import javafx.scene.input.MouseEvent;
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
    private Rectangle[][] myBlocks;
    public final int NUM_BLOCK_ROW = 29;
    public final int NUM_BLOCK_COL = 16;
    public final int BLOCK_SIZE = 25;

    public GridRenderer (GridPane pane) {
        myPane = pane;
        myBlocks = new Rectangle[NUM_BLOCK_ROW][NUM_BLOCK_COL];
    }

    private void drawGridLines () {
        // TODO: Hard-coded numbers
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                Rectangle rect = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
                rect.setFill(Color.TRANSPARENT);
                rect.setOnMouseClicked(e -> handleMouseClick(rect, e));
                myBlocks[i][j] = rect;
                myPane.add(rect, i, j);
            }
        }
    }

    private void handleMouseClick (Rectangle rect, MouseEvent e) {
        if (rect.getFill() == Color.TRANSPARENT)
            rect.setFill(Color.RED);
        else
            rect.setFill(Color.TRANSPARENT);
    }

    public Pane getPane () {
        return myPane;
    }

    public Rectangle[][] getBlocks () {
        return myBlocks;
    }

    @Override
    public void render () {
        drawGridLines();
    }
}
