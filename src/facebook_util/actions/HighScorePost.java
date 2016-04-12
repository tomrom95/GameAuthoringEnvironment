package facebook_util.actions;

import facebook_util.IUser;

public interface HighScorePost extends CustomPost{

    public void createPost (IUser user, int score);
    
}
