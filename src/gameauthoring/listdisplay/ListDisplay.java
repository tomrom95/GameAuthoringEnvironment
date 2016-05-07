package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import splash.LocaleManager;


public class ListDisplay<T extends IProfilable> implements Glyph {

    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/list_display");
    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private ListView<T> myListView;
    private GridPane myPane;

    public ListDisplay (ObservableList<T> list) {
        myPane = new GridPane();
        myPane.setAlignment(Pos.CENTER);
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

    public void rescale (double width, double height) {

        myPane.setScaleX(width / Double.parseDouble(myBundle.getString("Width")));
        myPane.setScaleY(height / Double.parseDouble(myBundle.getString("Height")));

    }
}
