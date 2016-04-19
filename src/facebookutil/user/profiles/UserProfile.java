package facebookutil.user.profiles;

import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.IUser;


public abstract class UserProfile extends SocialProfile {

    public UserProfile (String userID) {
        super(userID);
    }

    public abstract void challenge (IUser target, String message);

    public abstract void customPost (String message);

    public abstract void highScorePost (IUser user, int score);

    public abstract void highScoreBoardPost (HighScoreBoard board,
                                             String gameName,
                                             ScoreOrder order);

}
