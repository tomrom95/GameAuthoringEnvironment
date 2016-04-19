package gameauthoring.creation.subforms;

import java.util.List;
import java.util.function.Consumer;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import util.Coordinate;


public abstract class DynamicSubFormView extends SubFormView {

    private List<ISubFormView> mySubViews;
    private ObservableList<ProfileDisplay> myListOfSelectionOptions;
    private GridPane myPane = new GridPane();
    private IEntryView mySelection;
    private Node myCurrentSubView;
    private int myCurrentSubViewX = 1;
    private int myCurrentSubViewY = 0;

    public DynamicSubFormView (List<ISubFormView> views,
                               Consumer<Integer> action,
                               List<String> options) {
        this.mySubViews = views;
        initEntryViews(action, options);
        initView();
    }

    protected void initEntryViews (Consumer<Integer> action, List<String> options) {
        myListOfSelectionOptions = FXCollections.observableArrayList();
        for (String option : options) {
            myListOfSelectionOptions.add(new ProfileDisplay(option));
        }

        SingleChoiceEntryView<ProfileDisplay> entryView =
                new SingleChoiceEntryView<ProfileDisplay>(getSelectionKey(),
                                                          myListOfSelectionOptions,
                                                          AuthoringView.DEFAULT_ENTRYVIEW);

        entryView.addComboIndexListener(action);

        // add initializer somewhere?
        entryView.setSelected(myListOfSelectionOptions.get(0));

        mySelection = entryView;
        
    }

    protected abstract String getSelectionKey ();

    public void changeSubView (int index) {
        myPane.getChildren().remove(myCurrentSubView);
        myCurrentSubView = mySubViews.get(index).draw();
        myPane.add(myCurrentSubView, this.myCurrentSubViewX, this.myCurrentSubViewY);
    }

    protected void initView () {
        myPane.add(mySelection.draw(), 0, 0);
        myCurrentSubView = mySubViews.get(0).draw();
        myPane.add(myCurrentSubView, this.myCurrentSubViewX, this.myCurrentSubViewY);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    protected void setMyCurrentSubViewX (int x) {
        this.myCurrentSubViewX = x;
    }

    protected void setMyCurrentSubViewY (int y) {
        this.myCurrentSubViewX = y;
    }

    protected GridPane getMyGridPane () {
        return this.myPane;
    }
}
