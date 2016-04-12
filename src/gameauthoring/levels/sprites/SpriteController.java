package gameauthoring.levels.sprites;

import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.levels.PathCreator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import util.Coordinate;

/**
 * Controller for onscreen sprites. Handles actions like
 * deletion, moving, and creating paths
 * @author Tommy
 *
 */
public class SpriteController {
    
    private ILevel myLevel;
    private PathCreator pathCreator;
    
    public SpriteController(ILevel level) {
        myLevel = level;
        pathCreator = new PathCreator();
    }
    
    /**
     * Removes sprite from the level
     * @param sprite
     */
    public void deleteSprite(ISprite sprite){
        myLevel.remove(sprite);
    }
    
    /**
     * Moves sprite to new (x,y) location
     * @param sprite
     * @param x
     * @param y
     */
    public void moveSprite(ISprite sprite, double x, double y){
        sprite.setLocation(new Coordinate(x, y));
    }
    
    /**
     * Starts making a new path beginning with a start point
     * @param sprite
     * @param startPoint
     * @param container
     */
    public void createNewPath(ISprite sprite, Coordinate startPoint, Pane container){
        pathCreator.newPath(startPoint, container);
        container.setOnMouseClicked(e -> pathCreator.addToPath(e, container));
        container.setOnKeyPressed(e -> handleKeyPress(e, container, sprite));
    }
    
    /**
     * Handles ending of path creation using the escape key
     * @param e
     * @param container
     * @param sprite
     */
    private void handleKeyPress (KeyEvent e, Pane container, ISprite sprite) {
        if (e.getCode() == KeyCode.ESCAPE) {
            pathCreator.endPath(container);
            sprite.getMovementStrategy().setPath(pathCreator.getPathPoints());
        }
    }

}
