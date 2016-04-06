package gameauthoring.levels;

import engine.ILevel;
import engine.ISprite;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import util.Coordinate;

public class OnScreenSpawner extends OnScreenSprite{
    
    PathCreator pathCreator;

    public OnScreenSpawner (LevelRenderer renderer, ILevel level, ObjectProperty<ISprite> sprite) {
        super(renderer, level, sprite);
        pathCreator = new PathCreator();
    }
    
    @Override
    protected ContextMenu spriteActionsMenu (Pane container) {
        ContextMenu menu = super.spriteActionsMenu(container);
        MenuItem create = new MenuItem("Create Path");
        create.setOnAction(event -> {
            Coordinate point = getSprite().get().getLocation().get();
            createNewPath(point, container);
        });
        menu.getItems().add(create);
        return menu;
    }
    
    public void createNewPath(Coordinate startPoint, Pane container){
        pathCreator.newPath(startPoint, container);
        container.setOnMouseClicked(e -> pathCreator.addToPath(e, container));
        container.setOnKeyPressed(e -> handleKeyPress(e, container));
    }
    
    private void handleKeyPress (KeyEvent e, Pane container) {
        if (e.getCode() == KeyCode.ESCAPE) {
            pathCreator.endPath(container);
        }
    }

}
