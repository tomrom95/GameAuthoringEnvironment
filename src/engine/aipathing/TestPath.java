package engine.aipathing;

import util.AutoTrueBitMap;
import util.BitMap;
import util.Coordinate;
import util.IBitMap;
import java.util.ArrayList;
import java.util.List;
import engine.aipathing.GameGraphFactory;


public class TestPath {

    private TestPath () {
        // Utility class so hidden constructor
    }

    public static void main (String[] args) {
        IBitMap obstructionMap = new AutoTrueBitMap(5, 5);
        // setting some obstructions up
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                obstructionMap.set(i, j, true);
            }
        }

        INodeGraphFactory tester = new GameGraphFactory(obstructionMap);
        INodeGraph testGraph = tester.getConstructedGraph();
        for (IPathNode node : testGraph.getNodes()) {
            System.out.println(node.getLocation().getX() + " " + node.getLocation().getY());
        }
        IPathNode close = testGraph.getClosestNode(new Coordinate(1, 2));
    }

}
