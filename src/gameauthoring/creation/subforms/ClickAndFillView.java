package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import gameauthoring.util.BasicUIFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import splash.LocaleManager;


public abstract class ClickAndFillView extends SubFormView implements IMultiSFView{
    private GridPane myContainer;
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private ScrollPane myPane;
    private VBox myPaneContent;
    private HBox buttonHolder;
    private List<Button> myButtons = new ArrayList<>();
    private ResourceBundle myProperties =
            ResourceBundle.getBundle("defaults/click_and_fill_images");
    private String cssButtonClass = "DynamicButton";
    private String cssScrollClass = "DynamicScroll";
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", LocaleManager
            .getInstance().getCurrentLocaleProperty().get());

    public ClickAndFillView (List<String> options, String titleKey) {
        setMyTitle(titleKey);
        initButtonHolder(options);
    }

    protected ResourceBundle getMyLanguages () {
        return myLang;
    }

    private ResourceBundle getMyProperties () {
        return myProperties;
    }

    @Override
    public List<Button> getMyViewComponents () {
        return myButtons;
    }

    @Override
    public BasicUIFactory getMyUIFactory () {
        return myUIFactory;
    }

    @Override
    public void setMyViewComponents (List<Button> myControls) {
        this.myButtons = myControls;
    }

    private void initButtonHolder (List<String> options) {
        buttonHolder = myUIFactory.makeHBox(20, Pos.CENTER, (Node[]) null);
        initAndAddButtons(buttonHolder, options);
    }

    protected ScrollPane getMyPane () {
        return myPane;
    }

    private void initAndAddButtons (HBox buttonHolder, List<String> options) {
        for (String s : options) {
            Button button =
                    getMyUIFactory().createImageButton(getMyUIFactory()
                            .makeImageDisplay(getMyProperties().getString(s),
                                              getMyLanguages().getString(s)));
            getMyUIFactory().addStyling(button, cssButtonClass);
            getMyViewComponents().add(button);
        }
        buttonHolder.getChildren().addAll(getMyViewComponents());
    }

    @Override
    protected void initView () {
        myContainer = new GridPane();
        myPaneContent = myUIFactory.makeVBox(10, Pos.CENTER, 520, 300, (Node[]) null);
        myPane = myUIFactory.makeScrollPane(myPaneContent, 525, 310); // TODO: magic number
        myUIFactory.addStyling(myPane, cssScrollClass);
        myContainer.add(buttonHolder, 0, 0);
        myContainer.add(myPane, 0, 1);
    }

    @Override
    public abstract void addOrSetSFV (ISubFormView subFormView);

    @Override
    public void removeSFV (ISubFormView subFormView) {
        myPaneContent.getChildren().remove(subFormView.draw());
    }

    @Override
    public void clearSFVs () {
        myPaneContent.getChildren().clear();
    }

    public VBox getMyPaneContent () {
        return myPaneContent;
    }

    @Override
    public void setViewComponentAction (Button button, EventHandler<ActionEvent> e) {
        button.setOnAction(e);
    }

    @Override
    public Node draw () {
        return defaultDisplayWithNode(myContainer);
    }

}
