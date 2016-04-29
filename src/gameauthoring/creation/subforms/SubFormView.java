package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import gameauthoring.util.BasicUIFactory;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import splash.LocaleManager;


/**
 * Organizes and Displays a given grouping of EntryView Objects, according to specific needs of user
 * 
 * @author JoeLilien
 *
 */
public abstract class SubFormView implements ISubFormView {

    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private String myTitle;
    private ResourceBundle myLabels =
            ResourceBundle
                    .getBundle("languages/labels",
                               LocaleManager.getInstance().getCurrentLocaleProperty().get());

    // TODO: work this in
    // public SubFormView(String title){
    // myTitle = title;
    // }

    protected String getMyTitle () {
        return getMyLabels().getString(myTitle);
    }

    protected void setMyTitle (String title) {
        this.myTitle = title;
    }

    protected Node getTitleDisplay () {
        return new Label(getMyTitle());
        // TODO: fill in default display implementation here
    }

    protected VBox defaultDisplayWithNode(Node subview){
        return new VBox(getTitleDisplay(), subview);
    }
    protected ResourceBundle getMyLabels(){
        return myLabels;
    }
    
    protected BasicUIFactory getMyUIFactory () {
        return myUIFactory;
    }

    /**
     * Initializes arrangement and actual appearence of SFV
     */
    protected abstract void initView ();

}
