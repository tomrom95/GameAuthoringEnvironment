package facebook_util;

import facebook_util.login.Login;

public interface IUser {
    
    public String getUserID ();
    
    public void logScore (int score);
    
    public int getHighScore ();
    
    public void saveToFile ();
    
    public Login getActiveLogin ();

}
