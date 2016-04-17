package facebookutil.user;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import facebookutil.login.LoginObject;

public class SocialProfile {
    
    private String myUserID;
    @XStreamOmitField
    private LoginObject myLogin;
    
    public SocialProfile (String userID) {
        myUserID = userID;
    }
    
    public void login (LoginObject newLogin) {
        myLogin = newLogin;
    }
    
    public String getUserID () {
        return myUserID;
    }
    
    public LoginObject getLogin () {
        return myLogin;
    }

}
