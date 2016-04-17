package facebookutil.user;

import facebookutil.SocialType;
import facebookutil.login.Login;

/**
 * This interface manages the actions associated with a user 
 *
 */
public interface IUser {
    
    public String getUserEmail ();
    
    public UserScoreBoard getScoreBoard ();
    
    public void logout ();
    
    public SocialMap getProfiles ();
    
    public void login (SocialType type, Login login);

}
