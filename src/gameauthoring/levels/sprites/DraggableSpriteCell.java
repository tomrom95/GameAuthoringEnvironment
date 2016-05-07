// This entire file is part of my masterpiece.
// Tommy Romano
package gameauthoring.levels.sprites;

import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.levels.SceneController;
import gameauthoring.util.DraggableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;


/**
 * The DraggableSpriteCell extends the DraggableCell just for sprite definitions.
 * The draggable cell class simply defined the basic actions a dragger should take,
 * but this class specifically implements it for sprites. It continues the decorator
 * pattern by adding the specific actions for what happens when a sprite cell is dragged
 * onto the screen. Also, this class shows off the Model View Controller concept. In this
 * concept, the model (game) should be separated from the View (this) and from the Controller,
 * which I allow to be definable by the user. Basically, this sprite cell only implements
 * the dragging action but has no idea what happens when it is dropped. It takes in a
 * controller, so this can be customized, and then just passes it the x and y coordinate
 * of the sprite. Since the SceneController is a superclass, this actions can be changed
 * in any way without affecting the view. This actually came into play when we added
 * in the scale ratios to our view. The view shouldn't have to worry about that, and so
 * it simply passed the x and y coordinates and the controller handled it by scaling the
 * coordinates. The model of course, is completely separate from the controller and is
 * defined in the backend. This changed many times during our implementation but never
 * affected the controller. This shows complete decoupling from the controller and model. 
 *
 * @author Tommy
 *
 */
public class DraggableSpriteCell extends DraggableCell<SpriteDefinition> {

    private LevelRenderer myTarget;
    private SceneController myController;

    public DraggableSpriteCell (LevelRenderer target, SceneController controller) {
        setTarget(target.getPane());
        myTarget = target;
        myController = controller;
    }

    protected SceneController getController () {
        return myController;
    }

    /**
     * Sets what to do when it drags over the target
     */
    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    /**
     * On drag drop, the sprite is added to the screen
     */
    @Override
    public void setOnDragDropped (DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasString()) {
            addSprite(e.getX(), e.getY());
        }
    }

    /**
     * Calls the controller to add the sprite to the screen
     * @param x - sprite x coordinate
     * @param y - sprite y coordinate
     */
    protected void addSprite (double x, double y) {
        myController.addSprite(x, y, getProfilable());
        myTarget.render();
    }
    
}
