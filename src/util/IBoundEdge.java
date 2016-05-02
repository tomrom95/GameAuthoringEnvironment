package util;

import java.util.List;

/**
 * Data class that provides utility methods for
 * working with 'edges' that are built from the
 * amalgamation of Bounds objects
 * 
 * A singular BoundEdge should represent a contiguous
 * block of space
 * 
 * @author jonathanim
 *
 */
public interface IBoundEdge {

    /**
     * Will check to see if the proposed bound would be part
     * of the contiguous bound already constructed before adding
     * @param bound
     * @return true if bound was added, false if not
     */
    boolean addBoundToEdge (Bounds bound);
    
    /**
     * Will parse the current list of contiguous bounds
     * and will generate pixel by pixel the coordinates that 
     * form the basis of the edge of this shape
     * @return
     */
    List<Coordinate> getEdge ();
    
    /**
     * Will return true if the proposed coordinate falls within the
     * complex bounds object 
     * @param toCheck
     * @return
     */
    boolean complexContains (Coordinate toCheck);
    
    
    
    

}
