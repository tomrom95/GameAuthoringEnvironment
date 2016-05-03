package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import gameauthoring.util.BasicUIFactory;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import splash.LocaleManager;
import util.StringParser;


/**
 * Organizes and Displays a given grouping of EntryView Objects, according to specific needs of user
 *
 * @author JoeLilien
 *
 */
public abstract class SubFormView implements ISubFormView {

    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private String myTitle;
    private String myStyleClass = "SFVclass";
    private String myLabelStyle = "SFVtitle";
    private String mySubStyle = "SFVsub";
    private ResourceBundle myLabels =
            ResourceBundle
                    .getBundle("languages/labels",
                               LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private ResourceBundle myNumbers = ResourceBundle
            .getBundle("defaults/numbers");

    private StringParser s = new StringParser();

    protected StringParser getParser () {
        return s;
    }

    protected String getMyTitle () {
        return getMyLabels().getString(myTitle);
    }

    protected void setMyTitle (String title) {
        myTitle = title;
    }

    protected Node getTitleDisplay () {
        return getMyUIFactory().addStyling(makeTitleLabel(), myLabelStyle);
    }

    protected Node getSubTitleDisplay () {
        return getMyUIFactory().addStyling(makeTitleLabel(), mySubStyle);
    }

    private Node makeTitleLabel () {
        return new Label(getMyTitle());
    }

    protected VBox defaultDisplayWithNode (Node subview) {
        VBox box = new VBox(getTitleDisplay(), subview);
        getMyUIFactory().addStyling(box, getStyleClass());
        return box;
    }

    protected ResourceBundle getMyLabels () {
        return myLabels;
    }

    public ResourceBundle getMyNumbers () {
        return myNumbers;
    }

    protected BasicUIFactory getMyUIFactory () {
        return myUIFactory;
    }

    /**
     * Initializes arrangement and actual appearence of SFV
     */
    protected abstract void initView ();

    protected String getStyleClass () {
        return myStyleClass;
    }

}
