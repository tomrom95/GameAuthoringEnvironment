package gameauthoring.listdisplay;

import java.util.Locale;
import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.util.Glyph;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ListDisplay<T extends IProfilable> implements Glyph {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", LocaleManager.getInstance().getCurrentLocaleProperty().get());
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
    
    public void rescale (double width, double height) {

        myPane.setAlignment(Pos.CENTER);
        myPane.setScaleX(width / WIDTH);
        myPane.setScaleY((height) / (HEIGHT - 15));

    }
}
