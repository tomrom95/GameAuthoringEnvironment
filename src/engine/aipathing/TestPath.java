package engine.aipathing;

import util.BitMap;
import util.Coordinate;
import java.util.ArrayList;
import java.util.List;


public class TestPath {

    private TestPath () {
        // Utility class so hidden constructor
    }

    public static void main (String[] args) {
//        BitMap testMap = new BitMap(10, 20);
//        int i = 0;
//        for (Boolean b : testMap) {
//            System.out.println(i + " " + b.toString());
//            i++;
//        }
        
        Coordinate start = new Coordinate(1,1);
        Coordinate end = new Coordinate(2,5);
        List<Coordinate> test = PathNodeGeometry.lineBetween(start, end);
        test = PathNodeGeometry.lineRounder(test);
        for(Coordinate coord : test){
            System.out.println("x: " + coord.getX() + " y: " + coord.getY());
        }
        

    }

}
