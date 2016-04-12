package gameauthoring;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <E>
 */

public class DefinitionCollection<E> implements IDefinitionCollection<E>{

    String myTitle;
    ObservableList<E> myItems;
 
    
    public DefinitionCollection(String title){
        myTitle = title;
        myItems = FXCollections.observableArrayList();
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
