package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CachingEdgeBitMap extends SetBitMap implements IEdgeBitMap {

    private List<List<Coordinate>> myEdges;

    public CachingEdgeBitMap (int height, int width) {
        super(height, width);
        myEdges = new ArrayList<>();
    }

    @Override
    public Iterator<ArrayPosition> trueIterator () {
        return trueIter();
    }

    @Override
    public List<List<Coordinate>> getEdges () {
        return myEdges;
    }

    @Override
    public void setEdges (List<List<Coordinate>> toSet) {
        myEdges = toSet;
    }

}
