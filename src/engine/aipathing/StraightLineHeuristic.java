package engine.aipathing;

import util.Coordinate;

public class StraightLineHeuristic implements IHeuristic {

    @Override
    public double estimateCost (IPathNode start, IPathNode goal, INodeGraph graph) {
        return Coordinate.distance(start.getLocation(), goal.getLocation());
    }

}
