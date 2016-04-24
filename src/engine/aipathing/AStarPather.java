package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import util.Coordinate;
import util.IBitMap;

/**
 * Implementation of A* search based off of the pseudo-code provided
 * at <a href="web.mit.edu/eranki/www/tutorials/search">Pathfinding using A* (A-Star)</a>
 * 
 * @author jonathanim
 *
 */
public class AStarPather implements INodeGraphPather {
    private IHeuristic myHeuristic;

    public AStarPather () {
        myHeuristic = new StraightLineHeuristic();
    }
    
    @Override
    public List<Coordinate> findPathFor (IBitMap obstructionMap,
                                         Coordinate start,
                                         Coordinate goal) {
        INodeGraphFactory graphFactory = new GameGraphFactory(obstructionMap);
        INodeGraph graph = graphFactory.getConstructedGraph();
        IPathNode startNode = graph.getClosestNode(start);
        IPathNode goalNode = graph.getClosestNode(goal);
        if (startNode == null || goalNode == null) {
            return new ArrayList<>();
        }

        return aStar(startNode, goalNode, graph, getHeuristic());
    }
    
    private List<Coordinate> aStar (IPathNode startNode,
                                    IPathNode goalNode,
                                    INodeGraph graph,
                                    IHeuristic heuristic) {

        return null;
    }
    
    public IHeuristic getHeuristic () {
        return myHeuristic;
    }

}

