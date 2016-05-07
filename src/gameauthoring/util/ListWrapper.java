package gameauthoring.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A wrapper for an object that holds a list of items
 *
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <E> The type of item that this class holds in its list
 */

public class ListWrapper<E> implements IListWrapper<E> {

    private Metadata myMetadata;
    private ObservableList<E> myItems;

    public ListWrapper (String titleKey) {
        this(titleKey, FXCollections.observableArrayList());
    }

    public ListWrapper (String titleKey, ObservableList<E> items) {
        myMetadata = new Metadata(titleKey);
        myItems = items;
    }

    @Override
    public ObservableList<E> getItems () {
        return myItems;
    }

    @Override
    public Metadata getListMetadata () {
        return myMetadata;
    }

}
