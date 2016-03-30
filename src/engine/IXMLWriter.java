package engine;

import java.io.File;


/**
 * This class represents an XML converter that can output an XML file from a given IGame object
 * 
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IXMLWriter {

    /**
     * @param game to serialize and output
     * @param outputFile to write to
     */
    void convertXMLToGame (IGame game, File outputFile);
}
