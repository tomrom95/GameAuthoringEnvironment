package data;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.Game;


public class GameReader implements IGameReader {

    @Override
    public Game readFile (File file) throws LoadErrorException {

        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);

        String xml;
        try {
            xml = fileToXMLString(file);
            return (Game) xstream.fromXML(xml);
        }
        catch (IOException e) {
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
