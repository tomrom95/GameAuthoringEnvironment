package facebookutil.xstream;

import java.io.File;
import java.util.ResourceBundle;
import facebookutil.scores.HighScoreBoard;

public class HighScoreReader {
    
    private ResourceBundle mySecrets;
    private XStreamReader myReader;
    
    public HighScoreReader () {
        mySecrets = ResourceBundle.getBundle("facebookutil/secret");
        myReader = new XStreamReader();
    }

    /**
     * Gets the list of users from files
     * @return
     */
    public HighScoreBoard getBoard () {
        String fileName = mySecrets.getString("highscorefolder") + mySecrets.getString("scoreboardfile");
        File file = new File(fileName);
        HighScoreBoard board = (HighScoreBoard) myReader.getObject(file);
        if (board == null) {
            return new HighScoreBoard();
        }
        return board;
    }


}
