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

    private String myTitle;
    private ObservableList<E> myItems;

    public DefinitionCollection (String title) {
        this(title, FXCollections.observableArrayList());
    }
    
    public DefinitionCollection (String title, ObservableList<E> items) {
        myTitle = title;
        myItems = items;
    }

    @Override
    public String getTitle () {
        return myTitle;
    }

    @Override
    public ObservableList<E> getItems () {
        return myItems;
    }

    @Override
    public void addItem (E item) {
        myItems.add(item);
    }
    

}
