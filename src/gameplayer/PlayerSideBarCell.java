package gameplayer;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.costs.ICost;
import engine.rendering.LevelRenderer;
import gameauthoring.levels.SceneController;
import gameauthoring.levels.sprites.DraggableSpriteCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Subclass for showing the sprite definition cells in the side of the player.
 * Overrides DraggableSpriteCell and adds extra functionality like displaying
 * costs and not allowing dragging when you can't place the sprite;
 * @author Tommy
 *
 */
public class PlayerSideBarCell extends DraggableSpriteCell {
    private static final int SPACING = 5;
    private static final String COST_STRING = "Cost: ";
    private static final Background PLACEABLE_BACKGROUND =
            new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
                                              Insets.EMPTY)); // default good background
    private static final Background UNPLACEABLE_BACKGROUND =
            new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY,
                                              Insets.EMPTY)); // default bad background

    public PlayerSideBarCell (LevelRenderer target, SceneController controller) {
        super(target, controller);
    }

    @Override
    protected Node createSpriteCell (SpriteDefinition profile) {
        Node cell = super.createSpriteCell(profile);
        return addCost(cell);
    }

    /**
     * Adds the cost node to the bottom of the already created cell
     * @param cell
     * @return
     */
    private Node addCost (Node cell) {
        VBox box = new VBox(SPACING);
        box.getChildren().add(cell);
        box.getChildren().add(costNode());
        setBackgroundBinding();
        return box;
    }

    /**
     * Binds the list cell to change background when you can place the cell
     * @param box
     */
    private void setBackgroundBinding () {
        BooleanProperty canPlace = this.getProfilable().getCost().canPlace();
        this.backgroundProperty().bind(Bindings.when(canPlace)
                .then(PLACEABLE_BACKGROUND)
                .otherwise(UNPLACEABLE_BACKGROUND));

    }

    /**
     * Creates the actual hbox that shows the cost for the sprite
     * @return
     */
    private Node costNode () {
        HBox box = new HBox(SPACING);
        box.getChildren().add(new Text(COST_STRING));
        box.getChildren().add(costText(this.getProfilable().getCost()));
        box.getChildren().add(attributeText(this.getProfilable().getCost()));
        return box;
    }

    /**
     * Displays the attribute name
     * @param cost
     * @return
     */
    private Node attributeText (ICost cost) {
        String attribute = cost.getAttributeType().getType();
        return new Text(attribute);
    }

    /**
     * Displays the cost for this sprite
     * @param cost
     * @return
     */
    private Node costText (ICost cost) {
        return new Text(String.valueOf(cost.getCostAmount()));
    }

    /**
     * Overrides the draggable's set action method to lisen for when
     * you can drag a sprite or not
     * @param source
     */
    @Override
    public void setActions (Node source) {
        BooleanProperty canPlace = this.getProfilable().getCost().canPlace();
        setDraggedOrNot(source, canPlace.get());
        canPlace.addListener( (observable, oldValue, newValue) -> setDraggedOrNot(source,
                                                                                  newValue));
    }

    /**
     * If you can place the sprite, then you'll be able to drag it
     * on the screen
     * @param source
     * @param newValue
     */
    private void setDraggedOrNot (Node source, boolean newValue) {
        source.setOnDragDetected(e -> {
            if (newValue) {
                setOnDragDetected(e, source);
            }
        });
    }
    
    /**
     * Overrides DraggableSpriteCell's add sprite method
     * to actually buy the sprite
     * @param x
     * @param y
     */
    @Override
    protected void addSprite (double x, double y) {
        super.addSprite(x, y);
        getProfilable().getCost().buySprite();
    }

}
