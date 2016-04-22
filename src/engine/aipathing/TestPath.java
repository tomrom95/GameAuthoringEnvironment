package engine.aipathing;

import util.BitMap;


public class TestPath {

    public static void main (String[] args) {
        BitMap testMap = new BitMap(10, 20);
        int i = 0;
        for (Boolean b : testMap) {
            System.out.println(i + " " + b.toString());
            i++;
        }

    }

}
