package engine;

import java.io.File;


/**
 * This class represents an XML converter that can extract an IGame model from an XML file
 * describing that game
 * 
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IXMLReader {

    /**
     * @param xmlFile serialized version of IGame class
     * @return recreated IGame model
     */
    IGame convertXMLToGame (File xmlFile);
}
