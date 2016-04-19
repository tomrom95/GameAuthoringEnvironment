package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.HighScoreBoardPost;
import facebookutil.actions.HighScorePost;
import facebookutil.scores.HighScoreBoard;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public class FacebookHighScorePost implements HighScorePost {

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createPost (String message, SocialProfile profile) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createPost (IUser user, int score) {
        // TODO Auto-generated method stub
        
    }

}
