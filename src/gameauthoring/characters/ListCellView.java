package gameauthoring.characters;

import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class ListCellView<E> implements IListCellView<E> {
    private E myItem;
    private Button myEditButton;

    @Override
    public Node draw () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEditAction (Consumer<E> action) {
        getMyEditButton().setOnAction(e -> action.accept(getMyItem()));

    }

    private E getMyItem () {
        return myItem;
    }

    private Button getMyEditButton () {
        return myEditButton;
    }
}
