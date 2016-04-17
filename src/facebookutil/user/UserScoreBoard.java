package facebookutil.user;

import java.util.HashMap;
import java.util.Map;

public class UserScoreBoard {
    
    private Map<String, Integer> myBoard;
    
    public UserScoreBoard () {
        myBoard = new HashMap<>();
    }
    
    public void logScore (String gameName, int score) {
        if (!myBoard.containsKey(gameName) || myBoard.get(gameName) < score) {
            myBoard.put(gameName, score);
        }
    }

    public int getHighScore (String gameName) {
        if (!myBoard.containsKey(gameName)) {
            return -1;
        }
        return myBoard.get(gameName);
    }

}
