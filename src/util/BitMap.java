package util;

/**
 * Object representation of Bitmap. It will be used for implementing placeable-area, obstruction,
 * and artificial intelligence Pathing.
 * 
 * @author Jin An
 * @author Jon Im
 *
 */
public class BitMap extends BitMapAbstract {

    
    public BitMap (int width, int height) {
        super(width, height);
    }

    public BitMap (IBitMap map) {
        super(map);
    }

    public BitMap () {
        this(1, 1);
    }
    
    public boolean valueOf (int row, int column) {
        return getBitMap()[row][column];
    }
   
   
}
