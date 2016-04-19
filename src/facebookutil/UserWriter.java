package facebookutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import facebookutil.user.Email;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

/**
 * XStream writer for users. Writes a list of users all
 * to files in the same directory
 * @author Tommy
 *
 */
public class UserWriter {
    
    private static final String PATH = "savedusers/";
    private static final String WRITE_ERROR = "ERROR WRITING USER";
    private static final String FILE_FORMAT = "%s%s.xml";

    /**
     * Writes each user to a file
     * @param users
     */
    public void write (List<IUser> users) {
        users.stream()
             .forEach(u -> writeToFile(u));
    }
    
    /**
     * Writes a single user to an xml file
     * @param user
     */
    private void writeToFile (IUser user) {
        File dir = new File(PATH);
        File file = new File(dir, createFileName(user));
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(getXML(user));
            fw.close();
            System.out.println("Wrote " + user.getUserEmail());
        }
        catch (IOException e) {
            System.out.println(WRITE_ERROR);
        }
    }

    private String createFileName (IUser user) {
        Email email = user.getUserEmail();
        return String.format(FILE_FORMAT, email.getName(), email.getDomain());
    }

    /**
     * transforms user into XML string
     * @param user
     * @return
     */
    private String getXML (IUser user) {
        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(SocialProfile.class);
        return xstream.toXML(user);
    }

}
