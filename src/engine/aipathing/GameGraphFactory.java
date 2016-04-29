package engine.aipathing;

import util.Coordinate;
import util.IBitMap;
import util.ISampledBitMap;
import util.SampledBitMap;
import util.ISampledBitMap;
import util.ArrayPosition;
import util.AutoTrueBitMap;
import util.CachingEdgeBitMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import engine.IGame;

/**
 * 
 * This class will directly translate the input sampled bit maps
 * in to a NodeGraph representing the travserable space. 
 * 
 * @author jonathanim
 *
 */
public class GameGraphFactory implements INodeGraphFactory {
    private static final int INT_TWO = 2;
    private static final int INT_ONE = 1;
    private static final int INT_NEG_ONE = -1;
    private static final double ONE = 1d;

    
    
    private ISampledBitMap myObstructionMap;
    private IGame myGame;
    
    GameGraphFactory (ISampledBitMap obstructionMap, IGame game) {
        myObstructionMap = obstructionMap;
        myGame = game;
    }

    @Override
    public INodeGraph getConstructedGraph (Coordinate start, Coordinate goal) {
        INodeGraph toReturn = fillNodeGraph(getObstructionMap(), start, goal);
        //addEdgeNodes(toReturn, convertToArrayPosition(getObstructionMap().getEdges()),
        //             getObstructionMap(), NODE_GAP, toReturn.getPlacedNodes());
        return toReturn;

    }
    
        private List<List<ArrayPosition>> convertToArrayPosition (List<List<Coordinate>> list) {
                List<List<ArrayPosition>> toReturn = new ArrayList<>();
                for (List<Coordinate> toConvert : list) {
                    List<ArrayPosition> converted = new ArrayList<>();
                    for (Coordinate coord : toConvert) {
                        converted.add(new ArrayPosition(coord));
                    }
                    toReturn.add(converted);
                }
                return toReturn;
            }

    private INodeGraph fillNodeGraph (ISampledBitMap obstructionMap,
                                      Coordinate start,
                                      Coordinate goal) {
        int numHorizontalNodes = obstructionMap.getWidth();
        int numHeightNodes = obstructionMap.getHeight();
        int xGap = (int) (obstructionMap.trueWidth() / obstructionMap.getWidth());
        int yGap = (int) (obstructionMap.trueHeight() / obstructionMap.getHeight());
        IPathNode[][] placedNodes = new PathNode[numHorizontalNodes][numHeightNodes];
        INodeGraph toReturn = new NodeGraph(placedNodes);
        // place the standard grid less obstructed areas
        for (int i = 0; i < numHorizontalNodes; i++) {
            for (int j = 0; j < numHeightNodes; j++) {
                ArrayPosition pixelLocation = pixelForArrayLoc(i, j, xGap, yGap);
                if (!obstructionMap.valueOf(i,j)) {
                    IPathNode toAdd = new PathNode(new Coordinate(pixelLocation));
                    toReturn.addNode(toAdd);
                    placedNodes[i][j] = toAdd;
                }
            }
        }
        connectNeighboringGridNodes(placedNodes, obstructionMap);
        //adding the goal and start nodes
        //need to check if the nodes already exist in the graph
        IPathNode startNode = toReturn.addIfCantGetFor(start);
        IPathNode goalNode = toReturn.addIfCantGetFor(goal);
        connectWithAllInGraph(toReturn, startNode, obstructionMap);
        connectWithAllInGraph(toReturn, goalNode, obstructionMap);
        return toReturn;

    }

