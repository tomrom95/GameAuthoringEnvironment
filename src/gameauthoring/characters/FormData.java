package gameauthoring.characters;

import java.util.*;


/**
 * Class for very generic data storage based on user input, strings will be used to generate or
 * represent objects from the GameEngine using reflection
 * 
 * @author JoeLilien, Jeremy Schreck
 *
 */

public class FormData {
    private String myKey;
    private List<String> myValue;

    public FormData (String key, List<String> value) {
        this.myKey = key;
        this.myValue = value;
    }
    public FormData (String key, String value) {
        this.myKey = key;
        this.myValue = new ArrayList<String>(Arrays.asList(value));
    }

    public String getMyKey () {
        return myKey;
    }

    public void setMyKey (String myKey) {
        this.myKey = myKey;
    }

    public List<String> getMyValue () {
        return myValue;
    }

    public void setMyValue (List<String> myValue) {
        this.myValue = myValue;
    }

}
