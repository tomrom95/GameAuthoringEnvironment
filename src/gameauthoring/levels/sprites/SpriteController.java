package gameauthoring.levels.sprites;

import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.levels.PathCreator;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import util.Coordinate;

public class SpriteController {
    
    private ILevel myLevel;
    private PathCreator pathCreator;
    
    public SpriteController(ILevel level) {
        myLevel = level;
        pathCreator = new PathCreator();
    }
    
    public void deleteSprite(ISprite sprite){
        myLevel.remove(new SimpleObjectProperty<>(sprite));
    }
    
    public void moveSprite(ISprite sprite, double x, double y){
        sprite.getLocation().set(new Coordinate(x, y));
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
