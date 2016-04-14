package facebookutil;

import facebookutil.login.Login;

/**
 * This interface manages the actions associated with a user 
 *
 */
public interface IUser {
    
    public String getUserID ();
    
    public void logScore (int score);
    
    public int getHighScore ();
    
    public void saveToFile ();
    
    public Login getActiveLogin ();

}
