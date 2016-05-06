package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import util.ArrayPosition;
import util.Coordinate;


public class PathNode implements IPathNode {

    private List<IPathNode> myNeighbors;
    private List<NodePathFlag> myFlags;
    private Coordinate myLocation;

    public PathNode (Coordinate location) {
        myNeighbors = new ArrayList<>();
        myFlags = new ArrayList<>();
        myLocation = location;
    }

    public PathNode (ArrayPosition pos) {
        this(new Coordinate(pos.getX(), pos.getY()));
    }

    @Override
    public List<IPathNode> getNeighbors () {
        return myNeighbors;
    }

    @Override
    public List<NodePathFlag> getFlags () {
        return myFlags;
    }

    @Override
    public Coordinate getLocation () {
        return new Coordinate(myLocation.getX(), myLocation.getY());
    }

    @Override
    public int hashCode () {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((myLocation == null) ? 0 : myLocation.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PathNode other = (PathNode) obj;
        if (myLocation == null) {
            if (other.myLocation != null) {
                return false;
            }
        }
        else if (!myLocation.equals(other.myLocation)) {
            return false;
        }
        return true;
    }

}
