package facebookutil.actions;

import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.profiles.SocialProfile;


/**
 * This interface extends Posts and manages methods to handle posting and interpreting high scores
 *
 */
public interface HighScoreBoardPost extends Post {

    public abstract void createBoardPost (HighScoreBoard board,
                                 String gameName,
                                 ScoreOrder order,
                                 SocialProfile profile);

}
