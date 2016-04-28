package engine.aipathing;

import java.util.List;
import util.Coordinate;
import util.IBitMap;
import util.IEdgeBitMap;


public interface INodeGraphPather {

    /**
     * Using some graph search algorithm, will return a list of way-points from the starting
     * location to the desired goal location
     * 
     * @param obstructionMap
     * @param start
     * @param goal
     * @return
     */
    List<Coordinate> findPathFor (IEdgeBitMap obstructionMap, Coordinate start, Coordinate goal);

}
