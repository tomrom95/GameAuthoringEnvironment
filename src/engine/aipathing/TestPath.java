package engine.aipathing;

import util.AutoTrueBitMap;
import util.BitMap;
import util.Coordinate;
import util.IBitMap;
import java.util.ArrayList;
import java.util.List;


public class TestPath {

    private TestPath () {
        // Utility class so hidden constructor
    }

    public static void main (String[] args) {
        IBitMap obstructionMap = new AutoTrueBitMap(1000, 800);
        INodeGraphFactory tester = new GameGraphFactory(obstructionMap);
        INodeGraph testGraph = tester.getConstructedGraph();
        //System.out.println("test");


        
//        Coordinate start = new Coordinate(1,1);
//        Coordinate end = new Coordinate(2,5);
//        List<Coordinate> test = PathNodeGeometry.lineBetween(start, end);
//        test = PathNodeGeometry.lineRounder(test);
//        for(Coordinate coord : test){
//            System.out.println("x: " + coord.getX() + " y: " + coord.getY());
//        }
        

    }

}
