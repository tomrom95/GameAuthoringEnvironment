package gameauthoring.creation.entryviews;

import java.util.*;
import gameauthoring.util.ErrorMessage;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import splash.LocaleManager;


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

    private Map<String, ObservableList<StringProperty>> myStorage =
            new HashMap<String, ObservableList<StringProperty>>();
    private ResourceBundle myErrors = ResourceBundle
            .getBundle("defaults/errors");
    private final String myErrorKey = "NoKey";

    @Override
    public void add (FormData data) {
        myStorage.put(data.getMyKey(), data.getMyValueProperties());

    }

    @Override
    public void add (String key, String value) {
        FormData data = new FormData(key, value);
        myStorage.put(data.getMyKey(), data.getMyValueProperties());

    }

    @Override
    public void add (String key, List<String> values) {
        // TODO implementation
        // myStorage.put(key, values);

    }

    @Override
    public ObservableList<StringProperty> getValues (String key) {
        try {
            return myStorage.get(key);
        }
        catch (NullPointerException e) {
            ErrorMessage err = new ErrorMessage(myErrors.getString(myErrorKey));
            err.showError();
            return null;
        }
    }

    @Override
    public StringProperty getValueProperty (String key) {
        try {
            // TODO: maybe return error if myStorage.get(key).size ! = 1
            return myStorage.get(key).get(0);
        }
        catch (NullPointerException e) {
            ErrorMessage err = new ErrorMessage(myErrors.getString(myErrorKey));
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
            ErrorMessage err = new ErrorMessage(myErrors.getString(myErrorKey));
            err.showError();
        }
    }

    @Override
    public void set (String key, String value) {
        if (myStorage.containsKey(key)) {
            myStorage.get(key).get(0).setValue(value);
        }
        else {
            this.add(key, value);
        }
    }

    @Override
    public void set (String key, List<String> values) {
        FormData data = new FormData(key, values);
        if (myStorage.containsKey(key)) {
            myStorage.get(key).setAll(data.getMyValueProperties());
        }
        else {
            this.add(key, values);
        }
    }

}
