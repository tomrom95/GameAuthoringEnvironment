package gameauthoring.characters;

import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public interface IFormDataManager {
    
    void add (FormData data);
    
    void add (String key, String value);
    
    void add (String key, List<String> values);
    
    void set (String key, String value);
    
    void set (String key, List<String> values);

    void remove(String key);
    
    ObservableList<StringProperty> getValues (String key);
    
    /**
     * Convenience method for when key has only one associated value String
     * (which is true in most cases). 
     * 
     * @param key
     * @return
     */
    StringProperty getValue (String key);


}
