package facebookutil.user;

import facebookutil.SocialType;
import facebookutil.login.LoginObject;

/**
 * This interface manages the actions associated with a user 
 *
 */
public interface IUser {
    
    public Email getUserEmail ();
    
    public UserScoreBoard getScoreBoard ();
    
    public void logout ();
    
    public SocialMap getProfiles ();
    
    public void login (SocialType type, LoginObject login);

}
