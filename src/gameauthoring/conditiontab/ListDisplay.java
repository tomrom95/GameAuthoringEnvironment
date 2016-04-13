package gameauthoring.conditiontab;

import engine.profile.IProfilable;
import gameauthoring.ProfileCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class ListDisplay<T extends IProfilable> implements Glyph {

    private ListView<T> myListView;
    private BorderPane myPane;

    public ListDisplay (ObservableList<T> list) {

        myPane = new BorderPane();
        myListView = new ListView<>(list);
        myListView.setCellFactory(c -> new ProfileCellView<T>());
        init();

    }

    private void init () {

        getPane().bottomProperty().set(myListView);

    }

    @Override
    public Node draw () {
        return getPane();
    }

    protected BorderPane getPane () {
        return myPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
