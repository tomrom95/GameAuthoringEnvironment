package facebookutil.applications;

import java.util.List;
import facebookutil.HighScoreBoard;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public interface AppActor {
    
    void notifyUsers(List<IUser> users, String message);
    
    void customPost (String message, SocialProfile profile);
    
    void HighScoreBoardPost (HighScoreBoard board, SocialProfile profile);

}
