package gameauthoring.listdisplay;

import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ListDisplay<T extends IProfilable> implements Glyph {

    private ListView<T> myListView;
    private GridPane myPane;

    public ListDisplay (ObservableList<T> list) {

        myPane = new GridPane();
        myListView = new ListView<>(list);
        myListView.setCellFactory(c -> new ProfileCellView<T>());
        init();

    }

    private void init () {
        
        myListView.setPrefWidth(1100);
        myListView.setPrefHeight(200);
        add(myListView, 0, 3, 2, 1);

    }
    
    protected void add (Node node, int colStart, int rowStart, int colSpan, int rowSpan) {
        getPane().add(node, colStart, rowStart, colSpan, rowSpan);
    }

    @Override
    public Node draw () {
        return getPane();
    }

    protected GridPane getPane () {
        return myPane;
    }
}