    private ArrayPosition pixelForArrayLoc (int widthAccess, int heightAccess, int xGap, int yGap) {
        return new ArrayPosition(widthAccess * xGap, //+ (xGap / INT_TWO),
                                 heightAccess * yGap); //+ (yGap / INT_TWO));
    }


    
//    /**
//     * Adding nodes to the graph and connecting them in order to selectively sample
//     * wherever edges are closer to each other than the sampling resolution selected
//     * in regards to obstructability and traversability
//     * @param graph
//     * @param edges
//     * @param obstructionMap
//     * @param gap
//     * @param placedNodes
//     * @return
//     */
//    private void addEdgeNodes (INodeGraph graph,
//                                          List<List<ArrayPosition>> edges,
//                                          ISampledBitMap obstructionMap,
//                                          int gap,
//                                          IPathNode[][] placedNodes) {
//        List<IPathNode> addedNodes = new ArrayList<>();
//        for (List<ArrayPosition> edge1 : edges) {
//            for (List<ArrayPosition> edge2 : edges) {
//                if (!edge1.equals(edge2)) {
//                    for (ArrayPosition pos1 : edge1) {
//                        for (ArrayPosition pos2 : edge2) {
//                            if (!pos1.equals(pos2)) {
//                                addToListIfNotNull(addedNodes, attemptGapNodeAdd(graph, pos1, pos2, obstructionMap, gap,
//                                                  placedNodes));
//                           }
//                       }
//                   }
//                }
//            }
//        }
//        for(IPathNode iter : addedNodes){
//            connectWithAllInGraph(graph, iter, obstructionMap);
//        }
//
//        // if getting weird results check to see if re-comparing already compared edges is 
//        //causing problems
//        return;
//    }
    
//    private <T> void addToListIfNotNull (List<T> list, T obj) {
//        if (obj != null) {
//            list.add(obj);
//        }
//    }

//    private IPathNode attemptGapNodeAdd (INodeGraph graph,
//                                    ArrayPosition pos1,
//                                    ArrayPosition pos2,
//                                    ISampledBitMap obstructionMap,
//                                    int gap,
//                                    IPathNode[][] placedNodes) {
//        if (PathNodeGeometry.distance(pos1, pos2) <= gap) {
//            ArrayPosition pixelMidPoint = PathNodeGeometry.midPoint(pos1, pos2);
//            if(!obstructionMap.valueOf(pixelMidPoint)){
//                IPathNode proposed = new PathNode(pixelMidPoint);
//                if (!graph.containsNode(proposed)) {
//                    addNodeToGraph(graph, proposed);
//                    return proposed;
//                }
//            }
//        }
//        return null;
//    }
    
    /**
     * Without overwriting the existing nodes
     * 
     * @param graph
     * @param node
     */
    private void addNodeToGraph (INodeGraph graph, IPathNode node) {
        if (!graph.containsNode(node)) {
            graph.addNode(node);
        }
    }

    private void connectWithAllInGraph (INodeGraph graph,
                                        IPathNode toConnect,
                                        ISampledBitMap obstructionMap) {
        graph.getNodes()
            .stream()
            .filter(node -> !node.equals(toConnect))
            .forEach(node -> connectIfNotObstructed(toConnect, node, obstructionMap));
    }

//    private ArrayPosition convertPixelToGraphPosition (ArrayPosition pos, int gap) {
//        return new ArrayPosition((pos.getX() / gap), (pos.getY() / gap));
//    }




    

    
//    /**
//     * Obstruction edges are all the positions that are themselves obstructed, but
//     * have adjacent neighbors that are themselves not obstructed
//     * @param obstructionMap
//     * @param pos to check if it is an edge
//     * @return  True if one of the non-diagonally adjacent squares is not obstructed
//     */
//    private boolean isEdge (ISampledBitMap obstructionMap, ArrayPosition pos) {
//        boolean selfObstructed = obstructionMap.valueOf(pos);
//        boolean edgeTop = !obstructionMap.valueOf(pos.getX(), pos.getY() - INT_ONE);
//        boolean edgeBot = !obstructionMap.valueOf(pos.getX(), pos.getY() + INT_ONE);
//        boolean edgeRight = !obstructionMap.valueOf(pos.getX() + INT_ONE, pos.getY());
//        boolean edgeLeft = !obstructionMap.valueOf(pos.getX() - INT_ONE, pos.getY());
//        return (edgeTop || edgeBot || edgeRight || edgeLeft) && selfObstructed;
//    }

