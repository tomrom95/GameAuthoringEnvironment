package gameauthoring.creation.entryviews;

import gameauthoring.util.BasicUIFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


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
    private Pane myContainer;
    private BasicUIFactory myFactory = new BasicUIFactory();
    private double containerSpacing = 20;

    public EntryView (String label, String cssClass) {
        myLabel = new Label(label);
        myLabel.getStyleClass().add(myLabelCss);        
        initContainer(cssClass);
    }
    
    protected void initContainer (String cssClass) {
        myContainer = myFactory.makeVBox(containerSpacing, Pos.CENTER, myLabel);
        myContainer.getStyleClass().add(cssClass);
    }

    @Override
    public Label getLabel () {
        return myLabel;
    }
    
    protected Pane getMyContainer () {
        return myContainer;
    }
    
    protected void setMyContainer(Pane pane) {
        myContainer = pane;
    }
    
    protected BasicUIFactory getMyFactory(){
        return myFactory;
    }

    protected abstract void init ();

}
