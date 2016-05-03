package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.aipathing.PathNodeGeometry;


public class BoundEdge implements IBoundEdge {

    private List<Bounds> myBounds;
    private Map<Bounds, List<Coordinate>> myBoundEdgeMap;

    public BoundEdge () {
        myBounds = new ArrayList<>();
        myBoundEdgeMap = new HashMap<>();
    }

    @Override
    public boolean addBoundToEdge (Bounds bound) {
        if (getBounds().isEmpty() || proposedIsContiguous(bound)) {
            return getBounds().add(bound);
        }
        return false;
    }

    @Override
    public List<Coordinate> getEdge () {
        List<Coordinate> toReturn = new ArrayList<>();
        for (Bounds boundOuter : getBounds()) {
            for (Coordinate coord : edgeForBound(boundOuter)) {
                for (Bounds boundInner : getBounds()) {
                    if (!boundOuter.equals(boundInner) && !boundInner.edgeBoundsContains(coord)) {
                        toReturn.add(coord);
                    }
                }
            }
        }
        return toReturn;
    }

    @Override
    public boolean complexContains (Coordinate toCheck) {
        for (Bounds bnd : getBounds()) {
            if (bnd.contains(toCheck)) {
                return true;
            }
        }
        return false;
    }

    private List<Coordinate> edgeForBound (Bounds bound) {
        if (getBoundEdgeMap().containsKey(bound)) {
            return getBoundEdgeMap().get(bound);
        }
        List<Coordinate> toReturn = new ArrayList<>();
        Coordinate topLeft = new Coordinate(bound.getLeft(), bound.getTop());
        Coordinate topRight = new Coordinate(bound.getRight(), bound.getTop());
        Coordinate botLeft = new Coordinate(bound.getLeft(), bound.getBottom());
        Coordinate botRight = new Coordinate(bound.getRight(), bound.getBottom());
        toReturn.addAll(PathNodeGeometry.roundedLineBetween(topLeft, topRight));
        toReturn.addAll(PathNodeGeometry.roundedLineBetween(topLeft, botLeft));
        toReturn.addAll(PathNodeGeometry.roundedLineBetween(botRight, topRight));
        toReturn.addAll(PathNodeGeometry.roundedLineBetween(botRight, botLeft));
        getBoundEdgeMap().put(bound, toReturn);
        return toReturn;
    }

    private boolean proposedIsContiguous (Bounds bound) {
        if (getBounds().isEmpty()) {
            return true;
        }
        List<Coordinate> edge = edgeForBound(bound);
        for (Coordinate coord : edge) {
            for (Bounds bnd : getBounds()) {
                if (bnd.edgeBoundsContains(coord)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Bounds> getBounds () {
        return myBounds;
    }

    private Map<Bounds, List<Coordinate>> getBoundEdgeMap () {
        return myBoundEdgeMap;
    }

}
