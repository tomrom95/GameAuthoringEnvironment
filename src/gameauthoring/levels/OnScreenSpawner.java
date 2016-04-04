package gameauthoring.levels;

import engine.ISprite;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import util.Coordinate;

public class OnScreenSpawner extends OnScreenSprite{

    public OnScreenSpawner (ISprite sprite, Pane target, Image image, SceneController controller) {
        super(sprite, target, image, controller);
    }
    
    @Override
    protected ContextMenu spriteActionsMenu (Pane container, Node node) {
        ContextMenu menu = super.spriteActionsMenu(container, node);
        MenuItem create = new MenuItem("Create Path");
        create.setOnAction(event -> {
            Coordinate point = new Coordinate(node.getTranslateX(), node.getTranslateY());
            this.getController().createNewPath(point, container);
        });
        menu.getItems().add(create);
        return menu;
    }

}
