package gameauthoring.characters;

import java.util.List;


public interface IFormDataManager {
    
    void add (FormData data);

    void remove(String key);
    
    List<String> getValue (String key);

}
