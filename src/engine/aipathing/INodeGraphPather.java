package engine.aipathing;

import java.util.List;
import util.Coordinate;
import util.ISampledBitMap;


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
    List<Coordinate> findPathFor (ISampledBitMap obstructionMap, Coordinate start, Coordinate goal);

}
