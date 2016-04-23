package gameplayer;

import engine.definitions.SpriteDefinition;
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


public class PlayerSideBarCell extends DraggableSpriteCell {
    private static final int SPACING = 5;
    private static final String COST_STRING = "Cost: ";
    private static final Background PLACEABLE_BACKGROUND =
            new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
                                              Insets.EMPTY));
    private static final Background UNPLACEABLE_BACKGROUND =
            new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY,
                                              Insets.EMPTY));

    public PlayerSideBarCell (LevelRenderer target, SceneController controller) {
        super(target, controller);
    }

    @Override
    protected Node createSpriteCell (SpriteDefinition profile) {
        Node cell = super.createSpriteCell(profile);
        return addCost(cell);
    }

    private Node addCost (Node cell) {
        VBox box = new VBox(SPACING);
        box.getChildren().add(cell);
        box.getChildren().add(costNode());
        setBackgroundBinding(box);
        return box;
    }

    private void setBackgroundBinding (VBox box) {
        BooleanProperty canPlace = this.getProfilable().getCost().canPlace();
        this.backgroundProperty().bind(Bindings.when(canPlace)
                .then(PLACEABLE_BACKGROUND)
                .otherwise(UNPLACEABLE_BACKGROUND));

    }

    private Node costNode () {
        HBox box = new HBox(SPACING);
        box.getChildren().add(new Text(COST_STRING));
        box.getChildren().add(costText(this.getProfilable().getCost()));
        box.getChildren().add(attributeText(this.getProfilable().getCost()));
        return box;
    }

    private Node attributeText (ICost cost) {
        String attribute = cost.getAttributeType().getType();
        return new Text(attribute);
    }

    private Node costText (ICost cost) {
        return new Text(String.valueOf(cost.getCostAmount()));
    }

    @Override
    public void setActions (Node source) {
        BooleanProperty canPlace = this.getProfilable().getCost().canPlace();
        setDraggedOrNot(source, canPlace.get());
        canPlace.addListener( (observable, oldValue, newValue) -> setDraggedOrNot(source,
                                                                                  newValue));
    }

    private void setDraggedOrNot (Node source, boolean newValue) {
        source.setOnDragDetected(e -> {
            if (newValue) {
                setOnDragDetected(e, source);
            }
            else {
                return;
            }
        });
    }

}
