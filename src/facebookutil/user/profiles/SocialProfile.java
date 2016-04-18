package facebookutil.user.profiles;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import facebookutil.login.LoginObject;

public abstract class SocialProfile {
    
    private String myID;
    @XStreamOmitField
    private LoginObject myLogin;
    
    public SocialProfile () {
        
    }
    
    public SocialProfile (String userID) {
        myID = userID;
    }
    
    public void login (LoginObject newLogin) {
        myLogin = newLogin;
        myID = newLogin.getUserID();
    }
    
    public String getID () {
        return myID;
    }
    
    public LoginObject getLogin () {
        return myLogin;
    }

}
