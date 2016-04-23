package engine.aipathing;

import util.BitMap;
import util.Coordinate;
import util.IBitMap;
import util.ArrayPosition;
import util.AutoTrueBitMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class responsible for turning IGame objects
 * into INodeGraphs which can be used by our AI pathing
 * classes
 * @author jonathanim
 *
 */
public class GameGraphFactory implements INodeGraphFactory {
    private static final int INT_ONE = 1;
    private static final int INT_NEG_ONE = -1;
    private static final double ONE = 1d;
    private static final int NODE_GAP = 10;
    private IBitMap myObstructionMap;

    GameGraphFactory (IBitMap obstructionMap) {
        myObstructionMap = new AutoTrueBitMap(obstructionMap);
    }

    @Override
    public INodeGraph getConstructedGraph () {
        List<List<ArrayPosition>> edges = findAllEdges(getObstructionMap());
        return fillNodeGraph(getObstructionMap(), NODE_GAP, edges);

    }

    private INodeGraph fillNodeGraph (IBitMap obstructionMap,
                                      int gap,
                                      List<List<ArrayPosition>> edges) {
        INodeGraph toReturn = new NodeGraph();
        int numHorizontalNodes = obstructionMap.getWidth() / gap;
        int numHeightNodes = obstructionMap.getHeight() / gap;
        IPathNode[][] placedNodes = new PathNode[numHorizontalNodes][numHeightNodes];
        // place the standard grid less obstructed areas
        for (int i = 0; i < obstructionMap.getWidth(); i += gap) {
            for (int j = 0; j < obstructionMap.getHeight(); j += gap) {
                if (!obstructionMap.valueOf(i, j)) {
                    IPathNode toAdd = new PathNode(new Coordinate(i, j));
                    toReturn.addNode(toAdd);
                    placedNodes[i][j] = toAdd;
                }
            }
        }
        connectUnobstructedNodes(placedNodes, obstructionMap);
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
                                       IBitMap obstructionMap) {
        // connect nodes by doing distance search
        // distance NODE_GAP * sqrt(2) radius circle
        // TODO
    }

    private List<IPathNode> addEdgeNodes (INodeGraph graph,
                                          List<List<ArrayPosition>> edges,
                                          IBitMap obstructionMap) {

        // fill map with nodes at gapped interval
        // place nodes half-way between different edge points if distance is <= NODE_GAP
        // TODO
        return null;
    }
    
    private List<List<ArrayPosition>> findAllEdges (IBitMap obstructionMap) {
        List<List<ArrayPosition>> edgeList = new ArrayList<>();
        IBitMap obMapCopy = new BitMap(obstructionMap);
        Iterator<ArrayPosition> iter = obMapCopy.positionIterator();
        while (iter.hasNext()) {
            ArrayPosition pos = iter.next();
            boolean isObstructed = obMapCopy.valueOf(pos);
            if (isObstructed && isEdge(obMapCopy, pos)) {
                edgeList.add(recursiveEdgeHelper(obstructionMap, new ArrayList<>()));
                //if so then recursively build edge set by moving along this edge
            }
        }
        // TODO fill in method
        return null;
    }

    private List<ArrayPosition> recursiveEdgeHelper (IBitMap obstructionMap, List<Coordinate> inEdge) {
        // TODO fill in method
        return null;
    }
    
    /**
     * Assumes that we are on an obstructed location, will return true if one
     * of the non-diagonally adjacent squares is not obstructed
     * @param obstructionMap
     * @param pos
     * @return 
     */
    private boolean isEdge (IBitMap obstructionMap, ArrayPosition pos) {
        boolean edgeTop = !obstructionMap.valueOf(pos.getX(), pos.getY() - INT_ONE);
        boolean edgeBot = !obstructionMap.valueOf(pos.getX(), pos.getY() + INT_ONE);
        boolean edgeRight = !obstructionMap.valueOf(pos.getX() + INT_ONE, pos.getY());
        boolean edgeLeft = !obstructionMap.valueOf(pos.getX() - INT_ONE, pos.getY());
        return edgeTop || edgeBot || edgeRight || edgeLeft;
    }

    private void connectUnobstructedNodes (IPathNode[][] nodes, IBitMap obstructionMap) {
        int width = nodes.length;
        int height = nodes[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayPosition pos = new ArrayPosition(i, j);
                List<ArrayPosition> toCheck = nodesToCheck(nodes, pos);
                for (ArrayPosition potNeigh : toCheck) {
                    connectIfNotObstructed(nodes[potNeigh.getX()][potNeigh.getY()],
                                           nodes[pos.getX()][pos.getY()],
                                           obstructionMap);
                }
            }
        }
    }
    
    /**
     * Will list all legal and non-null nodes that are either directly
     * adjacent or diagonally so in relation to the proposed array 
     * location
     * @param nodes
     * @param xLoc
     * @param yLoc
     * @return
     */
    private List<ArrayPosition> nodesToCheck (IPathNode[][] nodes, ArrayPosition pos) {
        List<ArrayPosition> toReturn = new ArrayList<>();
        checkBoundsAndNull(toReturn, nodes,
                      new ArrayPosition(pos.getX() + INT_ONE, pos.getY() + INT_ONE));
        checkBoundsAndNull(toReturn, nodes, new ArrayPosition(pos.getX() + INT_ONE, pos.getY()));
        checkBoundsAndNull(toReturn, nodes, new ArrayPosition(pos.getX(), pos.getY() + INT_ONE));
        checkBoundsAndNull(toReturn, nodes,
                      new ArrayPosition(pos.getX() + INT_NEG_ONE, pos.getY() + INT_NEG_ONE));
        checkBoundsAndNull(toReturn, nodes,
                           new ArrayPosition(pos.getX() + INT_NEG_ONE, pos.getY()));
        checkBoundsAndNull(toReturn, nodes,
                           new ArrayPosition(pos.getX(), pos.getY() + INT_NEG_ONE));
        checkBoundsAndNull(toReturn, nodes,
                      new ArrayPosition(pos.getX() + INT_ONE, pos.getY() + INT_NEG_ONE));
        checkBoundsAndNull(toReturn, nodes,
                      new ArrayPosition(pos.getX() + INT_NEG_ONE, pos.getY() + INT_ONE));
        return toReturn;
    }
    
    private void checkBoundsAndNull (List<ArrayPosition> addable,
                                IPathNode[][] nodes,
                                ArrayPosition pos) {
        
        
        if (inBounds(nodes, pos)) {
            if (nodes[pos.getX()][pos.getY()] != null)
            {
                addable.add(pos);    
            }
        }
    }

    private boolean inBounds (IPathNode[][] nodes, ArrayPosition pos) {
        int width = nodes.length;
        int height = nodes[0].length;
        return (pos.getX() < width) 
                && (pos.getX() > 0) 
                && (pos.getY() < height) 
                && (pos.getY() > 0);
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
    private void connectIfNotObstructed (IPathNode first, IPathNode second, IBitMap obstructionMap) {
        List<Coordinate> pixelLine =
                PathNodeGeometry.lineRounder(PathNodeGeometry.lineBetween(first, second));
        if (!lineObstructed(pixelLine, obstructionMap)) {
            makeNeighbors(first, second);
        }
    }

    private boolean lineObstructed (List<Coordinate> line, IBitMap obstructionMap) {
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
                                 IBitMap obstructionMap,
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



    private IBitMap getObstructionMap () {
        return myObstructionMap;
    }




}
