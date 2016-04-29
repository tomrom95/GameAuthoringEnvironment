package gameauthoring.creation.subforms;

import gameauthoring.util.BasicUIFactory;
import javafx.scene.Node;
import javafx.scene.control.Label;


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
    
    //TODO: work this in
//    public SubFormView(String title){
//        myTitle = title;
//    }
    
    protected String getMyTitle(){
        return myTitle;
    }
    
    protected Node initTitleDisplay () {
        return new Label(getMyTitle());
        //TODO: fill in default display implementation here
    }

    protected BasicUIFactory getMyUIFactory() {
        return myUIFactory;
    }

    /**
     * Initializes arrangement and actual appearence of SFV
     */
    protected abstract void initView ();
    
    protected String getStyleClass() {
        return myStyleClass;
    }

}
