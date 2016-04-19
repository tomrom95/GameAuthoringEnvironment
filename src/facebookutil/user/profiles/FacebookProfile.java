package facebookutil.user.profiles;

import facebookutil.actions.facebook.FacebookCustomPost;
import facebookutil.actions.facebook.FacebookScoreBoardPost;
import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.IUser;

public class FacebookProfile extends UserProfile{

    public FacebookProfile (String userID) {
        super(userID);
    }

    @Override
    public void challenge (IUser target, String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void customPost (String message) {
        FacebookCustomPost post = new FacebookCustomPost();
        post.createPost(message, this);
        post.send(getLogin().getService(), getLogin().getToken());
    }

    @Override
    public void highScorePost (IUser user, int score) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void highScoreBoardPost (HighScoreBoard board,
                                    String gameName,
                                    ScoreOrder order) {
        FacebookScoreBoardPost post = new FacebookScoreBoardPost();
        post.createBoardPost(board, gameName, order, this);
        post.send(getLogin().getService(), getLogin().getToken());
    }

}
