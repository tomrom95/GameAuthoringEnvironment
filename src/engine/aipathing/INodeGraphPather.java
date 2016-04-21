package engine.aipathing;

import java.util.List;
import util.BitMap;
import util.Coordinate;


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
    List<Coordinate> findPathFor (BitMap obstructionMap, Coordinate start, Coordinate goal);

}
