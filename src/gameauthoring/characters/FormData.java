package gameauthoring.characters;

import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Class for very generic data storage based on user input, strings will be used to generate or
 * represent objects from the GameEngine using reflection
 * 
 * @author JoeLilien, Jeremy Schreck
 *
 */

public class FormData {
    private String myKey;
    private List<StringProperty> myValues;

    public FormData (String key, List<String> values) {
        List<StringProperty> valuesP = new ArrayList<StringProperty>();
        for (String value : values) {
            valuesP.add(new SimpleStringProperty(value));
        }

        init(key, valuesP);

    }

    public FormData (String key, String value) {
        List<StringProperty> valuesP = new ArrayList<StringProperty>();
        valuesP.add(new SimpleStringProperty(value));
        init(key, valuesP);
    }

//    public FormData (String key, List<StringProperty> values) {
//        init(key, values);
//    }

    private void init (String key, List<StringProperty> values) {
        myKey = key;
        myValues = values;
    }

    public String getMyKey () {
        return myKey;
    }

    public List<StringProperty> getMyValueProperties () {
        return myValues;
    }

    public StringProperty getValueProperty () {
        return myValues.get(0);
    }

}
