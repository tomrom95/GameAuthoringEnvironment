package facebook_util;

import java.util.Map;

public interface HighScoreBoard {
    
    public void update ();
    
    public Map<String, Integer> getScoreBoard (String gameName);

}
