package engine.aipathing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    //The below are initialized in the find path method;
    private IPathNode myGoal;
    private INodeGraph myGraph;
    

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
        setGraph(graph);
        setGoal(goalNode);
        
        
        if (startNode == null || goalNode == null) {
            return new ArrayList<>();
        }

        return aStar(startNode, goalNode, graph, getHeuristic());
    }
    
    private List<Coordinate> aStar (IPathNode startNode,
                                    IPathNode goalNode,
                                    INodeGraph graph,
                                    IHeuristic heuristic) {
        Map<IPathNode, IPathNode> parentMap = new HashMap<>();
        Map<IPathNode, Double> nodeCostThusFar = new HashMap<>();
        Map<IPathNode, Double> estimatedCost = new HashMap<>();
        List<IPathNode> openSet = new ArrayList<>();
        List<IPathNode> closedSet = new ArrayList<>();
        openSet.add(startNode);
        nodeCostThusFar.put(startNode, 0d);
        estimatedCost.put(startNode,
                          Coordinate.distance(startNode.getLocation(), goalNode.getLocation()));
        while (!openSet.isEmpty()) {
            IPathNode cur = openSet
                    .stream()
                    .reduce( (x, y) -> estimatedCost.get(x) > estimatedCost.get(y) ? y : x)
                    .orElse(null);
            openSet.remove(cur);
            closedSet.add(cur);
            if (cur.equals(goalNode)) {
                return goalPath(cur, startNode, parentMap);
            }
            for (IPathNode node : cur.getNeighbors()) {
                if (closedSet.contains(node)) {
                    continue;
                }
                double curEstCost =
                        nodeCostThusFar.get(cur) +
                                    Coordinate.distance(cur.getLocation(), node.getLocation());
                if (!openSet.contains(node)) {
                    openSet.add(node);
                }
                else if (curEstCost >= costForNode(node, nodeCostThusFar)) {
                    continue;
                }
                parentMap.put(node, cur);
                nodeCostThusFar.put(node, curEstCost);
                estimatedCost.put(node, curEstCost + heuristic.estimateCost(node, goalNode, graph));
            }   
        }
        return new ArrayList<>();
    }

    private List<Coordinate> goalPath (IPathNode goal,
                                          IPathNode start,
                                          Map<IPathNode, IPathNode> parentMap) {
        List<Coordinate> toReturn = new ArrayList<>();
        toReturn.add(goal.getLocation());
        IPathNode cur = goal;
        while (!cur.equals(start)) {
            cur = parentMap.get(cur);
            toReturn.add(cur.getLocation());
        }
        toReturn.add(start.getLocation());
        return toReturn;
    }
    
    private double costForNode (IPathNode node,
                                Map<IPathNode, Double> nodeCostThusFar) {
        if (nodeCostThusFar.containsKey(node)) {
            return nodeCostThusFar.get(node);
        }
        return Double.MAX_VALUE;
    }
    
    
    
    
    public IHeuristic getHeuristic () {
        return myHeuristic;
    }

    private IPathNode getGoal () {
        return myGoal;
    }

    private void setGoal (IPathNode myGoal) {
        this.myGoal = myGoal;
    }

    private INodeGraph getGraph () {
        return myGraph;
    }

    private void setGraph (INodeGraph myGraph) {
        this.myGraph = myGraph;
    }
    


}

