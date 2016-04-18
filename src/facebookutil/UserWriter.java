package facebookutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public class UserWriter {
    
    private static final String PATH = "savedusers/";
    private static final String WRITE_ERROR = "ERROR WRITING USER";
    private static final String FILE_TYPE = ".xml";

    public void write (List<IUser> users) {
        users.stream()
             .forEach(u -> writeToFile(u));
    }
    
    private void writeToFile (IUser user) {
        File dir = new File(PATH);
        File file = new File(dir, user.getUserEmail() + FILE_TYPE);
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

    private String getXML (IUser user) {
        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(SocialProfile.class);
        return xstream.toXML(user);
    }

}
