package gameauthoring.creation.subforms;

import gameauthoring.util.BasicUIFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import splash.LocaleManager;
import java.util.*;


public abstract class ClickAndFillView extends SubFormView {
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

    // TODO: add to language files and use this
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

    public List<Button> getMyButtons () {
        return myButtons;
    }

    public BasicUIFactory getMyUIFactory () {
        return myUIFactory;
    }

    public void setMyButtons (List<Button> myButtons) {
        this.myButtons = myButtons;
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
                            .makeImageDisplay(getMyProperties().getString(s), getMyLanguages().getString(s)));
            getMyUIFactory().addStyling(button, cssButtonClass);
            getMyButtons().add(button);
        }
        buttonHolder.getChildren().addAll(getMyButtons());
    }

    @Override
    protected void initView () {
        myContainer = new GridPane();
        myPaneContent = myUIFactory.makeVBox(10, Pos.CENTER, 510, 300, (Node[]) null);
        myPane = myUIFactory.makeScrollPane(myPaneContent, 525, 310); // TODO: magic number
        myUIFactory.addStyling(myPane, cssScrollClass);
        myContainer.add(buttonHolder, 0, 0);
        myContainer.add(myPane, 0, 1);
    }

    public abstract void addOrSetSFV (ISubFormView subFormView);

    public void removeSFV (ISubFormView subFormView) {
        myPaneContent.getChildren().remove(subFormView.draw());
    }

    public void clearSFVs () {
        myPaneContent.getChildren().clear();
    }

    public VBox getMyPaneContent () {
        return myPaneContent;
    }

    public void setButtonAction (Button button, EventHandler<ActionEvent> e) {
        button.setOnAction(e);
    }

    @Override
    public Node draw () {
        return defaultDisplayWithNode(myContainer);
    }


}
