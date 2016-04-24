package gameauthoring.creation.entryviews;

import java.util.ArrayList;
import java.util.Arrays;
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

    private FormData myFormData;
    private String myLabelCss = "entryViewLabel";
    private Label myLabel;

    public EntryView (String label) {
        myLabel = new Label(label);
        myLabel.getStyleClass().add(myLabelCss);
    }

    public EntryView (String label, IFormDataManager data) {
        this(label);
        myFormData = new FormData(label, new ArrayList<String>(Arrays.asList("")));
        data.add(myFormData);
    }

    @Override
    public Label getLabel () {
        return myLabel;
    }

    @Override
    public FormData getData () {
        return myFormData;
    }
    
    protected abstract void init(String label, String cssClass);

}
