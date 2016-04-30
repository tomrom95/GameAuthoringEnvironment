package util;

/**
 * BitMap with helper methods to assist with dealing with 
 * the fact that inputs and outputs have been sampled from the
 * true source
 * 
 * @author jonathanim
 *
 */
public interface ISampledBitMap extends IBitMap {
    

    
    /**
     * The actual width of the game in virtual pixels
     * @return
     */
    double trueWidth ();
    
    /**
     * Returns the number of horizontal virtual 
     * pixels per horizontal array location
     * @return
     */
    double widthScale ();
    
    /**
     * Returns the number of vertical virtual 
     * pixels per vertical array location
     * @return
     */
    double heightScale ();
    
    /**
     * The actual height of the game in virtual pixels
     * @return
     */
    double trueHeight ();
    
    /**
     * Will account for the virtual pixel sampling resolution
     * and return the bit value
     * 
     * @param virtualX
     * @param virtualY
     * @return
     */
    boolean translatedValueOf (double virtualX, double virtualY);

    /**
     * Convenience method, see {@link ISampledBitMap#translatedValueOf(double, double)}
     * 
     * @param coord
     * @return
     */
    boolean translatedValueOf (Coordinate coord);

    /**
     * Will account for the virtual pixel sampling resolution before
     * setting the bit value
     * @param virtualX
     * @param virtualY
     * @param toSet
     */
    void translatedSet( double virtualX, double virtualY, boolean toSet);
    
    

}
