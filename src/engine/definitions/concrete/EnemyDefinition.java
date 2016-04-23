package engine.definitions.concrete;

import java.util.List;
import engine.modules.PathMover;
import engine.sprite.ISprite;
import util.Coordinate;


/**
 * This class represents the definition template for an enemy
 *
 */
public class EnemyDefinition extends SpriteDefinition {

    private List<Coordinate> myPath;
    private double mySpeed;

    /**
     * TODO should this be defined in the overall sprite definition? or does it need to be defined
     * here at all
     */
    public void setPath (List<Coordinate> path) {
        myPath = path;
    }

    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    @Override
    public ISprite create () {
        ISprite sprite = super.create();
        sprite.initialize(new PathMover(mySpeed, myPath, sprite), createGraphicModule(),
                          createUpgrade(sprite), 
                          createModules(sprite),
                          createAttributes(),
                          createCoordinate());
        return sprite;

    }
}
