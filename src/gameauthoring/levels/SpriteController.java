package gameauthoring.levels;

import engine.ILevel;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import util.Coordinate;

public class SpriteController {
    
    private ILevel myLevel;
    
    public SpriteController(ILevel level) {
        myLevel = level;
    }
    
    public void deleteSprite(ObjectProperty<ISprite> sprite){
        myLevel.remove(sprite);
    }
    
    public void moveSprite(ObjectProperty<ISprite> sprite, double x, double y){
        sprite.get().getLocation().set(new Coordinate(x, y));
    }

}
