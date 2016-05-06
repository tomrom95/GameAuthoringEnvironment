package gameauthoring.listdisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.conditions.ICondition;
import engine.profile.IProfilable;
import engine.profile.Profile;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.ConditionUIFactory;
import gameauthoring.util.ErrorMessage;
import gameauthoring.util.IncompleteFormException;
import gameauthoring.util.UIFactory;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import splash.LocaleManager;


public abstract class SubConditionView {

    protected static final double CUSHION = 10;
    private static final String DEFAULT_IMAGE = "/images/C.png";
    private ResourceBundle myLabels =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private ResourceBundle mySize = ResourceBundle.getBundle("defaults/subcondition");
    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");

    private UIFactory myFactory = new ConditionUIFactory();
    private GridPane myGroup = new GridPane();
    private ObservableList<ICondition> myList;
    private TextField myName = new TextField();
    private TextArea myDescription = new TextArea();
    private List<Node> myNodes;

    public SubConditionView (ObservableList<ICondition> conditionList) {
        myGroup.getStyleClass().add(myStyle.getString("ConditionPane"));
        myList = conditionList;
        myNodes = new ArrayList<>();
    }

    protected void initStage () {
        setSizes();
        myGroup.setHgap(CUSHION);
        myGroup.setVgap(CUSHION);
        myGroup.add(addProfileInfo(), 1, 0);
        initializeDisplay();
    }

    private void setSizes () {
        myDescription.setPrefSize(Double.parseDouble(mySize.getString("DescriptionWidth")),
                                  Double.parseDouble(mySize.getString("DescriptionHeight")));
    }

    private Node addProfileInfo () {
        HBox hbox = new HBox(CUSHION);
        hbox.getChildren().add(createVBox(myLabels.getString("Name"), myName));
        hbox.getChildren().add(createVBox(myLabels.getString("Description"), myDescription));
        return hbox;
    }

    private Node createVBox (String label, Node node2) {
        VBox vbox = new VBox(CUSHION);
        vbox.getChildren().addAll(myFactory.createSubTitleLabel(label), node2);
        return vbox;
    }

    protected void addStringComboBox (ComboBox<String> combo) {
        myNodes.add(combo);
    }

    protected void initializeDisplay () {
        initBoxes();
        myGroup.add(getHBox(), 0, 1, 3, 1);
        myGroup.add(createButton(), 0, 0);
    }

    protected abstract void initBoxes ();

    private Node createButton () {
        Button button = myFactory.createButton(myLabels.getString("Create"));
        button.setOnAction(e -> addCondition());
        return button;
    }

    private void addCondition () {

        try {
            ICondition condition = createCondition();
            myList.add(condition);
        }
        catch (IncompleteFormException e) {
            ErrorMessage error = new ErrorMessage(e.getMessage());
            error.showError();
        }
    }

    protected void add (Node node, int column, int row, int colW, int rowW) {
        myGroup.add(node, column, row, colW, rowW);
    }

    private ICondition createCondition () throws IncompleteFormException {
        try {
            ICondition condition = subCreation();
            condition.setProfile(new Profile(myName.getText(), myDescription.getText(),
                                             DEFAULT_IMAGE));
            return condition;
        }
        catch (NullPointerException | NumberFormatException e) {
            throw new IncompleteFormException(myLabels.getString("FillForm"));
        }
    }

    protected abstract ICondition subCreation ();

    public Pane show () {
        return myGroup;
    }

    protected <T extends IProfilable> ComboBox<T> createComboBox (ObservableList<T> list) {
        ComboBox<T> combo = new BasicUIFactory().createCombo(list);
        myFactory.addStyling(combo, "WhiteCombo");
        myNodes.add(combo);
        return combo;
    }

    protected ComboBox<String> createStringComboBox (ObservableList<String> list) {
        ComboBox<String> box = new ComboBox<>(list);
        myNodes.add(box);
        return box;
    }

    /**
     * Loops through the property file grabbing the proper labels
     *
     * @return HBox of label/combo-box pairs
     */
    protected Node getHBox () {

        FlowPane pane = new FlowPane();
        setSize(pane, Double.parseDouble(mySize.getString("MaxWrap")));
        pane.setVgap(CUSHION);
        pane.setVgap(CUSHION);
        for (int i = 0; i < myNodes.size(); i++) {
            String label = myLabels.getString(getLabelKey(Integer.toString(i)));
            pane.getChildren().add(getCombo(label, myNodes.get(i)));
        }
        return pane;
    }

    private void setSize (Pane pane, double size) {
        pane.setMinWidth(size);
        pane.setMaxWidth(size);
        pane.setPrefWidth(size);
    }

    private Node getCombo (String label, Node node) {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(label), node);
        return vbox;
    }

    protected TextField createTextField () {
        TextField text = new TextField();
        myNodes.add(text);
        return text;
    }

    protected abstract String getLabelKey (String key);
}
