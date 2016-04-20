package facebookutil.actions;

import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.IUser;


/**
 * This interface extends CustomPost and manages methods specifically handle high scores
 *
 */
public interface HighScorePost extends CustomPost {

    /**
     * Builds high score post for a user
     * 
     * @param user game player with the resulting high score
     * @param score the new high score for the user
     */
    public void createPost (HighScoreBoard board, IUser user, String gameName, ScoreOrder order);

}
