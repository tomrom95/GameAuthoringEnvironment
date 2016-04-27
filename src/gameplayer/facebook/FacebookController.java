package gameplayer.facebook;

import engine.IAttribute;
import engine.IGame;
import facebookutil.JavaSocial;
import facebookutil.scores.ScoreOrder;

/**
 * Supports simple actions to attach our game to 
 * how the social environment works. This controller supports
 * custom messaging and posting about a high score
 * @author Tommy
 *
 */
public class FacebookController {
    private static final String SCORE_STRING = "score";
    
    private IGame myGame;
    private JavaSocial mySocial = JavaSocial.getInstance();
    
    public FacebookController (IGame game) {
        myGame = game;
    }
    
    /**
     * Sends a custom post using the active user
     * @param message
     * @return
     */
    public boolean postCustom (String message) {
        mySocial.getActiveUser().getProfiles().getActiveProfile().customPost(message);
        return true;
    }
    
    /**
     * Posts your current high score to Facebook.
     * If the game doesn't have a score attribute, then it returns false
     * If it posts correctly, it returns true
     * @return
     */
    public boolean postHighScore () {
        IAttribute scoreAttr = findScoreAttribute();
        if (scoreAttr == null) {
            return false;
        }
        addScore(scoreAttr);
        mySocial.getActiveUser().getProfiles().getActiveProfile()
                .highScorePost(mySocial.getHighScoreBoard(),
                               myGame.getGameInformation().getNameProperty().get(),
                               mySocial.getActiveUser(),
                               ScoreOrder.HIGHEST);
        return true;
    }

    /**
     * Helper to add the current score to the high score board
     * @param scoreAttr
     */
    private void addScore (IAttribute scoreAttr) {
        mySocial.getHighScoreBoard()
                .addNewScore(myGame.getGameInformation().getNameProperty().get(),
                             mySocial.getActiveUser(),
                             (int) scoreAttr.getValueProperty().get());
    }

    /**
     * Finds the score attribute in the game, or null if it doesn't
     * exist
     * @return
     */
    private IAttribute findScoreAttribute () {
        for (IAttribute attr : myGame.getAttributeManager().getAttributes()) {
            if (attr.getType().getType().toLowerCase().equals(SCORE_STRING)) {
                return attr;
            }
        }
        return null;
    }

}
