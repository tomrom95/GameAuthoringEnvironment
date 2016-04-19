package gameplayer;

import java.util.ResourceBundle;
import gameauthoring.util.Glyph;

public abstract class SizeableGlyph implements Glyph{

    private static final String PATH = "defaults/gameplayer";
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);
    
    protected double parseString (String input) {
        //TODO return error
        return Double.parseDouble(input);
    }

    protected String getString (String key) {
        return myBundle.getString(key);
    }
    
    
}
