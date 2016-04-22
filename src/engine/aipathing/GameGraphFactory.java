package engine.aipathing;

import engine.IGame;
import util.BitMap;
import util.Coordinate;
import java.util.List;


public class GameGraphFactory implements INodeGraphFactory {

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

    private INodeGraph fillNodeGraph (BitMap obstructionMap, int gap, List<List<Coordinate>> edges) {
        // TODO fill in method
        // tracking the nodes that we put in using an array
        INodeGraph toReturn = new NodeGraph(); 
        int numHorizontalNodes = obstructionMap.getWidth() / gap;
        int numHeightNodes = obstructionMap.getHeight() / gap;
        IPathNode[][] placedNodes = new PathNode[numHorizontalNodes][numHeightNodes];
        //place the standard grid
        for(int i = 0; i < obstructionMap.getWidth(); i+=gap){
            for(int j = 0; j < obstructionMap.getHeight(); j+=gap){
                if(obstructionMap.valueOf(i, j)){
                    IPathNode toAdd = new PathNode(new Coordinate(i,j));
                    toReturn.addNode(toAdd);
                    placedNodes[i][j] = toAdd;
                }
            }
        }

        // fill map with nodes at gapped interval
        // place nodes half-way between different edge points if distance is <= NODE_GAP
        // connect nodes by doing distance search
        // distance gridDistance * sqrt(2) radius circle
        // Fill a new INodeGraph with nodes except where obstructed at minGap resolution
        // connect the nodes to their cardinal neighbors
        // maybe use a data structure to store this
        
        return toReturn;

    }

    private List<List<Coordinate>> findAllEdges (BitMap obstructionMap) {
        BitMap destructiveCopy = new BitMap(obstructionMap);

        
        // TODO fill in method
        return null;
    }
    
    private List<Coordinate> recursiveEdgeHelper(BitMap obstructionMap, List<Coordinate> inEdge ){
        //TODO fill in method
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
