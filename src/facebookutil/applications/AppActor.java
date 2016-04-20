package facebookutil.applications;

import java.util.List;
import facebookutil.scores.HighScoreBoard;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public interface AppActor {
    
    void notifyUsers(List<IUser> users, String message);
    
    void HighScoreNotify (HighScoreBoard board, SocialProfile profile);

}
