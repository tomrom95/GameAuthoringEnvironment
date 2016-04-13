package facebookutil;

import facebookutil.login.Login;

public interface IUser {
    
    public String getUserID ();
    
    public void logScore (int score);
    
    public int getHighScore ();
    
    public void saveToFile ();
    
    public Login getActiveLogin ();

}
