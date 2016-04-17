package facebookutil.user;


/**
 * This interface manages the actions associated with a user 
 *
 */
public interface IUser {
    
    public String getUserEmail ();
    
    public UserScoreBoard getScoreBoard ();
    
    public void saveToFile ();
    
    public SocialMap getProfiles ();

}
