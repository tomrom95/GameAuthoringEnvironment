package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.IGame;


/**
 * This class takes in a file, and deserializes it to an IGame if it is a valid XML file
 *
 */
public class GameReader implements IGameReader {

    /**
     * Reads in an XML file describing a fully serialized game.
     */
    @Override
    public IGame readFile (File xmlFile) throws LoadErrorException {
        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);
        try {
            String xml = fileToXMLString(xmlFile);
            return (IGame) xstream.fromXML(xml);
        }
        catch (IOException e) {
            // TODO throw a checked error here to make front-end deal with an error if the file is
            // corrupted
            throw new LoadErrorException();
        }
    }

    private String fileToXMLString (File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffReader = getBufferedReader(file);
        String line;
        while ((line = buffReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private BufferedReader getBufferedReader (File file) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        return new BufferedReader(reader);
    }

}
