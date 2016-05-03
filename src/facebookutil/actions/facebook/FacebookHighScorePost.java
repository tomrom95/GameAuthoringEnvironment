package facebookutil.actions.facebook;

import facebookutil.SocialType;
import facebookutil.actions.HighScorePost;
import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.HighScoreMessage;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.IUser;


public class FacebookHighScorePost extends FacebookCustomPost implements HighScorePost {

    @Override
    public void createPost (HighScoreBoard board, IUser user, String gameName, ScoreOrder order) {
        HighScoreMessage message = new HighScoreMessage(board);
        String boardString = message.getUserScoreString(user, gameName, order);
        createPost(boardString, user.getProfiles().getProfileByType(SocialType.FACEBOOK));
    }

}
