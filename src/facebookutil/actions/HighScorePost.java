package facebookutil.actions;

import facebookutil.IUser;

public interface HighScorePost extends CustomPost{

    public void createPost (IUser user, int score);
    
}
