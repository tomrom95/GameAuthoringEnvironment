package gameauthoring.characters;

import java.util.List;


public interface IFormDataManager {
    
    void add (FormData data);

    void remove(String key);
    
    List<String> getValues (String key);
    
    /**
     * Convenience method for when key has only one associated value String
     * (which is true in most cases). 
     * 
     * @param key
     * @return
     */
    String getValue (String key);


}
