package engine.sprite.concrete;

import java.util.List;
import engine.modules.PathMover;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import util.Coordinate;


public class Enemy extends Sprite {

    public Enemy (SpriteType type, double speed, List<Coordinate> path) {
        super(type);
        init(speed, path);
    }

    private void init (double speed, List<Coordinate> path) {
        super.initialize(new PathMover(speed, path, this), getDrawer(), getModules(),
                         getAttributes(),
                         getLocation());

    }

}
