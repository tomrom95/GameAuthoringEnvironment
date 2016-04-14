package facebookutil.actions;

import facebookutil.HighScoreBoard;


/**
 * This interface extends Posts and manages methods to handle posting and interpreting high scores
 *
 */
public interface HighScoreBoardPost extends Post {

    public void createBoardPost (HighScoreBoard board);

}
