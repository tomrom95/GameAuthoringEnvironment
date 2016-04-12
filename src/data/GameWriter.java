package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.Game;

/**
 * This class takes in a Game and converts it into an XML file for the user to store 
 *
 */

public class GameWriter implements IGameWriter {

    @Override
    public void serialize (File file, Game game) throws IOException {
       
        FileWriter fw = new FileWriter(file);
        fw.write(getXML(game));
        fw.close();
    }

    private String getXML (Game game) {
        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);

        return xstream.toXML(game);
    }

   
}
