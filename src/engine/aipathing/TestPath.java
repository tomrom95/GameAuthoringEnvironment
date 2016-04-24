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
        IBitMap obstructionMap = new AutoTrueBitMap(50, 50);
        //setting some obstructions up
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 50; j++){
                obstructionMap.set(i, j, true);
            }
        }
        
        for(int i = 31; i < 50; i++){
            for(int j = 0; j < 50; j++){
                obstructionMap.set(i, j, true);
            }
        }
        
        //System.out.println("test");
        INodeGraphFactory tester = new GameGraphFactory(obstructionMap);
        INodeGraph testGraph = tester.getConstructedGraph();
        for(IPathNode node : testGraph.getNodes()){
            System.out.println(node.getLocation().getX() + " " + node.getLocation().getY());
        }
        System.out.println("test");


        
//        Coordinate start = new Coordinate(1,1);
//        Coordinate end = new Coordinate(2,5);
//        List<Coordinate> test = PathNodeGeometry.lineBetween(start, end);
//        test = PathNodeGeometry.lineRounder(test);
//        for(Coordinate coord : test){
//            System.out.println("x: " + coord.getX() + " y: " + coord.getY());
//        }
        

    }

}
