package engine.rendering;

import javafx.scene.input.MouseButton;
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

    public GridRenderer (GridPane pane) {
        myPane = pane;
    }

    private void drawGridLines () {
        //TODO: Hard-coded numbers
        for (int i = 0; i < 29; i++) {
            for (int j = 0; j < 16; j++) {
                Rectangle rect = new Rectangle(25, 25);
                rect.setFill(Color.TRANSPARENT);
                rect.setOnMouseClicked(e -> handleMouseClick(rect, e));
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

    @Override
    public void render () {
        drawGridLines();
    }
}
