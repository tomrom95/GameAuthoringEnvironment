package engine.aipathing;

import util.Coordinate;
import util.ISampledBitMap;
import util.ArrayPosition;
import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import engine.ObstructionManager;

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


    
    
    private ISampledBitMap myObstructionMap;

    
    GameGraphFactory (ISampledBitMap obstructionMap, IGame game) {
        myObstructionMap = obstructionMap;
    }

    @Override
    public INodeGraph getConstructedGraph (Coordinate start, Coordinate goal) {
        INodeGraph toReturn = fillNodeGraph(getObstructionMap(), start, goal);
        return toReturn;

    }

    private INodeGraph fillNodeGraph (ISampledBitMap obstructionMap,
                                      Coordinate start,
                                      Coordinate goal) {
        int numHorizontalNodes = obstructionMap.getWidth();
        int numHeightNodes = obstructionMap.getHeight();
        System.out.println(numHorizontalNodes);
        System.out.println(numHeightNodes);
        int xGap = ObstructionManager.SAMPLE_RESOLUTION;
        int yGap = ObstructionManager.SAMPLE_RESOLUTION;
        IPathNode[][] placedNodes = new PathNode[numHorizontalNodes][numHeightNodes];
        INodeGraph toReturn = new NodeGraph(placedNodes);
        // place the standard grid less obstructed areas
        for (int i = 0; i < numHorizontalNodes; i++) {
            for (int j = 0; j < numHeightNodes; j++) {
                ArrayPosition pixelLocation = pixelForArrayLoc(i, j, xGap, yGap);
                if (!obstructionMap.valueOf(i, j)) {
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
                                 heightAccess * yGap); // + (yGap / INT_TWO));
    }



  

    private void connectWithAllInGraph (INodeGraph graph,
                                        IPathNode toConnect,
                                        ISampledBitMap obstructionMap) {
        graph.getNodes()
            .stream()
            .filter(node -> !node.equals(toConnect))
            .forEach(node -> connectIfNotObstructed(toConnect, node, obstructionMap));
    }

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
                PathNodeGeometry
                        .lineRounder(PathNodeGeometry.lineBetween(first, second, obstructionMap));
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





    private ISampledBitMap getObstructionMap () {
        return myObstructionMap;
    }




}
