package engine.definitions.moduledef;

import java.util.ArrayList;
import java.util.List;
import engine.Positionable;
import engine.modules.IMovementModule;
import engine.modules.PathMover;
import util.Coordinate;

/**
 * This class represents the definition for a path-following movement module
 *
 */
public class PathMoverDefinition extends MovementDefinition {

    private List<Coordinate> myList;

    public PathMoverDefinition () {
        myList = new ArrayList<>();
    }

    @Override
    public IMovementModule create (Positionable parent) {
        return new PathMover(getSpeed(), new ArrayList<Coordinate>(myList), parent);
    }
}
