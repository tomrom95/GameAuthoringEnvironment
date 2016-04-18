package facebookutil.applications;

import java.util.List;
import facebookutil.HighScoreBoard;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public abstract class App extends SocialProfile {

    public abstract void login ();
    
    public abstract void notifyUsers(List<IUser> users, String message);
    
    public abstract void customPost (String message, SocialProfile profile);
    
    public abstract void HighScoreBoardPost (HighScoreBoard board, SocialProfile profile);
    
}
