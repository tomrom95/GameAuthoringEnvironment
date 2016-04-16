package facebookutil.user;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import facebookutil.login.Login;

public class SocialProfile {
    
    private String myUserID;
    @XStreamOmitField
    private Login myLogin;
    
    public SocialProfile (String userID) {
        myUserID = userID;
    }
    
    public void login (Login newLogin) {
        myLogin = newLogin;
    }
    
    public String getUserID () {
        return myUserID;
    }
    
    public Login getLogin () {
        return myLogin;
    }

}
