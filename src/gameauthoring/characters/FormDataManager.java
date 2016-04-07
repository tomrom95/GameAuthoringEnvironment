package gameauthoring.characters;

import java.util.*;


/**
 * Probably a good alternative to my current implementation for getting data from EntryViews. Rather
 * than SubFormView returning a List<FormData> on getData() call, will return this FormDataManager.
 * Implementation can be a List<FormData> or a Map<String, List<String>> which maps the key of
 * FormData to its value, which seems more natural to me. Good alternative maybe because we avoid
 * need for knowing proper indices (see TempSubFormView) and encapsulates storage of FormData too,
 * which is more flexible design
 * 
 * @author Joe Lilien
 *
 */
public class FormDataManager implements IFormDataManager {

    private Map<String, List<String>> myStorage = new HashMap<String, List<String>>();

    @Override
    public void add (FormData data) {
        myStorage.put(data.getMyKey(), data.getMyValue());
    }

    @Override
    public List<String> getValues (String key) {
        try {
            return myStorage.get(key);
        }
        catch (NullPointerException e) {
            ErrorMessage err = new ErrorMessage("Key Does Not Exist");
            err.showError();
            return null;
        }
    }
    
    @Override
    public String getValue (String key) {
        try {
            // TODO: maybe return error if myStorage.get(key).size ! = 1
            return myStorage.get(key).get(0);
        }
        catch (NullPointerException e) {
            ErrorMessage err = new ErrorMessage("Key Does Not Exist");
            err.showError();
            return null;
        }
    }

    @Override
    public void remove (String key) {
        try {
            myStorage.remove(key);
        }
        catch (NullPointerException e) {
            ErrorMessage err = new ErrorMessage("Key Does Not Exist");
            err.showError();
        }
    }

}
