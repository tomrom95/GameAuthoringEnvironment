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
     * The actual height of the game in virtual pixels
     * @return
     */
    double trueHeight ();
    
    /**
     * Will account for the virtual pixel sampling resolution
     * and return the bit value
     * @param virtualX
     * @param virtualY
     * @return
     */
    boolean translatedValueOf(double virtualX, double virtualY);
    
    
    
    /**
     * Will account for the virtual pixel sampling resolution before
     * setting the bit value
     * @param virtualX
     * @param virtualY
     * @param toSet
     */
    void translatedSet( double virtualX, double virtualY, boolean toSet);
    
    

}
