package engine.definitions;

import java.util.ArrayList;
import java.util.List;
import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.PathMover;
import util.Coordinate;


public class PathMoverDefinition extends MovementDefinition {

    private List<Coordinate> myList;

    public PathMoverDefinition () {
        myList = new ArrayList<>();
    }

    @Override
    public IMovementModule create (IPositionable parent) {
        return new PathMover(getSpeed(), new ArrayList(myList), parent);
    }
}
