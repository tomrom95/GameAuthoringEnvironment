package facebookutil.actions.facebook;

import facebookutil.actions.HighScoreBoardPost;
import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.HighScoreMessage;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.profiles.SocialProfile;


public class FacebookScoreBoardPost extends FacebookCustomPost implements HighScoreBoardPost {

    @Override
    public void createBoardPost (HighScoreBoard board,
                                 String gameName,
                                 ScoreOrder order,
                                 SocialProfile profile) {
        HighScoreMessage message = new HighScoreMessage(board);
        String boardString = message.getHighScoreListString(gameName, order);
        createPost(boardString, profile);
    }

}
