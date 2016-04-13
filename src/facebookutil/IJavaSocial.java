package facebookutil;

import java.util.List;

public interface IJavaSocial {
    
    public List<IUser> getUsers ();
    
    public List<IUser> getActiveUsers ();
    
    public IUser getUserByID (String id);
    
    public HighScoreBoard getHighScoreBoard ();
    
    public void loginUser ();
    
    public void createNewUser (IUser user);

}
