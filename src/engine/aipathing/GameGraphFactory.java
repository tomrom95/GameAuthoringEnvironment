package engine.aipathing;

import util.BitMap;
import util.Coordinate;
import java.util.List;


public class GameGraphFactory implements INodeGraphFactory {
    private static final double ONE = 1d;
    private static final int NODE_GAP = 10;
    private BitMap myObstructionMap;
    private double myMinGap;

    GameGraphFactory (BitMap obstructionMap) {
        myObstructionMap = obstructionMap;
        myMinGap = Double.MAX_VALUE;
    }

    @Override
    public INodeGraph getConstructedGraph () {
        List<List<Coordinate>> edges = findAllEdges(getObstructionMap());
        return fillNodeGraph(getObstructionMap(), NODE_GAP, edges);

    }

    private INodeGraph fillNodeGraph (BitMap obstructionMap,
                                      int gap,
                                      List<List<Coordinate>> edges) {
        INodeGraph toReturn = new NodeGraph();
        int numHorizontalNodes = obstructionMap.getWidth() / gap;
        int numHeightNodes = obstructionMap.getHeight() / gap;
        IPathNode[][] placedNodes = new PathNode[numHorizontalNodes][numHeightNodes];
        // place the standard grid
        for (int i = 0; i < obstructionMap.getWidth(); i += gap) {
            for (int j = 0; j < obstructionMap.getHeight(); j += gap) {
                if (obstructionMap.valueOf(i, j)) {
                    IPathNode toAdd = new PathNode(new Coordinate(i, j));
                    toReturn.addNode(toAdd);
                    placedNodes[i][j] = toAdd;
                }
            }
        }
        connectUnobstructedNodes(placedNodes);
        List<IPathNode> traversableGapNodes = addEdgeNodes(toReturn, edges, obstructionMap);
        connectFloatingNodes(traversableGapNodes, toReturn, gap, placedNodes, obstructionMap);
        return toReturn;

    }

    /**
     * 
     * @param nodes to add
     * @param graph
     * @param graphInterval the sample distance of the placed node
     * @param placedNodes cache of placed nodes to avoid having to check every node in graph
     */
    private void connectFloatingNodes (List<IPathNode> nodes,
                                       INodeGraph graph,
                                       int graphInterval,
                                       IPathNode[][] placedNodes,
                                       BitMap obstructionMap) {
        // connect nodes by doing distance search
        // distance NODE_GAP * sqrt(2) radius circle
        // TODO
    }

    private List<IPathNode> addEdgeNodes (INodeGraph graph,
                                          List<List<Coordinate>> edges,
                                          BitMap obstructionMap) {

        // fill map with nodes at gapped interval
        // place nodes half-way between different edge points if distance is <= NODE_GAP
        // TODO
        return null;
    }

    private void connectUnobstructedNodes (IPathNode[][] nodes) {
        //method to use is below
        //connectIfNotObstructed
        // the above method will check the actual bitmap between the two points
        // to see if the nodes should be connected
        int width = nodes.length;
        int height = nodes[0].length;
        
        
        // will connect cardinal and diagonal, for diagonal will simply check
        // one on each side of the line as well, so that corner blocks will stop
        // will check straight lines between nodes to see if there
        // are any obstructed pixels
        // TODO
    }

    /**
     * Will connect partially connected nodes
     * 
     * @param first Node to check
     * @param second Node to check
     * @return True if at least one node holds reference of the other, false if not
     */
    private boolean ifConnected (IPathNode first, IPathNode second) {
        boolean connected = false;
        if (first.getNeighbors().contains(second) || second.getNeighbors().contains(first)) {
            connected = true;
            makeNeighbors(first, second);
        }
        return connected;
    }

    private void makeNeighbors (IPathNode first, IPathNode second) {
        if (!first.getNeighbors().contains(second)) {
            first.getNeighbors().add(second);
        }
        if (!second.getNeighbors().contains(first)) {
            second.getNeighbors().add(first);
        }
    }

    /**
     * Will perform obstruction checks by checking a series of coordinates within the map
     * to see if they are all false
     * 
     * @param first Node in the pair to connect
     * @param second Node in the pair to connect
     * @param obstructionMap
     */
    private void connectIfNotObstructed (IPathNode first, IPathNode second, BitMap obstructionMap) {
        List<Coordinate> pixelLine =
                PathNodeGeometry.lineRounder(PathNodeGeometry.lineBetween(first, second));
        if (!lineObstructed(pixelLine, obstructionMap)) {
            makeNeighbors(first, second);
        }
    }

    private boolean lineObstructed (List<Coordinate> line, BitMap obstructionMap) {
        if (line.size() < 1) {
            return true;
        }
        boolean isObstructed = false;
        Coordinate last = line.get(0);
        for (Coordinate coor : line) {
            isObstructed =
                    isObstructed 
                    || obstructionMap.valueOf(last) 
                    || obstructionMap.valueOf(coor);
            isObstructed = checkSquare(last, coor, obstructionMap, isObstructed);
            last = coor;
        }
        return isObstructed;
    }

    private boolean isAdjNotDiag (Coordinate first, Coordinate second) {
        return (Math.abs(first.getX() - second.getX()) +
                Math.abs(first.getY() - second.getY())) <= ONE;
    }
    
    private boolean isDiagNeigh (Coordinate first, Coordinate second) {
        return (Math.abs(first.getX() - second.getX()) == ONE) &&
               (Math.abs(first.getY() - second.getY()) == ONE);
    }
    
    
    /**
     * If diagonal, will check the two squares that the 
     * line might be jumping through:<br>
     * x = line<br>
     * o = obstruction<br>
     * [o] [x]<br>
     * [x] [o]<br>
     * The above will by design choice count as obstructed
     * @return true if obstructed, false if not
     */
    private boolean checkSquare (Coordinate first,
                                 Coordinate second,
                                 BitMap obstructionMap,
                                 boolean currentValue) {
        boolean toReturn = currentValue;
        if (isDiagNeigh(first, second)) {
            Coordinate left = first.getX() < second.getX() ? first : second;
            Coordinate right = first.getX() < second.getX() ? second : first;
            double yOffset = left.getY() < right.getY() ? ONE : -ONE;
            Coordinate leftOffset = new Coordinate(left.getX(), left.getY() + yOffset);
            Coordinate rightOffset = new Coordinate(right.getX(), right.getY() - yOffset);
            toReturn = toReturn
                    || obstructionMap.valueOf(leftOffset)
                    || obstructionMap.valueOf(rightOffset);
        }
        return toReturn;
    }

    private List<List<Coordinate>> findAllEdges (BitMap obstructionMap) {
        BitMap destructiveCopy = new BitMap(obstructionMap);

        // TODO fill in method
        return null;
    }

    private List<Coordinate> recursiveEdgeHelper (BitMap obstructionMap, List<Coordinate> inEdge) {
        // TODO fill in method
        return null;
    }

    private BitMap getObstructionMap () {
        return myObstructionMap;
    }

    public double getMinGap () {
        return myMinGap;
    }

    private void setMinGap (double gap) {
        myMinGap = gap;
    }

}
