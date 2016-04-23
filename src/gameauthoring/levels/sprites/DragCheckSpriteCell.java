package gameauthoring.levels.sprites;

import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.levels.SceneController;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;


public class DragCheckSpriteCell extends DraggableSpriteCell implements Checkable {

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
        return checkBox;
    }

    private void checkBoxChange (Boolean oldValue, Boolean newValue) {
        if (!oldValue && newValue) {
            this.getController().addSpriteToLevel(this.getProfilable());
        }
        else if (oldValue && !newValue) {
            this.getController().removeSpriteFromLevel(this.getProfilable());
        }
    }

}
