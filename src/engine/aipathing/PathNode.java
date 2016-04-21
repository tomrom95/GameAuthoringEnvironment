package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import util.Coordinate;


public class PathNode implements IPathNode {

    private List<IPathNode> myNeighbors;
    private List<PathFlag> myFlags;
    private Coordinate myLocation;

    public PathNode (Coordinate location) {
        myNeighbors = new ArrayList<>();
        myFlags = new ArrayList<>();
        myLocation = location;
    }

    @Override
    public List<IPathNode> getNeighbors () {
        return myNeighbors;
    }

    @Override
    public List<PathFlag> getFlags () {
        return myFlags;
    }

    @Override
    public Coordinate getLocation () {
        return new Coordinate(myLocation.getX(), myLocation.getY());
    }

}
