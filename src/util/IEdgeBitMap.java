package util;

import java.util.Iterator;
import java.util.List;

/**
 * Adding edge storage to facilitate generation and checking
 * Going to cache in these objects the true locations, so that 
 * we can return an iterator over just the locations that matter
 * @author jonathanim
 *
 */
public interface IEdgeBitMap extends IBitMap {
    
    /**
     * Lists of lists of Coordinate objects representing the edges
     * that are present in the map
     * @return
     */
    List<List<Coordinate>> getEdges ();
    
    
    void setEdges (List<List<Coordinate>> toSet);
    
    
    /**
     * Iterator over only the true values, to prevent having to
     * search the entire array for areas of interest
     * @return
     */
    Iterator<ArrayPosition> trueIterator ();
    
    
    

}
