package gameauthoring.creation.entryviews;

import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


/**
 * Interface to allow for storage and access of Form Data objects, essentially String, List
 * <String> pairs
 *
 * @author Joe Lilien
 *
 */
public interface IFormDataManager {

    void add (FormData data);

    void add (String key, String value);

    void add (String key, List<String> values);

    void set (String key, String value);

    void set (String key, List<String> values);

    void remove (String key);

    ObservableList<StringProperty> getValues (String key);

    /**
     * Convenience method for when key has only one associated value String
     * (which is true in most cases).
     *
     * @param key
     * @return
     */
    StringProperty getValueProperty (String key);

}
