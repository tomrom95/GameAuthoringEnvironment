package facebookutil.actions;

import java.util.List;
import java.util.ResourceBundle;
import facebookutil.scores.HighScoreBoard;
import facebookutil.scores.Score;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.profiles.SocialProfile;


/**
 * This interface extends Posts and manages methods to handle posting and interpreting high scores
 *
 */
public abstract class HighScoreBoardPost implements Post {

    private static final String LANGUAGE_FILE = "facebookutil/englishposts";
    private static final String SCORE_FORMAT = "#%d %s - %d points\n";

    private ResourceBundle myProperties;

    public HighScoreBoardPost () {
        myProperties = ResourceBundle.getBundle(LANGUAGE_FILE);
    }

    public String getHighScoreString (HighScoreBoard board, String gameName, ScoreOrder order) {
        List<Score> scores = board.getScoreBoardSorted(gameName, order);
        StringBuffer message = new StringBuffer();
        addInitialMessage(message, gameName, order);
        addScores(message, scores);
        return message.toString();
    }

    private void addScores (StringBuffer message, List<Score> scores) {
        for (int i = 0; i < scores.size(); i++) {
            message.append(scoreString(scores.get(i), i + 1));
        }

    }

    private String scoreString (Score score, int place) {
        return String.format(SCORE_FORMAT, place, score.getUser().getUserEmail().toString(),
                             score.getPoints());
    }

    private void addInitialMessage (StringBuffer message, String gameName, ScoreOrder order) {
        String template = myProperties.getString("boardpost");
        String adjective = myProperties.getString(order.name());
        message.append(String.format(template, adjective, gameName));
    }

    public abstract void createBoardPost (HighScoreBoard board,
                                 String gameName,
                                 ScoreOrder order,
                                 SocialProfile profile);

}
