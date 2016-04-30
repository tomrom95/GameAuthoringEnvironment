package gameauthoring.shareddata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A wrapper for an object that holds a list of items
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <E> The type of item that this class holds in its list
 */

public class DefinitionCollection<E> implements IDefinitionCollection<E> {

    private String myTitleKey;
    private ObservableList<E> myItems;

    public DefinitionCollection (String titleKey) {
        this(titleKey, FXCollections.observableArrayList());
    }
    
    public DefinitionCollection (String titleKey, ObservableList<E> items) {
        myTitleKey = titleKey;
        myItems = items;
    }

    @Override
    public String getTitleKey () {
        return myTitleKey;
    }

    @Override
    public ObservableList<E> getItems () {
        return myItems;
    }

    @Override
    public void addItem (E item) {
        myItems.add(item);
    }
    
    @Override
    public void removeItem (E item) {
        myItems.remove(item);
    }

}
