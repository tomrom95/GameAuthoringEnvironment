package util;

/**
 * Interface provides extra information regarding the 
 * down sampling resolution.
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
     * The actual height of the game in virutal pixels
     * @return
     */
    double trueHeight ();

}
