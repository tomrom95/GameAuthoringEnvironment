package gameauthoring.levels;

import engine.ILevel;
import engine.definitions.concrete.SpriteDefinition;
import util.Coordinate;
import util.ScaleRatio;

public class GameSceneController extends SceneController {
    
    public GameSceneController (ILevel level, ScaleRatio ratio) {
        super(level, ratio);
    }
    
    @Override
    public void addSprite (double x, double y, SpriteDefinition spriteDefinition) {
        getLevel().add(spriteDefinition.create(), new Coordinate(getRatio().invert(x), getRatio().invert(y)));
    }

}
