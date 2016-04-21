package util;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BundleOperations {

    /**
     * ResourceBundle is passed here so it can by others
     * Static because it is a resource to which an instance of the class shouldn't have to be
     * created
     * 
     * @param bundle
     * @return
     */

    public static final ObservableList<String> getValuesAsObservable (ResourceBundle bundle) {
        List<String> list = Collections.list(bundle.getKeys()).stream()
                .map(key -> bundle.getString(key))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(list);
    }
    
    public static final ObservableList<String> getKeysAsObservable (ResourceBundle bundle) {
        return FXCollections.observableArrayList(bundle.keySet());
    }


}
