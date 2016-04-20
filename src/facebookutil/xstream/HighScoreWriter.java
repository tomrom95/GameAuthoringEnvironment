package facebookutil.xstream;

import java.util.ResourceBundle;
import facebookutil.scores.HighScoreBoard;

public class HighScoreWriter {
    
    private ResourceBundle mySecrets;
    private XStreamWriter<HighScoreBoard> myWriter;
    
    public HighScoreWriter () {
        mySecrets = ResourceBundle.getBundle("facebookutil/secret");
        myWriter = new XStreamWriter<>();
    }
    
    public void write (HighScoreBoard board) {
        String dirPath = mySecrets.getString("highscorefolder");
        String file = mySecrets.getString("scoreboardfile");
        myWriter.writeToFile(board, file, dirPath);
    }
}
