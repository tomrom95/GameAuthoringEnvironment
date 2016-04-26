package gameauthoring.creation.entryviews;

import gameauthoring.util.BasicUIFactory;
import javafx.scene.control.Label;


/**
 * Abstract class for an simple implementation of IEntryView
 * 
 * Note: one option is to have all of our entry views subclass this, and
 * in populateWithData they will call super and then access their myFormData
 * 
 * Other option is to never store formData and just regenerate a FormData object
 * in getData() every time, and use the passed in form data in populateWithData
 * 
 * design decision: state vs functional programming
 * 
 * @author Jeremy Schreck
 * @author Joe Lilien
 * 
 */
public abstract class EntryView implements IEntryView {

    private String myLabelCss = "entryViewLabel";
    private Label myLabel;
    private BasicUIFactory myFactory = new BasicUIFactory();

    public EntryView (String label) {
        myLabel = new Label(label);
        myLabel.getStyleClass().add(myLabelCss);
    }

    @Override
    public Label getLabel () {
        return myLabel;
    }
    
    protected BasicUIFactory getMyFactory(){
        return myFactory;
    }

    protected abstract void init (String label, String cssClass);

}
