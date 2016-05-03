package gameauthoring.levels.sprites;

import java.util.ResourceBundle;
import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.levels.SceneController;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import splash.LocaleManager;


public class DragCheckSpriteCell extends DraggableSpriteCell implements Checkable {

    private ResourceBundle myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
            .getInstance().getCurrentLocaleProperty().get());

    public DragCheckSpriteCell (LevelRenderer target, SceneController controller) {
        super(target, controller);
    }

    @Override
    protected Node createSpriteCell (SpriteDefinition profile) {
        Node cell = super.createSpriteCell(profile);
        return createCheckCell(cell);
    }

    private Node createCheckCell (Node cell) {
        HBox box = new HBox(5);
        box.getChildren().add(cell);
        box.getChildren().add(createCheckBox());
        return box;
    }

    private Node createCheckBox () {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(getController().isSpriteInLevel(getProfilable()));
        checkBox.selectedProperty()
                .addListener( (observable, oldValue, newValue) -> checkBoxChange(oldValue,
                                                                                 newValue));
        addToolTip(checkBox);
        return checkBox;
    }

    private void addToolTip (CheckBox checkBox) {
        Tooltip tooltip = new Tooltip(myLabel.getString("PlaceableLevel"));
        checkBox.setOnMouseEntered(e -> {
            Point2D point = getCheckBoxPoint(checkBox);
            tooltip.show(checkBox, point.getX(), point.getY());
        });
        checkBox.setOnMouseExited(e -> tooltip.hide());
    }

    private Point2D getCheckBoxPoint (CheckBox checkBox) {
        return checkBox.localToScreen(checkBox.getLayoutBounds().getMaxX(),
                                      checkBox.getLayoutBounds().getMaxY());
    }

    private void checkBoxChange (Boolean oldValue, Boolean newValue) {
        if (!oldValue && newValue) {
            getController().addSpriteToLevel(getProfilable());
        }
        else if (oldValue && !newValue) {
            getController().removeSpriteFromLevel(getProfilable());
        }
    }

}
