package facebookutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import facebookutil.user.IUser;

/**
 * XStream reader for users. Likely will be refactored so that
 * we can save the high score board as well
 * @author Tommy
 *
 */
public class UserReader {
    
    private static final String PATH = "savedusers/";

    /**
     * Gets the list of users from files
     * @return
     */
    public List<IUser> getUsers () {
        List<IUser> users = new ArrayList<IUser>();
        File dir = new File(PATH);
        File[] list = dir.listFiles();
        for (File f: list) {
            addUser(f, users);
        }
        return users;
    }
    
    /**
     * Reads in an XML file describing a fully serialized game.
     */
    public void addUser (File xmlFile, List<IUser> users) {
        XStream xstream = new XStream(new DomDriver());
        try {
            String xml = fileToXMLString(xmlFile);
            IUser user = (IUser) xstream.fromXML(xml);
            users.add(user);
            System.out.println("Added " + user.getUserEmail());
        }
        catch (IOException e) {
            System.out.println("ERROR LOADING FILE");
            return;
        }
    }

    /**
     * Transforms the file from a file to a string
     * @param file
     * @return
     * @throws IOException
     */
    private String fileToXMLString (File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffReader = getBufferedReader(file);
        String line;
        while ((line = buffReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    /**
     * helper to create a buffered reader of the file
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private BufferedReader getBufferedReader (File file) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        return new BufferedReader(reader);
    }

}
