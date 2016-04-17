package facebookutil.user;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import facebookutil.login.LoginObject;

public abstract class SocialProfile {
    
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
    
    public abstract void challenge (IUser target, String message);
    
    public abstract void customPost (String message);
    
    public abstract void highScorePost (IUser user, int score);

}
