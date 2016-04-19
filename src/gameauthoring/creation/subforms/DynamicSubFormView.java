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


public abstract class DynamicSubFormView extends SubFormView {

    private ObservableList<ISubFormView> mySubViews;
    private ObservableList<ProfileDisplay> myListOfSelectionOptions;
    private GridPane myPane = new GridPane();
    private IEntryView mySelection;
    private Node myCurrentSubView;

    public DynamicSubFormView (ObservableList<ISubFormView> views,
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

        myCurrentSubView = entryView.draw();
    }

    protected abstract String getSelectionKey ();

    public void changeSubView (int index) {
        myPane.getChildren().remove(myCurrentSubView);
        myCurrentSubView = mySubViews.get(index).draw();
        myPane.add(myCurrentSubView, 1, 0);
    }

    private void initView () {
        myPane.add(mySelection.draw(), 0, 0);
        // mySubMovementView = myViews.get(0).draw();
        myPane.add(myCurrentSubView, 1, 0);
    }

    @Override
    public Node draw () {
        return myPane;
    }
}