    private void connectNeighboringGridNodes (IPathNode[][] nodes, ISampledBitMap obstructionMap) {
        int width = nodes.length;
        int height = width > 0 ? nodes[0].length : 0;
        ArrayPosition pos = new ArrayPosition();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pos.setX(i);
                pos.setY(j);
                if (inBoundsAndNotNull(nodes, pos)) {
                    List<ArrayPosition> toCheck = nodesToCheck(nodes, pos);
                    for (ArrayPosition potNeigh : toCheck) {
                        if (inBoundsAndNotNull(nodes, potNeigh)) {
                            makeNeighbors(nodes[potNeigh.getX()][potNeigh.getY()],
                                          nodes[pos.getX()][pos.getY()]);
                            
//                            connectIfNotObstructed(nodes[potNeigh.getX()][potNeigh.getY()],
//                                                   nodes[pos.getX()][pos.getY()],
//                                                   obstructionMap);
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
        int height = width > 0 ? nodes[0].length : 0;
        return (pos.getX() < width) 
                && (pos.getX() >= 0) 
                && (pos.getY() < height) 
                && (pos.getY() >= 0);
    }

//    /**
//     * Will connect partially connected nodes
//     * 
//     * @param first Node to check
//     * @param second Node to check
//     * @return True if at least one node holds reference of the other, false if not
//     */
//    private boolean ifConnected (IPathNode first, IPathNode second) {
//        boolean connected = false;
//        if (first.getNeighbors().contains(second) || second.getNeighbors().contains(first)) {
//            connected = true;
//            makeNeighbors(first, second);
//        }
//        return connected;
//    }

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
                                         ISampledBitMap obstructionMap) {
        boolean toReturn = false;
        List<Coordinate> pixelLine =
                PathNodeGeometry.lineRounder(PathNodeGeometry.lineBetween(first, second, obstructionMap));
        removeFirstPoint(pixelLine);
        
        if (!lineObstructed(pixelLine, obstructionMap)) {
            makeNeighbors(first, second);
            toReturn = true;
        }
        return toReturn;
    }
    
    /**
     * Avoid comparison of where you are
     * @param toPrune
     */
    private void removeFirstPoint (List<Coordinate> toPrune){
        if (!(toPrune.size() > 0)) {
            return;
        }
        Coordinate first = toPrune.get(0);
        while (toPrune.contains(first)) {
            toPrune.remove(first);
        }
    }

    private boolean lineObstructed (List<Coordinate> line, ISampledBitMap obstructionMap) {
        if (line.size() < 1) {
            return false;
        }
        boolean isObstructed = false;
        Coordinate last = line.get(0);
        for (Coordinate coor : line) {
            isObstructed =
                    isObstructed 
                    || obstructionMap.valueOf(last) 
                    || obstructionMap.valueOf(coor);
            //isObstructed = checkSquare(last, coor, obstructionMap, isObstructed);
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
                                 ISampledBitMap obstructionMap,
                                 boolean currentValue) {
        boolean toReturn = currentValue;
        if (isDiagNeigh(first, second)) {
            Coordinate left = first.getX() < second.getX() ? first : second;
            Coordinate right = first.getX() < second.getX() ? second : first;
            double yOffset = left.getY() < right.getY() ? ONE : -ONE;
            Coordinate leftOffset = new Coordinate(left.getX(), left.getY() + yOffset);
            Coordinate rightOffset = new Coordinate(right.getX(), right.getY() - yOffset);
            toReturn = toReturn
                    || obstructionMap.translatedValueOf(leftOffset)
                    || obstructionMap.translatedValueOf(rightOffset);
        }
        return toReturn;
    }



    private ISampledBitMap getObstructionMap () {
        return myObstructionMap;
    }




}
