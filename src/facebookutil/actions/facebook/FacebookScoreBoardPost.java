package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.HighScoreBoardPost;
import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.profiles.SocialProfile;


public class FacebookScoreBoardPost extends HighScoreBoardPost {
    
    private FacebookCustomPost myPost;

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        myPost.send(service, token);
    }

    @Override
    public void createBoardPost (HighScoreBoard board,
                                 String gameName,
                                 ScoreOrder order,
                                 SocialProfile profile) {
        String boardString = getHighScoreString(board, gameName, order);
        myPost = new FacebookCustomPost();
        myPost.createPost(boardString, profile);
    }

}
