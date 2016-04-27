package gameauthoring.levels;

import engine.ILevel;
import engine.definitions.concrete.SpriteDefinition;
import util.Coordinate;
import util.ScaleRatio;

public class GameSceneController extends SceneController {

    private ScaleRatio myRatio;
    
    public GameSceneController (ILevel level, ScaleRatio ratio) {
        super(level);
        myRatio = ratio;
    }
    
    @Override
    public void addSprite (double x, double y, SpriteDefinition spriteDefinition) {
        getLevel().add(spriteDefinition.create(), new Coordinate(x/myRatio.getScale(), y/myRatio.getScale()));
    }

}
