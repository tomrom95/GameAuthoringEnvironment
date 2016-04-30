package facebookutil.user.profiles;

import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.IUser;

/**
 * Profile for local users
 * @author Tommy
 *
 */
public class LocalProfile extends UserProfile{
    private static final String LOCAL_ID = "local";

    public LocalProfile () {
        super(LOCAL_ID);
    }

    @Override
    public void customPost (String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void highScoreBoardPost (HighScoreBoard board, String gameName, ScoreOrder order) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void highScorePost (HighScoreBoard board,
                               String gameName,
                               IUser user,
                               ScoreOrder order) {
        // TODO Auto-generated method stub
        
    }

}
