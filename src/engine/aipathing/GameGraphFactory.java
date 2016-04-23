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
        for (int i = 0; i < numHorizontalNodes; i++) {
            for (int j = 0; j < numHeightNodes; j++) {
                if (!obstructionMap.valueOf(i, j)) {
                    IPathNode toAdd = new PathNode(new Coordinate(i*gap, j*gap));
                    toReturn.addNode(toAdd);
                    placedNodes[i][j] = toAdd;
                }
            }
        }
        connectUnobstructedNodes(placedNodes, obstructionMap);
        addEdgeNodes(toReturn, edges, obstructionMap, gap, placedNodes);
        return toReturn;

    }


    
    /**
     * Adding nodes to the graph and connecting them in order to selectively sample
     * wherever edges are closer to each other than the sampling resolution selected
     * in regards to obstructability and traversability
     * @param graph
     * @param edges
     * @param obstructionMap
     * @param gap
     * @param placedNodes
     * @return
     */
    private void addEdgeNodes (INodeGraph graph,
                                          List<List<ArrayPosition>> edges,
                                          IBitMap obstructionMap,
                                          int gap,
                                          IPathNode[][] placedNodes) {
        for (List<ArrayPosition> edge1 : edges) {
            for (List<ArrayPosition> edge2 : edges) {
                if (!edge1.equals(edge2)) {
                    for (ArrayPosition pos1 : edge1) {
                        for (ArrayPosition pos2 : edge2) {
                            if (!pos1.equals(pos2)) {
                                attemptGapNodeAdd(graph, pos1, pos2, obstructionMap, gap,
                                                  placedNodes);
                           }
                       }
                   }
                }
            }
        }

        // if getting weird results check to see if re-comparing already compared edges is 
        //causing problems
        return;
    }

    private void attemptGapNodeAdd (INodeGraph graph,
                                    ArrayPosition pos1,
                                    ArrayPosition pos2,
                                    IBitMap obstructionMap,
                                    int gap,
                                    IPathNode[][] placedNodes) {
        if (PathNodeGeometry.distance(pos1, pos2) <= gap) {
            ArrayPosition pixelMidPoint = PathNodeGeometry.midPoint(pos1, pos2);
            IPathNode proposed = new PathNode(pixelMidPoint);
            //need to check the placed nodes, which are placed at gap intervals
            List<ArrayPosition> toCheck =
                    surroundingPositions(convertPixelToGraphPosition(pixelMidPoint, gap));
            List<ArrayPosition> toConnect = new ArrayList<>();
            for (ArrayPosition check : toCheck) {
                addIfBoundsAndNull(toConnect, placedNodes, check);
            }
            for (ArrayPosition check : toConnect) {
                if (connectIfNotObstructed(proposed, placedNodes[check.getX()][check.getY()],
                                           obstructionMap)) {
                    if (!graph.containsNode(proposed)) {
                        graph.addNode(proposed);
                    }
                }
            }

        }
        return;

    }

    private ArrayPosition convertPixelToGraphPosition (ArrayPosition pos, int gap) {
        return new ArrayPosition((pos.getX() / gap), (pos.getY() / gap));
    }
    

    
    private List<List<ArrayPosition>> findAllEdges (IBitMap obstructionMap) {
        List<List<ArrayPosition>> edgeList = new ArrayList<>();
        IBitMap obMapCopy = new AutoTrueBitMap(obstructionMap);
        Iterator<ArrayPosition> iter = obMapCopy.positionIterator();
        while (iter.hasNext()) {
            ArrayPosition pos = iter.next();
            if (isEdge(obMapCopy, pos)) {
                edgeList.add(recursiveEdgeHelper(obMapCopy, pos, new ArrayList<>()));
                removeObstructionMask(obMapCopy, pos);
            }
        }
        return edgeList;
    }
    /**
     * This method will clear all the bits that are considered part of the same contiguous
     * obstruction
     * @param obstructionMap
     * @param pos
     */
    private void removeObstructionMask (IBitMap obstructionMap,
                                        ArrayPosition pos) {
        if (obstructionMap.valueOf(pos)) {
            obstructionMap.set(pos, false);
            List<ArrayPosition> toCheck = surroundingPositions(pos);
            for (ArrayPosition check : toCheck) {
                if (obstructionMap.inBounds(check)) {
                    removeObstructionMask(obstructionMap, check);
                }
            }
        }
        
    }
    
    /**
     * Finds the edges
     * @param obstructionMap
     * @param pos
     * @param inEdge
     * @return
     */
    private List<ArrayPosition> recursiveEdgeHelper (IBitMap obstructionMap,
                                                     ArrayPosition pos,
                                                     List<ArrayPosition> inEdge) {
        if (isEdge(obstructionMap, pos)) {
            if (!inEdge.contains(pos)) {
                inEdge.add(pos);
                List<ArrayPosition> toCheck = surroundingPositions(pos);
                for (ArrayPosition check : toCheck) {
                    if (obstructionMap.inBounds(check)) {
                        if (!inEdge.contains(check)) {
                            recursiveEdgeHelper(obstructionMap, check, inEdge);
                        }
                    }
                }
            }
        }
        return inEdge;
    }
    
    /**
     * Obstruction edges are all the positions that are themselves obstructed, but
     * have adjacent neighbors that are themselves not obstructed
     * @param obstructionMap
     * @param pos to check if it is an edge
     * @return  True if one of the non-diagonally adjacent squares is not obstructed
     */
    private boolean isEdge (IBitMap obstructionMap, ArrayPosition pos) {
        boolean selfObstructed = obstructionMap.valueOf(pos);
        boolean edgeTop = !obstructionMap.valueOf(pos.getX(), pos.getY() - INT_ONE);
        boolean edgeBot = !obstructionMap.valueOf(pos.getX(), pos.getY() + INT_ONE);
        boolean edgeRight = !obstructionMap.valueOf(pos.getX() + INT_ONE, pos.getY());
        boolean edgeLeft = !obstructionMap.valueOf(pos.getX() - INT_ONE, pos.getY());
        return (edgeTop || edgeBot || edgeRight || edgeLeft) && selfObstructed;
    }

    private void connectUnobstructedNodes (IPathNode[][] nodes, IBitMap obstructionMap) {
        int width = nodes.length;
        int height = nodes[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayPosition pos = new ArrayPosition(i, j);
                if (inBoundsAndNotNull(nodes, pos)) {
                    List<ArrayPosition> toCheck = nodesToCheck(nodes, pos);
                    for (ArrayPosition potNeigh : toCheck) {
                        if (inBoundsAndNotNull(nodes, potNeigh)) {
                            connectIfNotObstructed(nodes[potNeigh.getX()][potNeigh.getY()],
                                                   nodes[pos.getX()][pos.getY()],
                                                   obstructionMap);
                        }
                    }
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
        for (ArrayPosition checkPos : surroundingPositions(pos)) {
            addIfBoundsAndNull(toReturn, nodes, checkPos);
        }
        return toReturn;
    }
    

    
    /**
     * Diagonal and adjacent ArrayPositions surrounding an input position
     * @param pos
     * @return
     */
    private List<ArrayPosition> surroundingPositions (ArrayPosition pos) {
        List<ArrayPosition> toReturn = new ArrayList<>();
        toReturn.add(new ArrayPosition(pos.getX() + INT_ONE, pos.getY() + INT_ONE));
        toReturn.add(new ArrayPosition(pos.getX() + INT_ONE, pos.getY()));
        toReturn.add(new ArrayPosition(pos.getX(), pos.getY() + INT_ONE));
        toReturn.add(new ArrayPosition(pos.getX() + INT_NEG_ONE, pos.getY() + INT_NEG_ONE));
        toReturn.add(new ArrayPosition(pos.getX() + INT_NEG_ONE, pos.getY()));
        toReturn.add(new ArrayPosition(pos.getX(), pos.getY() + INT_NEG_ONE));
        toReturn.add(new ArrayPosition(pos.getX() + INT_ONE, pos.getY() + INT_NEG_ONE));
        toReturn.add(new ArrayPosition(pos.getX() + INT_NEG_ONE, pos.getY() + INT_ONE));
        return toReturn;
    }
    
    
    private void addIfBoundsAndNull (List<ArrayPosition> addable,
                                     IPathNode[][] nodes,
                                     ArrayPosition pos) {

        if (inBoundsAndNotNull(nodes, pos)) {
            addable.add(pos);
        }

    }

    private boolean inBoundsAndNotNull (IPathNode[][] nodes, ArrayPosition pos) {
        return inBounds(nodes, pos) && isNotNull(nodes, pos);
    }

    private boolean isNotNull (IPathNode[][] nodes, ArrayPosition pos) {
        return nodes[pos.getX()][pos.getY()] != null;
    }
    

    private boolean inBounds (IPathNode[][] nodes, ArrayPosition pos) {
        int width = nodes.length;
        int height = nodes[0].length;
        return (pos.getX() < width) 
                && (pos.getX() >= 0) 
                && (pos.getY() < height) 
                && (pos.getY() >= 0);
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
    private boolean connectIfNotObstructed (IPathNode first,
                                         IPathNode second,
                                         IBitMap obstructionMap) {
        boolean toReturn = false;
        List<Coordinate> pixelLine =
                PathNodeGeometry.lineRounder(PathNodeGeometry.lineBetween(first, second));
        if (!lineObstructed(pixelLine, obstructionMap)) {
            makeNeighbors(first, second);
            toReturn = true;
        }
        return toReturn;
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
