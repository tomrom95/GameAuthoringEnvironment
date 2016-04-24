package gameauthoring.listdisplay;

import java.util.Locale;
import java.util.ResourceBundle;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ListDisplay<T extends IProfilable> implements Glyph {

    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);
    private ListView<T> myListView;
    private GridPane myPane;

    public ListDisplay (ObservableList<T> list) {
        myPane = new GridPane();
        initListView(list);

    }
    
    private void initListView (ObservableList<T> list) {
        myListView = new ListView<>(list);
        myListView.setPlaceholder(new Label(myLang.getString("AddCondition")));
        myListView.setCellFactory(c -> new ProfileCellView<T>());        
    }

    protected void add (Node node, int colStart, int rowStart, int colSpan, int rowSpan) {
        getPane().add(node, colStart, rowStart, colSpan, rowSpan);
    }
    
    protected ListView<T> getListView () {
        return myListView;
    }
    

    @Override
    public Node draw () {
        return getPane();
    }

    protected GridPane getPane () {
        return myPane;
    }
}
