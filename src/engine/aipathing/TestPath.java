package engine.aipathing;

import util.AutoTrueBitMap;
import util.IBitMap;


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

        // INodeGraphFactory tester = new GameGraphFactory(obstructionMap);
        // INodeGraph testGraph = tester.getConstructedGraph();

    }

    private static void printArray (boolean[][] input) {
        for (boolean[] element : input) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(element[j] + " ");
            }
            System.out.println();
        }
    }

}
