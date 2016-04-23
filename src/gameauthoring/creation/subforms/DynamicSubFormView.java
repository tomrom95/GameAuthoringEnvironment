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


/**
 * This in an abstract class for an SFV that needs to dynamically change its
 * sub-subview based on user input
 * 
 * @author Jeremy Schreck
 *
 */
public abstract class DynamicSubFormView extends SubFormView {

    private List<ISubFormView> mySubViews;
    private GridPane myPane;
    private IEntryView mySelectionView;
    private Node myCurrentSubView;
    private int myCurrentSubViewX = 1;
    private int myCurrentSubViewY = 0;
    private int mySelectionViewX = 0;
    private int mySelectionViewY = 0;

    /**
     * Constructor
     * 
     * TODO: options should be retreived directly either from title of either sfc or sfv
     * 
     * @param views The list of sub-subformviews to dynamically switch between
     * @param action The method to call when user selects a different sub-subview
     * @param options A list of strings containing the titles of each sub-subview
     */
    public DynamicSubFormView (List<ISubFormView> views,
                               Consumer<Integer> action,
                               List<String> options) {
        this.myPane = new GridPane();
        this.mySubViews = views;
        this.myCurrentSubView = mySubViews.get(0).draw();
        initSelectionView(action, options);
        initView();

    }

    /**
     * Initialize the selection view
     * 
     * Note: can be overriden by subclasses if desired
     * 
     * @param action The method to call when user selects a different sub-subview
     * @param options A list of strings containing the titles of each sub-subview
     */
    protected void initSelectionView (Consumer<Integer> action, List<String> options) {

        SingleChoiceEntryView<ProfileDisplay> entryView =
                new SingleChoiceEntryView<ProfileDisplay>(getSelectionKey(),
                                                          createListOfSelectionOptions(options),
                                                          AuthoringView.DEFAULT_ENTRYVIEW);

        entryView.addComboIndexListener(action);
        entryView.setSelected(0);
        mySelectionView = entryView;

    }

    /**
     * Helper method for converting a list of strings to observable list of profile displays
     * 
     * @param options A list of strings containing the titles of each sub-subview
     * @return An observable list of ProfileDisplays based off the titles in options
     */
    private ObservableList<ProfileDisplay> createListOfSelectionOptions (List<String> options) {
        ObservableList<ProfileDisplay> listOfSelectionOptions = FXCollections.observableArrayList();

        for (String option : options) {
            listOfSelectionOptions.add(new ProfileDisplay(option));
        }
        return listOfSelectionOptions;
    }

    /**
     * Initialize the main view of this subform
     */
    protected void initView () {
        myPane.add(mySelectionView.draw(), this.mySelectionViewX, this.mySelectionViewY);
        myPane.add(myCurrentSubView, this.myCurrentSubViewX, this.myCurrentSubViewY);
    }

    /**
     * Get the name of the type of thing you are selecting between
     * 
     * @return A String to be used as the label of the selection view
     */
    protected abstract String getSelectionKey ();

    /**
     * Change which sub-subview is currently being displayed
     * 
     * @param index The index of the sub-subview in mySubViews
     */
    public void changeSubView (int index) {
        myPane.getChildren().remove(myCurrentSubView);
        myCurrentSubView = mySubViews.get(index).draw();
        System.out.println("In here");
        myPane.add(myCurrentSubView, this.myCurrentSubViewX, this.myCurrentSubViewY);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    // Getters and setters
    protected void setMyCurrentSubViewX (int x) {
        this.myCurrentSubViewX = x;
    }

    protected void setMyCurrentSubViewY (int y) {
        this.myCurrentSubViewY = y;
    }

    protected GridPane getMyGridPane () {
        return this.myPane;
    }
}
