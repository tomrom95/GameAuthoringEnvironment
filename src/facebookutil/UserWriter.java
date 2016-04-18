package facebookutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import facebookutil.user.IUser;
import facebookutil.user.User;
import facebookutil.user.UserScoreBoard;
import facebookutil.user.profiles.SocialProfile;

public class UserWriter {
    
    private static final String PATH = "userdata/";

    public void write (List<IUser> users) {
        users.stream()
             .forEach(u -> writeToFile(u));
    }
    
    private void writeToFile (IUser user) {
        File dir = new File(getClass().getResource(PATH).getPath());
        System.out.println(dir.exists());
        File file = new File(dir, user.getUserEmail());
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(getXML(user));
            fw.close();
            System.out.println(file.getAbsolutePath());
        }
        catch (IOException e) {
            System.out.println("ERROR WRITING USER");
            e.printStackTrace();
        }
    }

    private String getXML (IUser user) {
        // SEND HELP
        
        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        //Class[] types = {com.github.scribejava.core.oauth.OAuth20Service.class};
        //xstream.denyTypes(types);
        xstream.processAnnotations(SocialProfile.class);
        return xstream.toXML(user);
    }

}
